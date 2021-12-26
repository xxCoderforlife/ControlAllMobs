package me.Coderforlife.ControlAllMobs.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;

public class MobSpawn implements Listener {

	private Main plugin;

	public MobSpawn(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e) {
		try {
			for (String string : plugin.mobConfig.getStringList("Mobs.Deny-Spawn")) {
				if (e.getEntityType().equals(EntityType.valueOf(string.toUpperCase()))) {
					e.setCancelled(true);
				}
			}
		} catch (IllegalArgumentException ex) {
			Bukkit.getConsoleSender().sendMessage("Error with preventing mob spawns.");
			ex.printStackTrace();
		}

	}

}
