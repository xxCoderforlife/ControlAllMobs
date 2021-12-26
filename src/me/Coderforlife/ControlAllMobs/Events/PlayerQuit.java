package me.Coderforlife.ControlAllMobs.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.Messages;

public class PlayerQuit implements Listener {

	private Main plugin;

	public PlayerQuit(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	Messages m = new Messages();
	@EventHandler
	public void playerquitevent(PlayerQuitEvent e) {
		if (plugin.mobConfig.getBoolean("Mobs.Kill-On-Leave") == true) {
			final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					if (Bukkit.getServer().getOnlinePlayers().isEmpty()) {
						ConsoleCommandSender server = Bukkit.getConsoleSender();
						for (World worlds : Bukkit.getWorlds()) {
							for (Entity en : worlds.getEntities()) {
								boolean hasCustomName = en.getCustomName() != null;
								if (!(en instanceof Player)) {
									if (!hasCustomName) {
										en.remove();
									}
								}
							}
						}
						server.sendMessage(
								m.prefix + ChatColor.translateAlternateColorCodes('&', "&aAll Players Have Left"));
						server.sendMessage(
								m.prefix + ChatColor.translateAlternateColorCodes('&', "&4Removed all Entites."));

					} else {
						scheduler.cancelTasks(plugin);
					}
				}
			}, 60l);
		}

	}
}
