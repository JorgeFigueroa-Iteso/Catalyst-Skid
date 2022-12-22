package com.krazzzzymonkey.catalyst.managers;

import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

import java.util.ArrayList;

public class ChatMentionManager {

    public static ArrayList<String> mentionList = new ArrayList<String>();

    public static void addMention(String word) {
        if(!mentionList.contains(word)) {
            mentionList.add(word);
            FileManager.saveChatMention();
            ChatUtils.message((ChatColor.AQUA + word + ChatColor.GREEN + " Added " + ChatColor.GRAY + "to mention list."));
        }
        else {
            ChatUtils.error("Word already in mention list!");
        }
    }

    public static void removeMention(String word) {
        if(mentionList.contains(word)) {
            //mentionList.remove(word + " ");
            mentionList.remove(word);
            FileManager.saveChatMention();
            ChatUtils.message(ChatColor.AQUA + word + ChatColor.RED + " Removed " + ChatColor.GRAY + "from mention list.");
        } else {
            ChatUtils.error("Word not found in mention list!");
        }
    }

    public static void listMention() {
        ChatUtils.normalMessage(ChatColor.GRAY + "Mention list:");
        for(String word : mentionList) {
            ChatUtils.normalMessage(ChatColor.RED + word);
        }
    }

    public static void clear() {
        if(!mentionList.isEmpty()) {
            mentionList.clear();
            FileManager.saveChatMention();
            ChatUtils.message("\u00a7bMention \u00a77list cleared.");
        }
    }

    public static ArrayList<String> getMentionList(){
        return mentionList;
    }
}
