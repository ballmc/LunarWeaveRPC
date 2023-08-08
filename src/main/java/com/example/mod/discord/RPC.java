package com.example.mod.discord;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.minecraft.client.Minecraft;

public class RPC {
    private Core discordCore;
    private ExecutorService executorService;

    public RPC() {
        try (CreateParams params = new CreateParams()) {
            // 1138189932679155813 with L at end
            params.setClientID(1138189932679155813L);
            params.setFlags(CreateParams.getDefaultFlags());
            discordCore = new Core(params);
            executorService = Executors.newSingleThreadExecutor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startUpdatingDiscordActivity() {
        executorService.execute(() -> {
            if (discordCore != null) {
                try (Activity activity = new Activity()) {
                    String minecraftVersion = Minecraft.getMinecraft().getVersion();
                    activity.setState("Playing Minecraft " + minecraftVersion);
                    activity.timestamps().setStart(Instant.now());
                    // This is what the lunar client logo is called on my discord dev portal
                    // application
                    activity.assets().setLargeImage("logo");
                    discordCore.activityManager().updateActivity(activity);
                }
            }
        });
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
