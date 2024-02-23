package fr.slaymi;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "slaymimod", useMetadata=true)
public class SlaymiMod {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new Waypoint());
        ClientCommandHandler.instance.registerCommand(new ChestCommand());
        //MinecraftForge.EVENT_BUS.register(new MyEventHandlerClass());
        //MinecraftForge.EVENT_BUS.register(new TestTessellator());
        MinecraftForge.EVENT_BUS.register(new ChestTracer());
        //MinecraftForge.EVENT_BUS.register(new VertexTest());
        MinecraftForge.EVENT_BUS.register(this);
    }
}