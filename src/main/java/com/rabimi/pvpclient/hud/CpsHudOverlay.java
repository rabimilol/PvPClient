package com.rabimi.pvpclient.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import java.util.ArrayList;
import java.util.List;

public class CpsHudOverlay implements HudRenderCallback {
    private static final List<Long> leftClicks = new ArrayList<>();

    public static void onLeftClick() {
        leftClicks.add(System.currentTimeMillis());
    }

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        long now = System.currentTimeMillis();
        leftClicks.removeIf(time -> now - time > 1000);
        int cps = leftClicks.size();

        String text = "CPS: " + cps;
        drawContext.drawText(
                client.textRenderer,
                text,
                10, 10,
                0xFFFFFF,
                true
        );
    }
}
