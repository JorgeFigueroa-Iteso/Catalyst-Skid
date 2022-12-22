package com.krazzzzymonkey.catalyst;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;
import net.minecraft.client.Minecraft;

import static com.krazzzzymonkey.catalyst.module.modules.misc.RPC.showState;

public class CatalystRPC {
    private static final String ClientId = "900473855485833216";
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
    public static DiscordRichPresence presence = new DiscordRichPresence();
    public static DiscordUser user = new DiscordUser();
    private static String details;
    private static String state;

    public static void init(){
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.disconnected = ((var1, var2) -> Main.logger.info("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        rpc.Discord_Initialize(ClientId, handlers, true, "");
        presence.startTimestamp = System.currentTimeMillis() / 1000L;
        presence.state = "BuddyPlus Main Menu";
        presence.details = "Version " + Main.VERSION;
        presence.largeImageKey = "luna";
        presence.largeImageText = "BuddyPlus Client";
        presence.partyId = "ae488379-351d-4a4f-ad32-2b9b01c91657";
        presence.partySize = 1;
        presence.partyMax = 5;
        presence.joinSecret = "MTI4NzM0OjFpMmhuZToxMjMxMjM= ";

        rpc.Discord_UpdatePresence(presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    rpc.Discord_RunCallbacks();
                    details = "Version " + Main.VERSION;
                    state = "";
                    if (mc.isIntegratedServerRunning()) {
                        state = "Playing Singleplayer";
                    }
                    else if (mc.getCurrentServerData() != null) {
                        if (!mc.getCurrentServerData().serverIP.equals("")) {
                            if (showState.getValue()) {
                                state = "Playing on " + mc.getCurrentServerData().serverIP;
                            } else {
                                state = "Playing Multiplayer";
                            }
                        }

                    } else {
                        state = "Main Menu";
                    }
                    if (!details.equals(presence.details) || !state.equals(presence.state)) {
                        presence.startTimestamp = System.currentTimeMillis() / 1000L;
                    }
                    presence.details = details;
                    presence.state = state;
                    rpc.Discord_UpdatePresence(presence);
                } catch(Exception e2){
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                } catch(InterruptedException e3){
                    e3.printStackTrace();
                }
            }
        }, "Discord-RPC-Callback-Handler").start();
    }
}
