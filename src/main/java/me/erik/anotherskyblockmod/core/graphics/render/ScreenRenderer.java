package me.erik.anotherskyblockmod.core.graphics.render;

import me.erik.anotherskyblockmod.core.graphics.color.CustomColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class ScreenRenderer {
    
    private static final float scale = 1.0f;
    private static final float rotation = 0;
    private static final boolean mask = false;
    private static final boolean scissorTest = false;
    private static final Point drawingOrigin = new Point(0, 0);
    private static final Point transformationOrigin = new Point(0, 0);
    public static FontRenderer fontRenderer = null;
    public static Minecraft mc;
    public static ScaledResolution screen = null;
    private static boolean rendering = false;
    private static RenderItem itemRenderer = null;
    
    public static Point drawingOrigin() { return drawingOrigin; }
    
    public static void transformationOrigin(int x, int y) {
        transformationOrigin.x = x;
        transformationOrigin.y = y;
    }
    
    protected static Point transformationOrigin() {return transformationOrigin;}
    
    public static boolean isRendering() { return rendering; }
    
    public static void setRendering(boolean status) {
        rendering = status;
    }
    
    public static float getScale() { return scale; }
    
    public static float getRotation() { return rotation; }
    
    public static boolean isMasking() { return mask; }
    
    /**
     * refresh
     * Triggered by a slower loop(client tick), refresh
     * updates the screen resolution to match the window
     * size and sets the font renderer in until its ok.
     * Do not call this method from anywhere in the mod!
     */
    public static void refresh() {
        mc = Minecraft.getMinecraft();
        screen = new ScaledResolution(mc);
        if (fontRenderer == null) try {
            fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        } catch (Exception ignored) { } finally {
            if (fontRenderer != null) fontRenderer.onResourceManagerReload(mc.getResourceManager());
        }
        if (itemRenderer == null) itemRenderer = Minecraft.getMinecraft().getRenderItem();
    }
    
    /**
     * float drawString
     * Draws a string using the current fontRenderer
     * @param text      the text to render
     * @param x         x(from drawingOrigin) to render at
     * @param y         y(from drawingOrigin) to render at
     * @param color     the starting color to render(without codes, its basically the actual text's color)
     * @return the length of the rendered text in pixels(not taking scale into account)
     */
    public float drawString(String text, float x, float y, CustomColor color) {
        if (!rendering) return -1f;
        float f = fontRenderer.drawString(text, (int) (drawingOrigin.x + x), (int) (drawingOrigin.y + y), color.toInt());
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        return f;
    }
    
    public float drawCenteredString(String text, float x, float y, CustomColor color) {
        return drawString(text, x, y, color);
    }
    
    public void color(CustomColor color) {
        color.applyColor();
    }
    
    public void color(float r, float g, float b, float alpha) {
        GlStateManager.color(r, g, b, alpha);
    }
    
    public void drawItemStack(ItemStack is, int x, int y) {
        drawItemStack(is, x, y, false, "", true);
    }
    
    public void drawItemStack(ItemStack is, int x, int y, boolean count) {
        drawItemStack(is, x, y, count, "", true);
    }
    
    public void drawItemStack(ItemStack is, int x, int y, boolean count, boolean effects) {
        drawItemStack(is, x, y, count, "", effects);
    }
    
    public void drawItemStack(ItemStack is, int x, int y, String text) {
        drawItemStack(is, x, y, false, text, true);
    }
    
    public void drawItemStack(ItemStack is, int x, int y, String text, boolean effects) {
        drawItemStack(is, x, y, false, text, effects);
    }
    
    /**
     * drawItemStack
     * Draws an item
     * @param is      the itemstack to render
     * @param x       x on screen
     * @param y       y on screen
     * @param count   show numbers
     * @param text    custom text
     * @param effects show shimmer
     */
    private void drawItemStack(ItemStack is, int x, int y, boolean count, String text, boolean effects) {
        if (!rendering) return;
        RenderHelper.enableGUIStandardItemLighting();
        itemRenderer.zLevel = 200.0F;
        net.minecraft.client.gui.FontRenderer font = is.getItem().getFontRenderer(is);
        if (font == null) font = fontRenderer;
        if (effects) itemRenderer.renderItemAndEffectIntoGUI(is, x + drawingOrigin.x, y + drawingOrigin.y);
        else itemRenderer.renderItemIntoGUI(is, x + drawingOrigin.x, y + drawingOrigin.y);
        itemRenderer.renderItemOverlayIntoGUI(font, is, x + drawingOrigin.x, y + drawingOrigin.y,
                text.isEmpty() ? count ? Integer.toString(is.stackSize) : null : text);
        itemRenderer.zLevel = 0.0F;
        RenderHelper.disableStandardItemLighting();
    }
    
}
