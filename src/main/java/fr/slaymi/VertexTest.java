package fr.slaymi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.tools.nsc.doc.model.Def;

public class VertexTest {
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Tessellator tessy = Tessellator.getInstance();
        //BufferBuilder buf = tessy.getWorldRenderer().getByteBuffer();
    }

}
