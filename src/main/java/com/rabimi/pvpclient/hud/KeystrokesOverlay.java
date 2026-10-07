package com.rabimi.pvpclient.hud;

import com.rabimi.pvpclient.config.ClientConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderTickCounter;

public class KeystrokesOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (!ClientConfig.enableKeystrokes) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        int x = 10;
        int y = 40;

        KeyBinding forward = client.options.forwardKey;
        KeyBinding left = client.options.leftKey;
        KeyBinding back = client.options.backKey;
        KeyBinding right = client.options.rightKey;
        KeyBinding attack = client.options.attackKey;
        KeyBinding use = client.options.useKey;

        drawKey(drawContext, client, "W", x + 22, y, 20, 20, forward.isPressed());
        drawKey(drawContext, client, "A", x, y + 22, 20, 20, left.isPressed());
        drawKey(drawContext, client, "S", x + 22, y + 22, 20, 20, back.isPressed());
        drawKey(drawContext, client, "D", x + 44, y + 22, 20, 20, right.isPressed());

        drawKey(drawContext, client, "LMB", x, y + 44, 31, 20, attack.isPressed());
        drawKey(drawContext, client, "RMB", x + 33, y + 44, 31, 20, use.isPressed());
    }

    private void drawKey(DrawContext context, MinecraftClient client, String text, int x, int y, int width, int height, boolean pressed) {
        int color = pressed ? 0x80FFFFFF : 0x80000000; // 押されているときは半透明白、通常は半透明黒
        int textColor = pressed ? 0x000000 : 0xFFFFFF;

        context.fill(x, y, x + width, y + height, color);

        int textWidth = client.textRenderer.getWidth(text);
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - 8) / 2;
        context.drawText(client.textRenderer, text, textX, textY, textColor, false);
    }
}
