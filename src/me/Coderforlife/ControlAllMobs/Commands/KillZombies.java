package me.Coderforlife.ControlAllMobs.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class KillZombies implements CommandExecutor {
	private Main plugin;

	public KillZombies(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
	Messages m = new Messages();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("killzombies")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("KAM.killzombies")) {
					for (Entity en : player.getWorld().getEntities()) {
						if (en.getType().equals(EntityType.ZOMBIE) || en.getType().equals(EntityType.DROWNED)
								|| en.getType().equals(EntityType.ZOMBIE_VILLAGER)) {
							en.remove();
						}
					}
					player.sendMessage(
							m.prefix + ChatColor.translateAlternateColorCodes('&', "&4Killed all &2Zombies"));

				}
			} else if (sender instanceof ConsoleCommandSender) {
				Bukkit.getConsoleSender().sendMessage("You can't use that as the console yet.");
			}
		}
		return true;
	}
}
