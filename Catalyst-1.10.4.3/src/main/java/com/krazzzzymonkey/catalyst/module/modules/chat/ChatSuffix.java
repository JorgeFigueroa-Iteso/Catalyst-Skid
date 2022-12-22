package com.krazzzzymonkey.catalyst.module.modules.chat;


import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.SubMenu;
import dev.tigr.simpleevents.listener.EventHandler;
import dev.tigr.simpleevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class ChatSuffix extends Modules {

    public BooleanValue normal = new BooleanValue("NormalText", false, "It sends the suffix in normal characters");
    public BooleanValue addChars = new BooleanValue("AddChars", false, "Adds random unicode chars to the suffix");
    public BooleanValue blueText = new BooleanValue("BlueText", false, "Adds \"`\" to the beginning of the suffix making it blue on some severs");
    public BooleanValue customText = new BooleanValue("CustomText", false, "Change suffix to username");
    SubMenu mSubMenu = new SubMenu("Mode", customText, normal, addChars, blueText);

    public ChatSuffix() {
        super("ChatSuffix", ModuleCategory.CHAT, "Adds client name at the end of a chat message");
        this.addValue(mSubMenu);
    }

    @EventHandler
    private final EventListener<PacketEvent> onPacket = new EventListener<>(e -> {
        if(e.getSide() == PacketEvent.Side.OUT) {
            Packet packet = e.getPacket();
            if ((packet instanceof CPacketChatMessage)) {
                String decoChar = this.getRandomStringFromArray(deco);
                final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage) packet;
                if (!cPacketChatMessage.getMessage().startsWith("/")) {
                    String concat;
                    if (addChars.getValue()) { // Add random unicode chars to the suffix
                        if (!customText.getValue()){ // If customText is false
                            concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + toUnicode(Main.NAME) + " " + decoChar);
                        } else { // If customText is true
                            concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + toUnicode(mc.player.getName()) + " " + decoChar);
                        }
                    } else { // If addChars is false
                        if (!customText.getValue()){ // If customText is false
                            concat = cPacketChatMessage.getMessage().concat(" \u262A " + toUnicode(Main.NAME) + " \u262A");
                        } else { // If customText is true
                            concat = cPacketChatMessage.getMessage().concat(" \u262A " + toUnicode(mc.player.getName()) + " \u262A");
                        }
                    }

                    if (normal.getValue()){ // If normal is true
                        if (addChars.getValue()) { // Add random unicode chars to the suffix
                            if (!customText.getValue()){ // If customText is false
                                concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + Main.NAME + " " + decoChar);
                            } else { // If customText is true
                                concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + mc.player.getName() + " " + decoChar);
                            }
                        } else { // If addChars is false
                            if (!customText.getValue()){ // If customText is false
                                concat = cPacketChatMessage.getMessage().concat(" \u262A " + Main.NAME + " \u262A");
                            } else { // If customText is true
                                concat = cPacketChatMessage.getMessage().concat(" \u262A " + mc.player.getName() + " \u262A");
                            }
                        }
                    }
                    if (blueText.getValue()) {
                        if (normal.getValue()){ // If normal is true
                            if (addChars.getValue()) { // Add random unicode chars to the suffix
                                if (!customText.getValue()){ // If customText is false
                                    concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + Main.NAME + " " + decoChar);
                                } else { // If customText is true
                                    concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + mc.player.getName() + " " + decoChar);
                                }
                            } else { // If addChars is false
                                if (!customText.getValue()){ // If customText is false
                                    concat = cPacketChatMessage.getMessage().concat(" \u262A " + Main.NAME + " \u262A");
                                } else { // If customText is true
                                    concat = cPacketChatMessage.getMessage().concat(" \u262A " + mc.player.getName() + " \u262A");
                                }
                            }
                        } else { // NormalText Disabled
                            if (addChars.getValue()) { // Add random unicode chars to the suffix
                                if (!customText.getValue()){ // If customText is false
                                    concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + toUnicode(Main.NAME) + " " + decoChar);
                                } else { // If customText is true
                                    concat = cPacketChatMessage.getMessage().concat("   " + decoChar + " " + toUnicode(mc.player.getName()) + " " + decoChar);
                                }
                            } else { // If addChars is false
                                if (!customText.getValue()){ // If customText is false
                                    concat = cPacketChatMessage.getMessage().concat(" \u262A " + toUnicode(Main.NAME) + " \u262A");
                                } else { // If customText is true
                                    concat = cPacketChatMessage.getMessage().concat(" \u262A " + toUnicode(mc.player.getName()) + " \u262A");
                                }
                            }
                        }
                    }
                    cPacketChatMessage.message = concat;
                }
            }
        }
    });

    private String getRandomStringFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

    private static final String[] latinSmallLetter = new String[]{
        "\u1D00", // A
        "\u0299", // B
        "\u1D04", // C
        "\u1D05", // D
        "\u1D07", // E
        "\uA730", // F
        "\u0262", // G
        "\u029C", // H
        "\u026A", // I
        "\u1D0A", // J
        "\u1D0B", // K
        "\u029F", // L
        "\u1D0D", // M
        "\u0274", // N
        "\u1D0F", // O
        "\u1D18", // P
        "\u0051", // Q
        "\u0280", // R
        "\uA731", // S
        "\u1D1B", // T
        "\u1D1C", // U
        "\u1D20", // V
        "\u1D21", // W
        "\u1D22", // X
        "\u028F", // Y
        "\u1D26"  // Z
    };

    //toUnicode set the text to unicode
    public static String toUnicode(String string) {
        String unicode = "";
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                unicode += latinSmallLetter[c - 'A'];
            } else if (c >= 'a' && c <= 'z') {
                unicode += latinSmallLetter[c - 'a'];
            } else {
                unicode += c;
            }
        }
        return unicode;
    }

    private static final String[] deco = new String[]{"\u2622", "\u2623", "\u2620", "\u26A0", "\u2624", "\u269A", "\u2020", "\u262F", "\u262E", "\u2698", "\u271E", "\u271F", "\u2727", "\u2661", "\u2665", "\u2764", "\u266B", "\u266A", "\u2655", "\u265B", "\u2656", "\u265C", "\u2601", "\u2708","\u2709", "\u270A", "\u270B", "\u270C", "\u270D", "\u270E", "\u270F", "\u2710", "\u2711", "\u2712", "\u2713", "\u2714", "\u2715", "\u2716", "\u2717", "\u2718", "\u2719", "\u271A", "\u271B", "\u271C", "\u271D", "\u271E", "\u271F", "\u2720", "\u2721", "\u2722", "\u2723", "\u2724", "\u2725", "\u2726", "\u2727", "\u2728", "\u2729", "\u272A", "\u272B", "\u272C", "\u272D", "\u272E", "\u272F", "\u2730", "\u2731", "\u2732", "\u2733", "\u2734", "\u2735", "\u2736", "\u2737", "\u2738", "\u2739", "\u273A", "\u273B", "\u273C", "\u273D", "\u273E", "\u273F", "\u2740", "\u2741", "\u2742", "\u2743", "\u2744", "\u2745", "\u2746", "\u2747", "\u2748", "\u2749", "\u274A", "\u274B", "\u274D", "\u274F", "\u2750", "\u2751", "\u2752", "\u2756", "\u2758", "\u2759", "\u275A", "\u275B", "\u275C", "\u275D", "\u275E", "\u2761", "\u2762", "\u2763", "\u2764", "\u2765", "\u2766", "\u2767", "\u2776", "\u2777", "\u2778", "\u2779", "\u277A", "\u277B", "\u277C", "\u277D", "\u277E", "\u277F", "\u2780", "\u2781",
            "\u238B"};
}
