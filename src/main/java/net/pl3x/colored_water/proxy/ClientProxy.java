package net.pl3x.colored_water.proxy;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pl3x.colored_water.block.BlockWater;
import net.pl3x.colored_water.block.ModBlocks;
import net.pl3x.colored_water.util.Overlay;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy {
     public void preInit(FMLPreInitializationEvent event) {
          super.preInit(event);
          ModBlocks.renderBlocks();
     }

     public void init(FMLInitializationEvent event) {
          super.init(event);
          MinecraftForge.EVENT_BUS.register(this);
     }

     @SubscribeEvent
     public void on(RenderBlockOverlayEvent event) {
          if (event.getOverlayType() == OverlayType.WATER) {
               if (event.getBlockForOverlay()== Blocks.WATER) {
                    Block blockHead = event.getPlayer().getEntityWorld().getBlockState(event.getBlockPos().up()).getBlock();
                    event.setCanceled(true);
                    Overlay.renderWaterOverlayTexture(blockHead instanceof BlockWater ? ((BlockWater)blockHead).dyeColor : null);
               }
          }
     }
}
