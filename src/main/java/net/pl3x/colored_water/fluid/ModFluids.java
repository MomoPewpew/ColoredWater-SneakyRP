package net.pl3x.colored_water.fluid;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.item.EnumDyeColor;

public class ModFluids {
     public static final Set __FLUIDS__ = new HashSet();

     public static void registerFluids() {
          EnumDyeColor[] var0 = EnumDyeColor.values();
          int var1 = var0.length;

          for(int var2 = 0; var2 < var1; ++var2) {
               EnumDyeColor color = var0[var2];
               __FLUIDS__.add(new FluidWater(color, 0));
               __FLUIDS__.add(new FluidWater(color, 15));
          }

     }
}
