package com.example.mod.discord;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import java.time.Instant;

public class RPC {
    private Core discordCore;

    public RPC() {
        try (CreateParams params = new CreateParams()) {
            params.setClientID(1138189932679155813L);
            params.setFlags(CreateParams.getDefaultFlags());
            discordCore = new Core(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDiscordActivity() {
        if (discordCore != null) {
            try (Activity activity = new Activity()) {
                activity.setDetails("Running an example");
                activity.setState("and having fun");
                activity.timestamps().setStart(Instant.now());
                activity.party().size().setMaxSize(100);
                activity.party().size().setCurrentSize(10);
                activity.assets().setLargeImage("logo");
                activity.party().setID("Party!");
                activity.secrets().setJoinSecret("Join!");
                discordCore.activityManager().updateActivity(activity);
            }
            while (true) {
                discordCore.runCallbacks();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
