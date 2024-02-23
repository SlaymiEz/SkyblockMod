package fr.slaymi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class TestTessellator {
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event){
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.disableLighting();

        GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);

        worldRenderer.begin(GL11.GL_LINE, DefaultVertexFormats.POSITION_COLOR);

        int x = 0;
        int y = (int) Minecraft.getMinecraft().thePlayer.getEyeHeight();
        int z = 10;

        // south side [pos z] [parent x]
        worldRenderer.pos(x - 0.5f, y + 0.5f, z + 0.5f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        worldRenderer.pos(x - 0.5f, y - 0.5f, z + 0.5f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();


        RenderGlobal renderGlobal = Minecraft.getMinecraft().renderGlobal;


        tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
}
