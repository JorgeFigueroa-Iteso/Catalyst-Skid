package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.managers.FileManager;
import com.krazzzzymonkey.catalyst.managers.ProfileManager;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

import java.io.File;
import java.util.Objects;

public class Save extends Command {
    public Save() {
        super("save");
    }

    @Override
    public void runCommand(String s, String[] args) {
        /** TODO:
         * Save the current profile.
         */
        String profileName = ProfileManager.currentProfile;
        FileManager.saveModules(profileName);
        ChatUtils.normalMessage("Successfully saved: " + ChatColor.AQUA + profileName + ChatColor.GRAY + ".");
    }

    @Override
    public String getDescription() {
        return "Save's current profile.";
    }

    @Override
    public String getSyntax() {
        return "save";
    }
}
