package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class BatMob implements Listener{

	private Main plugin;
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();
	
	private File BatConfigFile;
	private FileConfiguration BatConfig;
	
	public BatMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBatSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity().getType() == EntityType.BAT)) {
			return;
		}
		if(BatConfig.getBoolean("Bat.Can-Spawn") == true) {
		Bat b = (Bat) e.getEntity();
		b.setCustomName(ChatColor.translateAlternateColorCodes('&', BatConfig.getString("Bat.Name")));
		b.setCustomNameVisible(BatConfig.getBoolean("Bat.Custom-Name-Visable"));
		b.setAI(BatConfig.getBoolean("Bat.Has-A-Brain"));
		b.setCanPickupItems(BatConfig.getBoolean("Bat.Can-PickUp-Items"));
		b.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(BatConfig.getDouble("Bat.Max-Health"));
		b.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(BatConfig.getDouble("Bat.Flying.Speed"));
			
		}else {
			e.setCancelled(true);
		}
	}
	
	public void createBatConfig() {
		BatConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "bat.yml");
		if(!BatConfigFile.exists()) {
			try {
				BatConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadBatConfig();
	}
	
	public void loadBatConfig() {
		BatConfig = new YamlConfiguration();
		try {
			chat.SendConsoleMessage(m.prefix + "&a&oLoading Bat Configuration...");
			BatConfig.load(BatConfigFile);
			if(!BatConfig.contains("Bat.Flying.Speed")) {
				BatConfig.set("Bat.Name", (String) "&d&oBat");
				BatConfig.set("Bat.Custom-Name-Visable", (boolean) true);
				BatConfig.set("Bat.Has-A-Brain", (boolean) true);
				BatConfig.set("Bat.Can-PickUp-Items", (boolean) false);
				BatConfig.set("Bat.Max-Health", (double) 100);
				BatConfig.set("Bat.Can-Spawn", (boolean) true);
				BatConfig.set("Bat.Flying-Speed", (double) 0.2);
			}
		} catch (FileNotFoundException e) {
			chat.SendConsoleMessage(m.prefix + "&c&oBat.yml was not found.");
			chat.SendConsoleMessage(m.prefix + "&a&oCreating Bat.yml...");
			createBatConfig();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	public void saveBatConfig() {
		try {
			BatConfig.save(BatConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}