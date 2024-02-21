package fr.slaymi;


import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ChestCommand extends CommandBase {

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
                    chatLog("Succesfully turned ON the ESP");
                } else if (args[1].equals("off")) {
                    ChestTracer.actived = false;
                    chatLog("Succesfully turned OFF the ESP");
                } else {
                    CommandException exception = new CommandException("get rekt idiot");
                    throw exception;
                }
            }
            if (args[0].equals("width")) {
                float f = Float.valueOf(args[1]);
                chatLog("Succesfully set the width to " + f);
                ChestTracer.width = f;
            }
        } else {
            CommandException exception = new CommandException("get rekt idiot");
            throw exception;
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
