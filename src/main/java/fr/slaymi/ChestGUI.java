package fr.slaymi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ChestGUI extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        super.drawScreen(mouseX, mouseY, partialTicks);
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        FontRenderer fontRenderer = mc.fontRendererObj;
        fontRenderer.drawString("Chests nearby " + String.valueOf(ChestTracer.nearbyChestsPublic), screenWidth/2, screenHeight/2, 0xFFFFFF);
    }
}
