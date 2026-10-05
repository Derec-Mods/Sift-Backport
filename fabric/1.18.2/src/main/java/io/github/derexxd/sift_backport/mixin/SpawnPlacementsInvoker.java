package io.github.derexxd.sift_backport.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnPlacements.class)
public interface SpawnPlacementsInvoker {
    @Invoker("register")
    static <T extends Mob> void invokeRegister(
            EntityType<T> entityType,
            SpawnPlacements.Type type,
            Heightmap.Types types,
            SpawnPlacements.SpawnPredicate<T> predicate) {
        throw new AssertionError();
    }
}
