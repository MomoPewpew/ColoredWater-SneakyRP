package net.pl3x.colored_water;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.pl3x.colored_water.proxy.ServerProxy;

@Mod(
     modid = "colored_water",
     name = "ColorfulWater",
     version = "1.12.2-b37",
     updateJSON = "http://pl3x.net/versions/colored_water.json",
     acceptedMinecraftVersions = "[1.12.2]"
)
public class ColoredWater {
     public static final String modId = "colored_water";
     public static final String name = "ColorfulWater";
     public static final String version = "1.12.2-b38";
     @SidedProxy(
          serverSide = "net.pl3x.colored_water.proxy.ServerProxy",
          clientSide = "net.pl3x.colored_water.proxy.ClientProxy"
     )
     public static ServerProxy proxy;

     @EventHandler
     public void preInit(FMLPreInitializationEvent event) {
          proxy.preInit(event);
     }

     @EventHandler
     public void init(FMLInitializationEvent event) {
          proxy.init(event);
     }

     static {
          FluidRegistry.enableUniversalBucket();
     }
}
