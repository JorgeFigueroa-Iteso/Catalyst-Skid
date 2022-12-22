package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.managers.FileManager;
import com.krazzzzymonkey.catalyst.managers.ProfileManager;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Coords extends Command {
    public Coords() {
        super("coords");
    }

    /**
     * Todo: Add coord's of other players
     * @param s
     * @param args player specified
     */

    @Override
    public void runCommand(String s, String[] args) {

    String coords = "X: " + (int)mc.player.posX + " Y: " + (int)mc.player.posY + " Z: " + (int)mc.player.posZ;
    StringSelection stringSelection = new StringSelection(coords);
    Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
    ChatUtils.message(ChatColor.GREEN + "Copied coords to clipboard: " + ChatColor.GRAY + coords);

    }

    @Override
    public String getDescription() {
        return "Copy coords to clipboard";
    }

    @Override
    public String getSyntax() {
        return "coords";
    }
}
