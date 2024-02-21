package fr.slaymi;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;


import java.util.Arrays;
import java.util.List;

public class Waypoint extends CommandBase {
    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName(){
        return "waypoint";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
            chatLog(String.valueOf((int) player.posX) + " " +  String.valueOf((int) player.posY) + " " + String.valueOf((int) player.posZ));
        }
    }
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        return true;
    }
    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("");
    }
    public void chatLog(String str){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
    }
}

