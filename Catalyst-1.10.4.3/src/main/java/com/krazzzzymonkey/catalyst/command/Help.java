package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.CommandManager;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

public class Help extends Command {
    public Help() {
        super("help");
    }

    @Override
    public void runCommand(String s, String[] args) {
        if (args[0].length() == 0) {
            ChatUtils.normalChat(" ");
            ChatUtils.normalChat(ChatColor.GREEN + "<============= " + ChatColor.WHITE + Main.NAME + ChatColor.GREEN + " =============>");
            for (Command cmd : CommandManager.getInstance().getCommands()) {
                ChatUtils.normalChat(ChatColor.DARK_PURPLE + " - " + cmd.getSyntax().replace("<", ChatColor.GRAY + "<" + ChatColor.LIGHT_PURPLE).replace(">", "\2477>")
                        .replace("[", ChatColor.GRAY + "[" + ChatColor.DARK_GREEN).replace("]", "\2477]"));
                ChatUtils.normalChat(ChatColor.GRAY + "   ➥ " + cmd.getDescription());
            }
            ChatUtils.normalChat(ChatColor.GREEN + "<====================================>");
            ChatUtils.normalChat(" ");
        } else {
            for(Command hack : CommandManager.getInstance().getCommands()) {
                if(args[0].equalsIgnoreCase(hack.getCommand())) {
                    ChatUtils.normalChat(ChatColor.GRAY+"["+ ChatColor.LIGHT_PURPLE + "HELP" + ChatColor.GRAY + "] " +
                            ChatColor.DARK_PURPLE + hack.getSyntax().replace("<", ChatColor.GRAY + "<" + ChatColor.LIGHT_PURPLE)
                            .replace(">", "\2477>")
                        .replace("[", ChatColor.GRAY + "[" + ChatColor.DARK_GREEN).replace("]", "\2477]"));
                    ChatUtils.normalChat(ChatColor.GRAY + "          ➥ " + hack.getDescription());
                    return;
                }
            }ChatUtils.normalMessage("Module " + args[0] + " not found.");
        }
    }

    @Override
    public String getDescription() {
        return "Get help / Show syntax from a command.";
    }

    @Override
    public String getSyntax() {
        return "help";
    }
}
