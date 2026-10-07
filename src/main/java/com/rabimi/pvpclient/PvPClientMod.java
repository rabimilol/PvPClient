package com.rabimi.pvpclient;

import com.rabimi.pvpclient.hud.CpsHudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PvPClientMod implements ClientModInitializer {
    public static final String MOD_ID = "qual_client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Qual Client...");

        boolean isVulkanModLoaded = FabricLoader.getInstance().isModLoaded("vulkanmod");
        boolean isSodiumLoaded = FabricLoader.getInstance().isModLoaded("sodium");

        if (isVulkanModLoaded) {
            LOGGER.info("[PvP Client] VulkanMod detected! Skipping Sodium injection/recommendation to prevent conflict.");
        } else if (!isSodiumLoaded) {
            LOGGER.warn("==========================================================");
            LOGGER.warn("[PvP Client] Sodium is NOT installed!");
            LOGGER.warn("[PvP Client] Please install Sodium for optimal performance.");
            LOGGER.warn("==========================================================");
        } else {
            LOGGER.info("[PvP Client] Sodium is active and ready.");
        }

        HudRenderCallback.EVENT.register(new CpsHudOverlay());
    }
}
