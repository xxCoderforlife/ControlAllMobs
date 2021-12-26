package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;

public class CowMob implements Listener{

	private Main plugin;
	
	private File CowConfigFile;
	private FileConfiguration CowConfig;
	
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();
	
	public CowMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCowSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity().getType() == EntityType.COW)) {
			return;
		}
		Cow c = (Cow) e.getEntity();
		c.setCustomName(CowConfig.getString("Cow.Name"));
		c.setCustomNameVisible(CowConfig.getBoolean("Cow.Custom-Name-Visable"));
		c.setAI(CowConfig.getBoolean("Cow.Has-A-Brain"));
		c.setCanPickupItems(CowConfig.getBoolean("Cow.Can-PickUp-Items"));
		c.setGlowing(CowConfig.getBoolean("Cow.Glowing"));
		c.setBreed(CowConfig.getBoolean("Cow.Can-Breed"));
		c.setAge(CowConfig.getInt("Cow.Age"));
		c.setSilent(CowConfig.getBoolean("Cow.No-Mooing"));
		c.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(CowConfig.getDouble("Cow.Speed"));
		c.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(CowConfig.getDouble("Cow.Max-Health"));
		
	}
	
	public void createCowConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oLoading Cow Configuration...");
		CowConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "cow.yml");
		if(!CowConfigFile.exists()) {
			try {
				CowConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		CowConfig = new YamlConfiguration();
		try {
			CowConfig.load(CowConfigFile);
			if(!CowConfig.contains("Cow.Max-Health")) {
			CowConfig.set("Cow.Name", (String) "&e&oCow");
			CowConfig.set("Cow.Age", (int) 1);
			CowConfig.set("Cow.Has-A-Brain", (boolean) true);
			CowConfig.set("Cow.Can-Breed", (boolean) true);
			CowConfig.set("Cow.No-Mooing", (boolean) false);
			CowConfig.set("Cow.Can-PickUp-Items", (boolean) false);
			CowConfig.set("Cow.Custom-Name-Visable", (boolean) true);
			CowConfig.set("Cow.Glowing", (boolean) false);
			CowConfig.set("Cow.Speed", (double) 0.2);
			CowConfig.set("Cow.Max-Health", (double) 100);
			}
			saveCowConfig();


		} catch (FileNotFoundException e) {
			chat.SendConsoleMessage(m.prefix + "&c&oCould not find cow.yml.");
			chat.SendConsoleMessage(m.prefix + "&a&oCreating one and doing all the the loading and what not..");
			createCowConfig();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void loadCowConfig() {
		try {
			CowConfig.load(CowConfigFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void saveCowConfig() {
		try {
			CowConfig.save(CowConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
