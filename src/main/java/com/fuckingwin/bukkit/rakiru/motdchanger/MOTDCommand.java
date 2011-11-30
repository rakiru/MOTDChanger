package com.fuckingwin.bukkit.rakiru.motdchanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author rakiru
 */
public class MOTDCommand implements CommandExecutor {

	private final MOTDChangerPlugin plugin;

	public MOTDCommand(MOTDChangerPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Show usage if no arguments given
		if (args.length == 0) {
			return false;
		}

		//Time flag used?
		if (args[0].equals("-t")) {
			//Time flag used but no time and/or MOTD
			if (args.length < 3) {
				return false;
			}
			//Parse input time and return if not valid
			try {
				int duration = Integer.parseInt(args[1]);
				if (duration <= 0) {
					sender.sendMessage("Duration must be greater than 0");
					return false;
				}
				plugin.setDuration(duration);
			} catch(NumberFormatException e) {
				sender.sendMessage(args[1] + "is not a valid length of time");
				return false;
			}
			//Join up the rest of the arguments to use as the new MOTD
			plugin.setTempMOTD(joinStringArray(args, 2));
			//Set the start time
			plugin.setStartTime();
			return true;
		} else {
			plugin.setRealMOTD(joinStringArray(args, 0));
			return true;
		}
	}

	public static String joinStringArray(String[] array, int start) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = start; i < array.length; i++) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append(' ');
			}
			stringBuilder.append(array[i]);
		}
		return stringBuilder.toString();
	}
}
