package fr.slaymi;


import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import scala.tools.nsc.doc.model.Def;


public class ChestTracer {
    public static final int SEARCH_RADIUS = 10;
    public static boolean actived = false;
    public static float width = (float) 8.0;
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event){
        renderTracers(event.partialTicks);
    }

    public static void renderTracers(float partialTick){
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        for (int x = (int) player.posX - SEARCH_RADIUS; x <= (int) player.posX + SEARCH_RADIUS; x++){
            for(int y = (int) player.posY - SEARCH_RADIUS; y <= (int) player.posY + SEARCH_RADIUS; y++) {
                for (int z = (int) player.posZ - SEARCH_RADIUS; z <= (int) player.posZ + SEARCH_RADIUS; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock() instanceof BlockChest) {
                        if (actived == true) {
                            blockESPBox(pos);
                        }
                    }
                }
            }
        }
    }
    private static void renderTracer(EntityPlayer player, double chestX, double chestY, double chestZ){
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(player.posX, player.posY + player.getEyeHeight(), player.posZ).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex();
        worldRenderer.pos(chestX, chestY, chestZ).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex();

        Tessellator.getInstance().draw();
        GlStateManager.popMatrix();
        GlStateManager.pushAttrib();
    }
    public static void blockESPBox(BlockPos blockPos) {

        double x =
                blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        double y =
                blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        double z =
                blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(width);
        GL11.glColor4d(0, 1, 0, 0.15F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        //drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glColor4d(0, 0, 1, 0.5F);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
