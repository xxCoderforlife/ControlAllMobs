package me.Coderforlife.ControlAllMobs.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.Messages;

public class MobEgg implements Listener {

	private Main plugin;

	public MobEgg(Main plugin) {
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
	public void EggUse(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		if (e.getItem() == null) {
			return;
		}
		for (String mobs : plugin.mobConfig.getStringList("Mobs.Deny-Spawn")) {
			if (e.getItem().getType() == Material.valueOf(mobs.toString() + "_SPAWN_EGG")) {
				p.sendMessage(m.prefix + mobs.toString() + "s are not allowed to spawn.");
			}
		}
	}
}
