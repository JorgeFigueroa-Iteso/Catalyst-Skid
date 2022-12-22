package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

public class Modules extends Command
{
	public Modules()
	{
		super("modules");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		for(com.krazzzzymonkey.catalyst.module.Modules hack : ModuleManager.getModules()) {
			ChatUtils.normalMessage(String.format("%s \u00a7a| \u00a7f%s \u00a7a| \u00a7f%s \u00a7a| \u00a7f%s", hack.getModuleName(), hack.getCategory(), hack.getKey(), hack.isToggled()));
		}
		ChatUtils.normalChat("Loaded " + ModuleManager.getModules().size() + " Modules.");
	}

	@Override
	public String getDescription()
	{
		return "Lists all hacks.";
	}

	@Override
	public String getSyntax()
	{
		return "modules";
	}
}
