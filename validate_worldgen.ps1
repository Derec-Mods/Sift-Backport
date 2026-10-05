# i ai coded this to check my versions im too lazy sue me

$root = $PSScriptRoot

$projects = @{
    "fabric/1.16.5"   = "1.16.5"
    "fabric/1.19.2"   = "1.19.2"
    "forge/1.19.2"    = "1.19.2"
    "fabric/1.20.1"   = "1.20.1"
    "forge/1.20.1"    = "1.20.1"
    "neoforge/1.20.1" = "1.20.1"
    "fabric/26.1"     = "26.1"
    "fabric/26.2"     = "26.2"
    "fabric/26.3"     = "26.3"
    "fabric/1.21.1"   = "1.21.1"
    "neoforge/1.21.1" = "1.21.1"
    "neoforge/26.1"   = "26.1"
    "neoforge/26.2"   = "26.2"
    "neoforge/26.3"   = "26.3"
}

$errors = [System.Collections.Generic.List[string]]::new()
$totalJsonCount = 0

foreach ($kv in $projects.GetEnumerator()) {
    $proj = $kv.Key
    $mcVer = $kv.Value
    $dataDir = Join-Path $root "$proj/src/main/resources/data/sift"
    
    if (-not (Test-Path $dataDir)) {
        $errors.Add("[$proj] Missing data/sift directory")
        continue
    }

    # Validate JSON syntax for all files
    $jsonFiles = Get-ChildItem -Recurse $dataDir -Filter "*.json"
    foreach ($jf in $jsonFiles) {
        $totalJsonCount++
        try {
            $null = Get-Content $jf.FullName -Raw | ConvertFrom-Json
        } catch {
            $errors.Add("[$proj] Invalid JSON in $($jf.FullName): $($_.Exception.Message)")
        }
    }
    
    # 1. Dimension Settings
    $dimPath = Join-Path $dataDir "dimension/sift.json"
    if (Test-Path $dimPath) {
        $dim = Get-Content $dimPath -Raw | ConvertFrom-Json
        if ($dim.generator.settings -ne "sift:sift") {
            $errors.Add("[$proj] dimension settings is '$($dim.generator.settings)' instead of 'sift:sift'")
        }
        if ($mcVer -ne "1.16.5" -and $dim.generator.biome_source.type -ne "minecraft:multi_noise") {
            $errors.Add("[$proj] biome_source is '$($dim.generator.biome_source.type)' instead of 'minecraft:multi_noise'")
        }
    } else {
        $errors.Add("[$proj] Missing dimension/sift.json")
    }
    
    # 2. Noise Settings
    $noisePath = Join-Path $dataDir "worldgen/noise_settings/sift.json"
    if (Test-Path $noisePath) {
        $noise = Get-Content $noisePath -Raw | ConvertFrom-Json
        $blockName = if ($noise.default_block -is [string]) { $noise.default_block } else { $noise.default_block.Name }
        if ($blockName -ne "minecraft:gray_concrete") {
            $errors.Add("[$proj] noise_settings default_block is '$blockName' instead of 'minecraft:gray_concrete'")
        }
    } else {
        $errors.Add("[$proj] Missing worldgen/noise_settings/sift.json")
    }
    
    # 3. Placed Features schema for 1.19.2 and 1.20.1
    if ($mcVer -in @("1.19.2", "1.20.1")) {
        $pfDir = Join-Path $dataDir "worldgen/placed_feature"
        if (Test-Path $pfDir) {
            $pfs = Get-ChildItem -Recurse $pfDir -Filter "*.json"
            foreach ($pf in $pfs) {
                $raw = Get-Content $pf.FullName -Raw
                if ($raw -match '"type":\s*"minecraft:random_offset"' -and $raw -match '"xz_spread":\s*\{\s*"type":\s*"minecraft:uniform"\s*,\s*"min_inclusive"') {
                    $errors.Add("[$proj] $($pf.Name) has inlined 1.21 uniform IntProvider (must use 'value' object for 1.20.1/1.19.2)")
                }
                if ($raw -match '"type":\s*"minecraft:matching_blocks"\s*,\s*"blocks":\s*"') {
                    $errors.Add("[$proj] $($pf.Name) has 1.21 string blocks in matching_blocks filter (must be array for 1.20.1/1.19.2)")
                }
            }
        }
    }

    # 4. Loot Tables
    $lootDir = Join-Path $dataDir "loot_table/blocks"
    if (Test-Path $lootDir) {
        $requiredLoots = @("sculk_grass.json", "sculk_grass_block.json", "light_sculk_grass_block.json", "tall_sculk_grass.json")
        foreach ($rl in $requiredLoots) {
            $rlPath = Join-Path $lootDir $rl
            if (-not (Test-Path $rlPath)) {
                $errors.Add("[$proj] Missing loot table: $rl")
            }
        }
    }

    # 5. Sync to build/resources if present
    $buildDir = Join-Path $root "$proj/build/resources/main/data/sift"
    if (Test-Path $buildDir) {
        Copy-Item -Path "$dataDir\*" -Destination $buildDir -Recurse -Force
    }
}

Write-Host "========================================="
Write-Host "Total JSON files checked: $totalJsonCount"
if ($errors.Count -eq 0) {
    Write-Host "All 14 projects PASSED with 0 errors!" -ForegroundColor Green
} else {
    Write-Host "Found $($errors.Count) error(s):" -ForegroundColor Red
    foreach ($err in $errors) {
        Write-Host "  - $err" -ForegroundColor Yellow
    }
    exit 1
}
Write-Host "========================================="
