package org.lichtspiele.ConfirmService.api;

import java.util.HashMap;

import org.bukkit.command.CommandSender;

public class ConfirmRegistry {
	
	/*
	 * implicit synchronized singleton
	 */
	private static HashMap<CommandSender,ConfirmInterface> confirm
		= new HashMap<CommandSender,ConfirmInterface>();

	private static final class InstanceHolder {
		static final ConfirmRegistry INSTANCE = new ConfirmRegistry();
	}

	/*
	 * private constructor
	 */
	private ConfirmRegistry() {}
	
	/*
	 * instance methods
	 */
	public static ConfirmRegistry getInstance () {
		return InstanceHolder.INSTANCE;
	}

	public void put(CommandSender sender, ConfirmInterface command) {
		confirm.put(sender, command);
	}
	
	public boolean has(CommandSender sender) {
		return confirm.containsKey(sender);
	}

	public ConfirmInterface get(CommandSender sender) {
		return confirm.get(sender);
	}

	public void remove(CommandSender sender) {
		confirm.remove(sender);
	}	
}
