package me.Coderforlife.ControlAllMobs.Utils;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {

	public ChatUtils() {
		
	}
	/**
	 * @param s
	 * @return Send Console Command with Color Codes.
	 */
	public void SendConsoleMessage(String s) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
		return;
	}
	/**
	 * 
	 * @param s
	 * @return BroadCast command with Color Codes.
	 */
	public void BroadCastMessage(String s) {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s));
		return;
	}
}
