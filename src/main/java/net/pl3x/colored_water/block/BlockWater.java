package net.pl3x.colored_water.block;

import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap.Builder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pl3x.colored_water.fluid.FluidWater;

public class BlockWater extends BlockFluidClassic {
     public final EnumDyeColor dyeColor;

     public BlockWater(FluidWater fluid) {
          super(fluid, Material.WATER);
          this.setHardness(100.0F);
          this.setLightOpacity(fluid.getLuminosity() > 0 ? 0 : 3);
          this.setLightLevel((float)fluid.getLuminosity());
          this.disableStats();
          this.dyeColor = fluid.dyeColor;
          this.displacements.put(Blocks.WATER, false);
          this.displacements.put(Blocks.FLOWING_WATER, false);
          this.displacements.put(Blocks.LAVA, false);
          this.displacements.put(Blocks.FLOWING_LAVA, false);
          this.setRegistryName(this.fluidName);
          //this.setTranslationKey(this.getRegistryName().toString());
          ForgeRegistries.BLOCKS.register(this);
     }

     @SideOnly(Side.CLIENT)
     public void render() {
          ModelLoader.setCustomStateMapper(this, (new Builder()).ignore(new IProperty[]{LEVEL}).build());
     }

     public int getLightValue(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
          return this.maxScaledLight == 0 ? super.getLightValue(state, world, pos) : this.maxScaledLight - (Integer)state.getValue(LEVEL) * 2;
     }

     public float getFluidHeightForRender(IBlockAccess world, BlockPos pos, @Nonnull IBlockState up) {
          IBlockState here = world.getBlockState(pos);
          if (here.getBlock() == this) {
               if (up.getMaterial().isLiquid() || up.getBlock() instanceof IFluidBlock) {
                    return 1.0F;
               }

               if (this.getMetaFromState(here) == this.getMaxRenderHeightMeta()) {
                    return 0.8875F;
               }
          }

          if (!(here.getBlock() instanceof BlockLiquid) && !(here.getBlock() instanceof IFluidBlock)) {
               return !here.getMaterial().isSolid() && up.getBlock() == this ? 1.0F : this.getQuantaPercentage(world, pos);
          } else {
               return Math.min(1.0F - BlockLiquid.getLiquidHeightPercent((Integer)here.getValue(BlockLiquid.LEVEL)), 1.0F);
          }
     }

     public int getQuantaValue(IBlockAccess world, BlockPos pos) {
          IBlockState state = world.getBlockState(pos);
          Block block = state.getBlock();
          if (block == Blocks.AIR) {
               return 0;
          } else {
               return !(block instanceof BlockWater) && block != Blocks.WATER ? -1 : this.quantaPerBlock - (Integer)state.getValue(LEVEL);
          }
     }

     @SideOnly(Side.CLIENT)
     public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks) {
          if (this.getFluid() != null) {
               float f12 = 0.0F;
               if (entity instanceof EntityLivingBase) {
                    EntityLivingBase ent = (EntityLivingBase)entity;
                    f12 = (float)EnchantmentHelper.getRespirationModifier(ent) * 0.2F;
                    if (ent.isPotionActive(MobEffects.WATER_BREATHING)) {
                         f12 = f12 * 0.3F + 0.6F;
                    }
               }

               return new Vec3d((double)(0.02F + f12), (double)(0.02F + f12), (double)(0.2F + f12));
          } else {
               return super.getFogColor(world, pos, state, entity, originalColor, partialTicks);
          }
     }

     @SideOnly(Side.CLIENT)
     public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
          double x = (double)pos.getX();
          double y = (double)pos.getY();
          double z = (double)pos.getZ();
          int level = (Integer)state.getValue(LEVEL);
          if (level > 0 && level < 8) {
               if (rand.nextInt(64) == 0) {
                    world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
               }
          }
     }
}
