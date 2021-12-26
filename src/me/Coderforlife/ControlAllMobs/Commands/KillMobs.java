package me.Coderforlife.ControlAllMobs.Commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
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

public class KillMobs implements CommandExecutor {

	private Main plugin;

	public KillMobs(Main plugin) {
		this.plugin = plugin;
	}

	Messages m = new Messages();

	public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (command.getName().equalsIgnoreCase("killmobs")) {
					if (p.hasPermission(m.perm + "main")) {
						p.sendMessage(" ");
						p.spigot().sendMessage(m.HeaderBuilder(m.getHeaderText()));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&c&oClick one of the following commands:"));
						p.spigot().sendMessage(m.mobsBuilder(m.getMobsText()));
						p.spigot().sendMessage(m.killallBuilder(m.getKillAllMobsText()));
						p.spigot().sendMessage(m.killallUNSBuilder(m.getKillAllUnSafeText()));
					} else {
						p.sendMessage(m.prefix + m.permessage);
						p.sendMessage(m.prefix
								+ ChatColor.translateAlternateColorCodes('&', "&7&oPermission:&b KAM.main"));
					}
				}

			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("mobs")) {
					p.sendMessage(m.prefix
							+ ChatColor.translateAlternateColorCodes('&', " Use &e&o/killmobs mobs &c&o<MobName>"));

				} else if (args[0].equalsIgnoreCase("killall")) {
					if (p.hasPermission(m.perm + "killmobs.killall")) {
						final int dead = p.getWorld().getEntities().size();
						final String deadS = Integer.toString(dead);
						for (Entity en : p.getWorld().getEntities()) {
							boolean hasCustomName = en.getCustomName() != null;
							if (!(en instanceof Player)) {
								if (!hasCustomName) {
									en.remove();
								}
							}
						}
						p.sendMessage(m.prefix + ChatColor.translateAlternateColorCodes('&',
								"Wow you are a &4&lMonster&r you just killed &a&l" + deadS + "&r Mobs"));

					} else {
						p.sendMessage(m.prefix + m.permessage);
						p.sendMessage(m.prefix + ChatColor.translateAlternateColorCodes('&',
								"&7&oPermission:&b KAM.killmobs.killall"));
					}
				} else if (args[0].equalsIgnoreCase("version")) {
					if (p.hasPermission(m.perm + "version")) {
						p.sendMessage(m.prefix + "You are running version "
								+ plugin.getDescription().getVersion().toString());
						for (File Mobfile : new File(plugin.getDataFolder() + File.separator + "Mobs").listFiles()) {
							Bukkit.getConsoleSender().sendMessage(Mobfile.getName());
						}

					}
				}else if(args[0].equalsIgnoreCase("reload")) {
					if(p.hasPermission(m.perm + "reload")) {
						p.sendMessage("");
						plugin.cm.ReloadCreeperConfig();
					}
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("mobs")) {
					if (p.hasPermission(m.perm + "killmobs")) {
						try {
							EntityType ent = EntityType.valueOf((String) args[1].toUpperCase());
							for (World worlds : Bukkit.getWorlds()) {
								for (Entity en : worlds.getEntities()) {
									if (en.getType() == ent) {
										en.remove();
									}
								}
							}
							p.sendMessage(m.prefix
									+ ChatColor.translateAlternateColorCodes('&', "&aKilled all &e" + args[1] + "S"));
						} catch (IllegalArgumentException e) {
							p.sendMessage(m.prefix + ChatColor.translateAlternateColorCodes('&',
									"&c" + args[1] + " is not a vaild Entity"));
						}
					} else {
						p.sendMessage(m.prefix + m.permessage);
						p.sendMessage(m.prefix
								+ ChatColor.translateAlternateColorCodes('&', "&7&oPermission:&b KAM.killmobs"));
					}
				} else if (args[0].equalsIgnoreCase("killall")) {
					if (args[1].equalsIgnoreCase("unsafe")) {
						if (p.hasPermission(m.perm + "killall.unsafe")) {
							final int dead = p.getWorld().getEntities().size();
							final String deadS = Integer.toString(dead);
							for (World worlds : Bukkit.getWorlds()) {
								for (Entity en : worlds.getEntities()) {
									if (!(en instanceof Player)) {
										en.remove();
									}
								}
							}
							p.sendMessage(m.prefix + ChatColor.translateAlternateColorCodes('&',
									"Wow you are a &4&lMonster&r you just killed &a&l" + deadS + "&r Mobs"));
						} else {
							p.sendMessage(m.prefix + m.permessage);
							p.sendMessage(m.prefix + ChatColor.translateAlternateColorCodes('&',
									"&7&oPermission:&b KAM.killall.unsafe"));
						}
					}
				}
			}
		}
		if (sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			if (args.length == 0) {
				if (command.getName().equalsIgnoreCase("killmobs")) {
					console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&oUse the following commands:"));
					console.sendMessage(m.dash
							+ ChatColor.translateAlternateColorCodes('&', "&b&o/killmobs &amobs &4&lMOBNAME"));
					console.sendMessage(m.dash + ChatColor.translateAlternateColorCodes('&',
							"&b&o/killmobs &akillall &7&o(&f&lRemoves all unnamed mobs)"));
					console.sendMessage(m.dash + ChatColor.translateAlternateColorCodes('&',
							"&b&o/killmobs &akillall unsafe &7&o(&f&lRemoves all Mobs,Drops, and Entites even if named&7&o)"));
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("killall")) {
					for (World worlds : Bukkit.getWorlds()) {
						for (Entity en : worlds.getEntities()) {
							if (!(en instanceof Player)) {
								en.remove();
							}
						}
					}
					console.sendMessage(m.prefix + ChatColor.translateAlternateColorCodes('&',
							"Wow you are a &4&lMonster&r you just killed &a&l"));
				}
			}
			if (args.length == 2) {
				try {
					EntityType ent = EntityType.valueOf((String) args[1].toUpperCase());
					for (World worlds : Bukkit.getWorlds()) {
						for (Entity en : worlds.getEntities()) {
							if (en.getType() == ent) {
								en.remove();
							}
						}
					}
					console.sendMessage(m.prefix
							+ ChatColor.translateAlternateColorCodes('&', "&aKilled all &e" + args[1] + "S"));
				} catch (IllegalArgumentException e) {
					console.sendMessage(m.prefix
							+ ChatColor.translateAlternateColorCodes('&', "&c" + args[1] + " is not a vaild Entity"));
				}
			}
		}
		return true;

	}
}
