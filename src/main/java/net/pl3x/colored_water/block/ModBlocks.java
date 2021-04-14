package net.pl3x.colored_water.block;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.pl3x.colored_water.fluid.FluidWater;
import net.pl3x.colored_water.fluid.ModFluids;

public class ModBlocks {
     public static final Set<BlockWater> __BLOCKS__ = new HashSet();

     public static void registerBlocks() {
          Iterator var0 = ModFluids.__FLUIDS__.iterator();

          while(var0.hasNext()) {
               FluidWater fluid = (FluidWater)var0.next();
               __BLOCKS__.add(new BlockWater(fluid));
          }

     }

     public static void renderBlocks() {
          __BLOCKS__.forEach(BlockWater::render);
     }
}
