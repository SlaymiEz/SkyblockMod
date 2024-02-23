package fr.slaymi;



import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;
import java.util.ArrayList;
import java.util.List;


public class ChestTracer {
    public static Thread t = new Thread(ChestTracer::debugChestCheck);
    public ChestTracer(){
        t.start();
    }
    public static List<BlockPos> chestPositions = new ArrayList<>();
    public static List<IBlockState> openedChests = new ArrayList<>();
    public static int SEARCH_RADIUS = 15;
    public static boolean actived = false;
    public static boolean autoChest = false;
    public static float width = (float) 8.0;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private final ChestGUI chestText = new ChestGUI();
    public static int drillSlot = 1;
    public static int pickSlot = 0;

    public static int nearbyChestsPublic = 0;

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event){
        renderESP(event.partialTicks);
    }
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event){
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (actived) {
            chestText.drawScreen(scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), event.partialTicks);
        }
    }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        chestText.updateScreen();
        if (autoChest) {
            checkChest();
            checkStone();
        }
    }

    private static void debugChestCheck(){
        while (true){
            try {
                Thread.sleep(1000);
                openedChests.clear();
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    private static void checkStone() {
        MovingObjectPosition target = Minecraft.getMinecraft().objectMouseOver;
        if (actived) {
            if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                if (Minecraft.getMinecraft().theWorld.getBlockState(target.getBlockPos()).getBlock() instanceof BlockStone) {
                    mc.thePlayer.inventory.currentItem = pickSlot;
                }
            }
        }
    }
    private static void checkChest(){
        MovingObjectPosition target = Minecraft.getMinecraft().objectMouseOver;
        if (actived) {
            if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                if (Minecraft.getMinecraft().theWorld.getBlockState(target.getBlockPos()).getBlock() instanceof BlockChest) {
                    if (!openedChests.contains(mc.theWorld.getBlockState(target.getBlockPos()))) {
                        Minecraft.getMinecraft().thePlayer.inventory.currentItem = drillSlot;
                        KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());
                        openedChests.add(mc.theWorld.getBlockState(target.getBlockPos()));
                    }
                }
            }
        }
    }

    public static void renderESP(float partialTick){
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        int nearbyChests = 0;
        chestPositions.clear();
        if (actived) {
            for (int x = (int) player.posX - SEARCH_RADIUS; x <= (int) player.posX + SEARCH_RADIUS; x++) {
                for (int y = (int) player.posY - SEARCH_RADIUS; y <= (int) player.posY + SEARCH_RADIUS; y++) {
                    for (int z = (int) player.posZ - SEARCH_RADIUS; z <= (int) player.posZ + SEARCH_RADIUS; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock() instanceof BlockChest) {
                            if (!chestPositions.contains(pos)) chestPositions.add(pos);
                            if (!ChestCommand.blPos.contains(pos)) {
                                blockESPBox(pos);
                                //renderTracer(player, x, y, z);
                                nearbyChests += 1;
                            }
                        }
                    }
                }
            }
        }
        nearbyChestsPublic = nearbyChests;
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
