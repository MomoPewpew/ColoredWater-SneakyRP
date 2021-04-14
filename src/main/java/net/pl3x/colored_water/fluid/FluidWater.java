package net.pl3x.colored_water.fluid;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidWater extends Fluid {
     public final EnumDyeColor dyeColor;

     public FluidWater(EnumDyeColor color, int luminosity) {
          super((luminosity > 0 ? "glowing_" : "") + "water_" + color.getName(), new ResourceLocation("colored_water", "fluids/water_still_" + color.getName()), new ResourceLocation("colored_water", "fluids/water_flow_" + color.getName()));
          this.dyeColor = color;
          this.setLuminosity(luminosity);
          FluidRegistry.registerFluid(this);
          FluidRegistry.addBucketForFluid(this);
     }
}
