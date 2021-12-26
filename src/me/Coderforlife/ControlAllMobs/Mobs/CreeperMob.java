package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class CreeperMob implements Listener {


	private Main plugin;
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();
	
	public CreeperMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	


	public File CreeperConfigFile;
	public FileConfiguration CreeperConfig;

	public void createCreeperConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oLoading Creeper Configuration...");
		CreeperConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "creeper.yml");
		if (CreeperConfigFile == null) {

			try {
				CreeperConfigFile.createNewFile();
				saveCreeperConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		CreeperConfig = new YamlConfiguration();
		try {
			CreeperConfig.load(CreeperConfigFile);
			if(!CreeperConfig.contains("Creeper.Name")) {
				CreeperConfig.set("Creeper.Name", (String) "eyuibfiqueb");
				CreeperConfig.set("Creeper.Speed", (double) 1);
				CreeperConfig.set("Creeper.Damage", (double) 2);
				CreeperConfig.set("Creeper.Max-Fuse-Timer", (int) 200);
				CreeperConfig.set("Creeper.Fuse-Timer", (int) 200);
				CreeperConfig.set("Creeper.Max-Health", (double) 100);
				CreeperConfig.set("Creeper.Name-Visable", (boolean) false);
				CreeperConfig.set("Creeper.Can-Pick-Up-Items", (boolean) false);
				CreeperConfig.set("Creeper.Has-A-Brain", (boolean) true);
				CreeperConfig.set("Creeper.No-Hiss", (boolean) false);
				CreeperConfig.set("Creeper.Remove-When-Far-Away", (boolean) true);
				CreeperConfig.set("Creeper.Explosion-Size", (int) 10);
				CreeperConfig.set("Creeper.Follow-Range", (double) 20);
			}

		} catch (FileNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage("Error loading Modded Creeper");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("Invaild creeper.yml");
		}
	}

	public void saveCreeperConfig() {
		try {
			CreeperConfig.save(CreeperConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ReloadCreeperConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oReloading CreeperConfig...");
			try {
				CreeperConfig.load(CreeperConfigFile);
			} catch (FileNotFoundException e) {
				chat.SendConsoleMessage(m.prefix + "&cCould not load CreeperConfig...");
				chat.SendConsoleMessage(m.prefix + "&aCreating Creeper Config...");
				createCreeperConfig();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.getCause().toString();
				e.printStackTrace();
			}
		}

	public YamlConfiguration getConfig() {
		return (YamlConfiguration) CreeperConfig;
	}

	public void loadCreeperConfig() throws InvalidConfigurationException {
		try {
			CreeperConfig.load(CreeperConfigFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void CreeperSpawn(EntitySpawnEvent e) {
		if (!(e.getEntity() instanceof Creeper)) {
			return;
		}
		Creeper creeper = (Creeper) e.getEntity();
		creeper.setCustomName(ChatColor.translateAlternateColorCodes('&', CreeperConfig.getString("Creeper.Name")));
		creeper.setMaxFuseTicks(CreeperConfig.getInt("Creeper.Max-Fuse-Timer"));
		creeper.setFuseTicks(CreeperConfig.getInt("Creeper.Fuse-Timer"));
		creeper.setCustomNameVisible(CreeperConfig.getBoolean("Creeper.Name-Visable"));
		creeper.setCanPickupItems(CreeperConfig.getBoolean("Creeper.Can-Pick-Up-Items"));
		creeper.setAI(CreeperConfig.getBoolean("Creeper.Has-A-Brain"));
		creeper.setSilent(CreeperConfig.getBoolean("Creeper.No-Hiss"));
		creeper.setRemoveWhenFarAway(CreeperConfig.getBoolean("Creeper.Remove-When-Far-Away"));
		creeper.setExplosionRadius(CreeperConfig.getInt("Creeper.Explosion-Size"));
		creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)
				.setBaseValue((double) CreeperConfig.getDouble("Creeper.Speed"));
		creeper.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)
				.setBaseValue((double) CreeperConfig.getDouble("Creeper.Damage"));
		creeper.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
				.setBaseValue((double) CreeperConfig.getDouble("Creeper.Follow-Range"));
		creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH)
				.setBaseValue((double) CreeperConfig.getDouble("Creeper.Max-Health"));
				

	}

}
