package io.github.derexxd.sift_backport.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class SiftTeleportCommand {

    public static final ResourceKey<Level> SIFT_DIMENSION_KEY = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.parse("sift:sift")
    );

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("siftteleport")
                .executes(context -> teleportPlayers(context.getSource(), Collections.singleton(context.getSource().getPlayerOrException())))
                .then(Commands.argument("targets", EntityArgument.players())
                    .requires(source -> source.hasPermission(2))
                    .executes(context -> teleportPlayers(context.getSource(), EntityArgument.getPlayers(context, "targets")))
                )
        );
        dispatcher.register(
            Commands.literal("sifttp")
                .executes(context -> teleportPlayers(context.getSource(), Collections.singleton(context.getSource().getPlayerOrException())))
                .then(Commands.argument("targets", EntityArgument.players())
                    .requires(source -> source.hasPermission(2))
                    .executes(context -> teleportPlayers(context.getSource(), EntityArgument.getPlayers(context, "targets")))
                )
        );
    }

    private static int teleportPlayers(CommandSourceStack source, Collection<ServerPlayer> targets) {
        ServerLevel siftLevel = source.getServer().getLevel(SIFT_DIMENSION_KEY);
        if (siftLevel == null) {
            source.sendFailure(Component.literal("Sift dimension ('sift:sift') could not be found!"));
            return 0;
        }

        for (ServerPlayer player : targets) {
            player.displayClientMessage(
                Component.literal("This is a band aid for now as we debug and work on this, barely anything is officially announced yet")
                    .withStyle(ChatFormatting.YELLOW),
                false
            );

            ServerLevel targetLevel = (player.serverLevel().dimension().equals(SIFT_DIMENSION_KEY))
                    ? source.getServer().getLevel(Level.OVERWORLD)
                    : siftLevel;

            if (targetLevel == null) {
                targetLevel = siftLevel;
            }

            double x = player.getX();
            double z = player.getZ();
            BlockPos topPos = targetLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, new BlockPos((int) x, 0, (int) z));
            double y = topPos.getY();

            if (y <= targetLevel.getMinBuildHeight()) {
                y = 65;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        targetLevel.setBlockAndUpdate(new BlockPos((int) x + dx, (int) y - 1, (int) z + dz), Blocks.STONE.defaultBlockState());
                        targetLevel.setBlockAndUpdate(new BlockPos((int) x + dx, (int) y, (int) z + dz), Blocks.AIR.defaultBlockState());
                        targetLevel.setBlockAndUpdate(new BlockPos((int) x + dx, (int) y + 1, (int) z + dz), Blocks.AIR.defaultBlockState());
                    }
                }
            }

            Set<RelativeMovement> relatives = Collections.emptySet();
            player.teleportTo(targetLevel, x + 0.5, y + 1.0, z + 0.5, relatives, player.getYRot(), 0.0F);
        }

        return targets.size();
    }
}
