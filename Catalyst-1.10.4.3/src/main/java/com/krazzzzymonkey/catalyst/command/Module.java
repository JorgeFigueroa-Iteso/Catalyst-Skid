package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

public class Module extends Command
{
	public Module()
	{
		super("module");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
        // Search for the module in Modules
        com.krazzzzymonkey.catalyst.module.Modules module = ModuleManager.getModules().stream().filter(hack -> hack.getModuleName().equalsIgnoreCase(args[0])).findFirst().orElse(null);

        if (args[0].isEmpty()) {
            ChatUtils.error("Please specify a module!");
        } else
        if (module == null) {
            ChatUtils.error("Module " + args[0] + " not found!");
        } else {
            ChatUtils.normalMessage(String.format("%s \u00a7a| \u00a7f%s \u00a7a| \u00a7f%s \u00a7a| \u00a7f%s", module.getModuleName(), module.getCategory(), module.getKey(), module.isToggled()));
        }
	}

	@Override
	public String getDescription()
	{
		return "Show's module config.";
	}

	@Override
	public String getSyntax()
	{
		return "module [module]";
	}
}
