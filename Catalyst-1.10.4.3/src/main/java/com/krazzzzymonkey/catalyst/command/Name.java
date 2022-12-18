package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

public class Name extends Command
{
	public Name()
	{
		super("name");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		for(Modules hack : ModuleManager.getModules()) {
			if(args[0].equalsIgnoreCase(hack.getModuleName())) {
				ChatUtils.normalMessage(String.format("%s \u00a7a| \u00a7f%s \u00a7a| \u00a7f%s \u00a7a| \u00a7f%s", hack.getModuleName(), hack.getCategory(), hack.getKey(), hack.isToggled()));
				return;
			}
		}
		ChatUtils.normalMessage("Module " + args[0] + " not found.");
	}

	@Override
	public String getDescription()
	{
		return "Get the name of a module.";
	}

	@Override
	public String getSyntax()
	{
		return "name [module]";
	}
}
