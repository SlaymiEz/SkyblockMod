package fr.slaymi;


import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
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
                    chatLog("Successfully turned " + EnumChatFormatting.GREEN + "ON" + EnumChatFormatting.WHITE + " the ESP.");
                } else if (args[1].equals("off")) {
                    ChestTracer.actived = false;
                    chatLog("Successfully turned " + EnumChatFormatting.RED + "OFF" + EnumChatFormatting.WHITE + " the ESP.");
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
            }
            if (args[0].equals("width")) {
                float f = Float.valueOf(args[1]);
                chatLog("Successfully set the width to " + EnumChatFormatting.GREEN + String.valueOf(f));
                ChestTracer.width = f;
            }
            if (args[0].equals("bl")){
                if (args[1].equals("add")){
                    blPos.addAll(ChestTracer.chestPositions);
                    chatLog("Successfully " + EnumChatFormatting.DARK_GRAY + "blacklisted" + EnumChatFormatting.WHITE + " all annoying chests.");
                } else if (args[1].equals("clear")){
                    blPos.clear();
                    chatLog("Successfully " + EnumChatFormatting.GREEN + "cleared" + EnumChatFormatting.WHITE + " the blacklist.");
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
            }
            if (args[0].equals("radius")){
                int i = Integer.valueOf(args[1]);
                ChestTracer.SEARCH_RADIUS = i;
                chatLog("Successfully set the search radius to " + EnumChatFormatting.GREEN + i);
            }
        }
        if (args.length == 3){
            if (args[0].equals("auto")){
                if (args[1].equals("set")){
                    if (args[2].equals("on")){
                        ChestTracer.autoChest = true;
                        chatLog("Successfully turned " + EnumChatFormatting.GREEN + "ON" + EnumChatFormatting.WHITE + " the automatic chest opener.");
                    } else if (args[2].equals("off")){
                        ChestTracer.autoChest = false;
                        chatLog("Successfully turned " + EnumChatFormatting.RED + "OFF" + EnumChatFormatting.WHITE + " the automatic chest opener.");
                    } else {
                        CommandException exception = new CommandException("get rekt idiot");
                        throw exception;
                    }
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
            } else {
                CommandException exception = new CommandException("get rekt idiot");
                throw exception;
            }
        }
        if (args.length == 4){
            if (args[0].equals("auto")){
                if (args[1].equals("slots")){
                    if (args[2].equals("pick")){
                        int i = Integer.valueOf(args[3]);
                        ChestTracer.pickSlot = i - 1;
                        chatLog("Successfully set the pickaxe slot to slot number " + EnumChatFormatting.GREEN + i);
                    } else if (args[2].equals("drill")) {
                        int i = Integer.valueOf(args[3]);
                        ChestTracer.drillSlot = i - 1;
                        chatLog("Successfully set the drill slot to slot number " + EnumChatFormatting.GREEN + i);
                    } else {
                        CommandException exception = new CommandException("get rekt idiot");
                        throw exception;
                    }
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
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
