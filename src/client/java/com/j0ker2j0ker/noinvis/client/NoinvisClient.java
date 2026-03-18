package com.j0ker2j0ker.noinvis.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.network.chat.Component;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.literal;

public class NoinvisClient implements ClientModInitializer {

    public static InvisConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = InvisConfig.load();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    literal("invis").executes(context -> {
                        CONFIG.noInvis = !CONFIG.noInvis;

                        context.getSource().sendFeedback(
                                Component.literal("§oEverything is " + (CONFIG.noInvis ? "now visible." : "back to normal."))
                        );
                        return 1;
                    })
            );
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            CONFIG.save();
        });
    }
}