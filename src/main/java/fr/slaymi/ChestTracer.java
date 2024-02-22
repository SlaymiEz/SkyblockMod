package fr.slaymi;



import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;


public class ChestTracer {
    public static List<BlockPos> chestPositions = new ArrayList<>();
    public static final int SEARCH_RADIUS = 15;
    public static boolean actived = false;
    public static float width = (float) 8.0;
    private static Minecraft mc = Minecraft.getMinecraft();
    private ChestGUI chestText = new ChestGUI();

    public static int nearbyChestsPublic = 0;

    private static int nearbyChests = 0;
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event){
        renderESP(event.partialTicks);
    }
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event){
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (actived == true) {
            chestText.drawScreen(scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), event.partialTicks);
        }
    }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        chestText.updateScreen();
    }

    public static void renderESP(float partialTick){
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        nearbyChests = 0;
        chestPositions.clear();
        for (int x = (int) player.posX - SEARCH_RADIUS; x <= (int) player.posX + SEARCH_RADIUS; x++){
            for(int y = (int) player.posY - SEARCH_RADIUS; y <= (int) player.posY + SEARCH_RADIUS; y++) {
                for (int z = (int) player.posZ - SEARCH_RADIUS; z <= (int) player.posZ + SEARCH_RADIUS; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock() instanceof BlockChest) {
                        if (actived == true) {
                            if (!chestPositions.contains(pos)) chestPositions.add(pos);
                            if (!ChestCommand.blPos.contains(pos)) {
                                blockESPBox(pos);
                                nearbyChests += 1;
                            }
                        }
                    }
                }
            }
        }
        nearbyChestsPublic = nearbyChests;
    }
    private static void renderTracer(EntityPlayer player, double chestX, double chestY, double chestZ){
        GL11.glPushMatrix();
        GL11.glTranslated(mc.getRenderManager().viewerPosX, mc.getRenderManager().viewerPosY, mc.getRenderManager().viewerPosZ);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(width);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);

        
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
