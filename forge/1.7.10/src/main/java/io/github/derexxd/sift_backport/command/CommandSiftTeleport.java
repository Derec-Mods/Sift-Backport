package io.github.derexxd.sift_backport.command;

import java.util.Collections;
import java.util.List;
import io.github.derexxd.sift_backport.world.ModDimensions;
import io.github.derexxd.sift_backport.world.TeleporterSift;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;

public class CommandSiftTeleport extends CommandBase {

    @Override
    public String getCommandName() {
        return "siftteleport";
    }

    @Override
    public List getCommandAliases() {
        return Collections.singletonList("sifttp");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/siftteleport [player]";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP player;
        if (args.length > 0) {
            player = getPlayer(sender, args[0]);
        } else if (sender instanceof EntityPlayerMP) {
            player = (EntityPlayerMP) sender;
        } else {
            throw new CommandException("commands.generic.player.notFound");
        }

        player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "This is a band aid for now as we debug and work on this, barely anything is officially announced yet"));

        int targetDim = (player.dimension == ModDimensions.SIFT_DIM_ID) ? 0 : ModDimensions.SIFT_DIM_ID;
        MinecraftServer server = MinecraftServer.getServer();
        WorldServer targetWorld = server.worldServerForDimension(targetDim);

        if (targetWorld == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Target dimension world " + targetDim + " is not loaded!"));
            return;
        }

        server.getConfigurationManager().transferPlayerToDimension(player, targetDim, new TeleporterSift(targetWorld));
        String destName = (targetDim == 0) ? "Overworld" : "The Sift";
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Teleported " + player.getCommandSenderName() + " to " + destName + " (dim " + targetDim + ")."));
    }
}
