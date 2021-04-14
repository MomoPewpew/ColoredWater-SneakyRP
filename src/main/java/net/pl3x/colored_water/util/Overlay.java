package net.pl3x.colored_water.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Overlay {
     public static final Map COLORED_UNDERWATER_OVERLAY = new HashMap();
     public static final ResourceLocation VANILLA_UNDERWATER_OVERLAY = new ResourceLocation("minecraft", "textures/misc/underwater.png");

     @SideOnly(Side.CLIENT)
     public static void renderWaterOverlayTexture(EnumDyeColor color) {
          Minecraft mc = Minecraft.getMinecraft();
          ResourceLocation texture = color == null ? VANILLA_UNDERWATER_OVERLAY : (ResourceLocation)COLORED_UNDERWATER_OVERLAY.get(color);
          if (texture == null) {
               texture = VANILLA_UNDERWATER_OVERLAY;
          }

          mc.getTextureManager().bindTexture(texture);
          Tessellator tessellator = Tessellator.getInstance();
          BufferBuilder bufferbuilder = tessellator.getBuffer();
          float brightness = mc.thePlayer.getBrightness();
          GlStateManager.color(brightness, brightness, brightness, 0.5F);
          GlStateManager.enableBlend();
          GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
          GlStateManager.pushMatrix();
          float yaw = -mc.thePlayer.rotationYaw / 64.0F;
          float pitch = mc.thePlayer.rotationPitch / 64.0F;
          bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
          bufferbuilder.pos(-1.0D, -1.0D, -0.5D).tex((double)(4.0F + yaw), (double)(4.0F + pitch)).endVertex();
          bufferbuilder.pos(1.0D, -1.0D, -0.5D).tex((double)(0.0F + yaw), (double)(4.0F + pitch)).endVertex();
          bufferbuilder.pos(1.0D, 1.0D, -0.5D).tex((double)(0.0F + yaw), (double)(0.0F + pitch)).endVertex();
          bufferbuilder.pos(-1.0D, 1.0D, -0.5D).tex((double)(4.0F + yaw), (double)(0.0F + pitch)).endVertex();
          tessellator.draw();
          GlStateManager.popMatrix();
          GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
          GlStateManager.disableBlend();
     }

     static {
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.BLACK, new ResourceLocation("colored_water", "textures/misc/underwater_black.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.BLUE, new ResourceLocation("colored_water", "textures/misc/underwater_blue.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.BROWN, new ResourceLocation("colored_water", "textures/misc/underwater_brown.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.CYAN, new ResourceLocation("colored_water", "textures/misc/underwater_cyan.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.GRAY, new ResourceLocation("colored_water", "textures/misc/underwater_gray.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.GREEN, new ResourceLocation("colored_water", "textures/misc/underwater_green.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.LIGHT_BLUE, new ResourceLocation("colored_water", "textures/misc/underwater_light_blue.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.LIME, new ResourceLocation("colored_water", "textures/misc/underwater_lime.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.MAGENTA, new ResourceLocation("colored_water", "textures/misc/underwater_magenta.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.ORANGE, new ResourceLocation("colored_water", "textures/misc/underwater_orange.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.PINK, new ResourceLocation("colored_water", "textures/misc/underwater_pink.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.PURPLE, new ResourceLocation("colored_water", "textures/misc/underwater_purple.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.RED, new ResourceLocation("colored_water", "textures/misc/underwater_red.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.SILVER, new ResourceLocation("colored_water", "textures/misc/underwater_silver.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.WHITE, new ResourceLocation("colored_water", "textures/misc/underwater_white.png"));
          COLORED_UNDERWATER_OVERLAY.put(EnumDyeColor.YELLOW, new ResourceLocation("colored_water", "textures/misc/underwater_yellow.png"));
     }
}
