package io.github.derexxd.sift_backport.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.Collection;
import java.util.Collections;

public class SiftTeleportCommand {

    public static final ResourceKey<Level> SIFT_DIMENSION_KEY = ResourceKey.create(
            Registry.DIMENSION_REGISTRY,
            new ResourceLocation("sift", "sift")
    );

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        register(event.getDispatcher());
    }

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

            ServerLevel targetLevel = (player.getLevel().dimension().equals(SIFT_DIMENSION_KEY))
                    ? source.getServer().getLevel(Level.OVERWORLD)
                    : siftLevel;

            if (targetLevel == null) {
                targetLevel = siftLevel;
            }

            double x = player.getX();
            double z = player.getZ();
            BlockPos topPos = targetLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, new BlockPos(x, 0, z));
            double y = topPos.getY();


            player.teleportTo(targetLevel, x + 0.5, y + 1.0, z + 0.5, player.getYRot(), 0.0F);
        }

        return targets.size();
    }
}
