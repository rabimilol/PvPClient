package com.example.pvpclient.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {

    public ConfigScreen() {
        super(Text.literal("PvP Client Settings"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = this.height / 2 - 60;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("CPS HUD: " + (ClientConfig.enableCps ? "ON" : "OFF")),
                button -> {
                    ClientConfig.enableCps = !ClientConfig.enableCps;
                    button.setMessage(Text.literal("CPS HUD: " + (ClientConfig.enableCps ? "ON" : "OFF")));
                }
        ).dimensions(centerX - 100, startY, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Keystrokes: " + (ClientConfig.enableKeystrokes ? "ON" : "OFF")),
                button -> {
                    ClientConfig.enableKeystrokes = !ClientConfig.enableKeystrokes;
                    button.setMessage(Text.literal("Keystrokes: " + (ClientConfig.enableKeystrokes ? "ON" : "OFF")));
                }
        ).dimensions(centerX - 100, startY + 30, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Show Self Nametag: " + (ClientConfig.enableSelfNametag ? "ON" : "OFF")),
                button -> {
                    ClientConfig.enableSelfNametag = !ClientConfig.enableSelfNametag;
                    button.setMessage(Text.literal("Show Self Nametag: " + (ClientConfig.enableSelfNametag ? "ON" : "OFF")));
                }
        ).dimensions(centerX - 100, startY + 60, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
