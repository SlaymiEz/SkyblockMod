package fr.slaymi;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.awt.*;

public class MyEventHandlerClass {
    int chatCount = 0;
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        chatCount++;
        System.out.println("Chat received total: " + chatCount);
    }

    @SubscribeEvent
    void onBreak(final BlockEvent.BreakEvent event) {
        Block blockIn = event.state.getBlock();
        if (blockIn instanceof BlockColored && blockIn.getMaterial() == Material.rock && blockIn.getMetaFromState(event.state) == 9) {
            chatLog("works");
        }
        if (blockIn instanceof BlockColored && blockIn.getMaterial() == Material.cloth) {
            if (blockIn.getMetaFromState(event.state) == 3 || blockIn.getMetaFromState(event.state) == 7) {
                chatLog("works");
            }
        }
        if (blockIn instanceof BlockPrismarine){
            chatLog("works");
        }
    }
    public void chatLog(String str){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
    }
}
