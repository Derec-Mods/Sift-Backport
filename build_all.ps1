$dirs = @(
    "fabric/1.16.5", "fabric/1.19.2", "fabric/1.20.1", "fabric/26.1", "fabric/26.2", "fabric/26.3",
    "forge/1.19.2", "forge/1.20.1",
    "neoforge/1.20.1", "neoforge/1.21.1", "neoforge/26.1", "neoforge/26.2", "neoforge/26.3"
)

foreach ($dir in $dirs) {
    $fullPath = "C:\Users\derex\Documents\GitHub\SiftBackport\$dir"
    Start-Process -FilePath "cmd.exe" -ArgumentList "/c cd /d `"$fullPath`" && gradlew.bat build" -WindowStyle Hidden
}
