package fr.slaymi;


import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class ChestCommand extends CommandBase {
    public static List<BlockPos> blPos = new ArrayList<>();
    @Override
    public String getCommandName() {
        return "chestConfig";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Ez";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 2){
            if (args[0].equals("set")){
                if (args[1].equals("on")){
                    ChestTracer.actived = true;
                    chatLog("Successfully turned ON the ESP");
                } else if (args[1].equals("off")) {
                    ChestTracer.actived = false;
                    chatLog("Successfully turned OFF the ESP");
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
            }
            if (args[0].equals("width")) {
                float f = Float.valueOf(args[1]);
                chatLog("Successfully set the width to " + f);
                ChestTracer.width = f;
            }
            if (args[0].equals("bl")){
                if (args[1].equals("add")){
                    blPos.addAll(ChestTracer.chestPositions);
                    chatLog("Successfully blacklisted all annoying chests");
                } else if (args[1].equals("clear")){
                    blPos.clear();
                    chatLog("Successfully cleared the blacklist");
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
            }
        }
        if (args.length == 1) {
            if (args[0].equals("debug")) {
                ChestTracer.openedChests.clear();
            } else {
                CommandException exception = new CommandException("get rekt idiot");
                throw exception;
            }
        }
    }
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        return true;
    }
    public void chatLog(String str){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
    }
}
