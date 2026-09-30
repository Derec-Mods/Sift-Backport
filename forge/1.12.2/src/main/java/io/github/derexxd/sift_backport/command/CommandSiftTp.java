package io.github.derexxd.sift_backport.command;

import io.github.derexxd.sift_backport.world.ModDimensions;
import io.github.derexxd.sift_backport.world.TeleporterSift;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;

public class CommandSiftTp extends CommandBase {

    @Override
    public String getName() {
        return "sifttp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/sifttp [player]";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player;
        if (args.length > 0) {
            player = getPlayer(server, sender, args[0]);
        } else if (sender instanceof EntityPlayerMP) {
            player = (EntityPlayerMP) sender;
        } else {
            throw new CommandException("commands.generic.player.notFound");
        }

        int targetDim = (player.dimension == ModDimensions.SIFT_DIM_ID) ? 0 : ModDimensions.SIFT_DIM_ID;
        WorldServer targetWorld = server.getWorld(targetDim);

        if (targetWorld == null) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Target dimension world " + targetDim + " is not loaded!"));
            return;
        }

        server.getPlayerList().transferPlayerToDimension(player, targetDim, new TeleporterSift(targetWorld));
        String destName = (targetDim == 0) ? "Overworld" : "The Sift";
        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Teleported " + player.getName() + " to " + destName + " (dim " + targetDim + ")."));
    }
}
