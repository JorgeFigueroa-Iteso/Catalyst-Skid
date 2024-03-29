package com.krazzzzymonkey.catalyst.managers;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

import java.util.ArrayList;

public class FriendManager {

	public static ArrayList<String> friendsList = new ArrayList<String>();

	public static void addFriend(String friendname) {
		if(!friendsList.contains(friendname)) {
			friendsList.add(friendname);
			FileManager.saveFriends();

			ChatUtils.message(friendname + " has been \u00A7aadded\u00A77 to friend list!");
		}
	}

    public static void addFriendList(String friendname) {
        if(!friendsList.contains(friendname)) {
            friendsList.add(friendname);
            FileManager.saveFriends();
        }
    }

	public static void removeFriend(String friendname) {
		if(friendsList.contains(friendname)) {
			friendsList.remove(friendname);
			FileManager.saveFriends();
			ChatUtils.message(friendname + " has been \u00A7cremoved\u00A77 from friend list!");
		}
	}

	public static void clear() {
		if(!friendsList.isEmpty()) {
			friendsList.clear();
			FileManager.saveFriends();
			ChatUtils.message("\u00a7dFriends \u00a77list cleared.");
		}
	}

    public static void friendList() {
        if(!friendsList.isEmpty()) {
            ChatUtils.normalMessage("\u00a7dFriends \u00a77list:");
            for(String friend : friendsList) {
                ChatUtils.normalMessage(friend);
            }
        } else {
            //                      "Cannot resolve internal command:        |
            ChatUtils.message("You have \u00a7c0 \u00a77friends :(" +
                                    "                                " +
                                    "Add me as a friend! :D - \u00a7aReimound\u00a77.");
        }
    }
}
