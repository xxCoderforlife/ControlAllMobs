package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class AxolotlMob implements Listener{

	private Main plugin;
	
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();
	
	File AxoConfigFile;
	FileConfiguration AxoConfig;
	
	public AxolotlMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAxolotlSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity().getType() == EntityType.AXOLOTL)) {
			return;
		}
		Axolotl axo = (Axolotl) e.getEntity();
		axo.setCustomName(ChatColor.translateAlternateColorCodes('&', AxoConfig.getString("Axolotl.Name")));
		axo.setCustomNameVisible(AxoConfig.getBoolean("Axolotl.Custom-Name-Visable"));
		axo.setAge(AxoConfig.getInt("Axolotl.Age"));
		axo.setAgeLock(AxoConfig.getBoolean("Axolotl.Age-Lock"));
		axo.setAI(AxoConfig.getBoolean("Axolotl.Has-A-Brain"));
		axo.setBreed(AxoConfig.getBoolean("Axolotl.Can-Breed"));
		axo.setCanPickupItems(AxoConfig.getBoolean("Axolotl.Can-PickUp-Items"));
		axo.setGlowing(AxoConfig.getBoolean("Axolotl.Glowing"));
		axo.setLoveModeTicks(AxoConfig.getInt("Axolotl.How-Long-Will-I-Love-You"));
		axo.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(AxoConfig.getDouble("Axolotl.Max-Health"));
		axo.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(AxoConfig.getDouble("Axolotl.Speed"));
	}
	
	public void createAxolotlConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oLoading Axolotl Configuration...");
		AxoConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "axolotl.yml");
		if(!AxoConfigFile.exists()) {
			try {
				AxoConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadAxolotlConfig();
	}
	public void loadAxolotlConfig() {
      AxoConfig = new YamlConfiguration();
		
		try {
			AxoConfig.load(AxoConfigFile);
			if(!AxoConfig.contains("Axolotl.Max-Health")) {
				AxoConfig.set("Axolotl.Name", (String) "&1&oAxolotl");
				AxoConfig.set("Axolotl.Custom-Name-Visable", (boolean) true);
				AxoConfig.set("Axolotl.Age", (int) 1);
				AxoConfig.set("Axolotl.Age-Lock", (boolean) false);
				AxoConfig.set("Axolotl.Has-A-Brain", (boolean) true);
				AxoConfig.set("Axolotl.Can-Breed", (boolean) true);
				AxoConfig.set("Axolotl.Can-PickUp-Items", (boolean) false);
				AxoConfig.set("Axolotl.Glowing", (boolean) false);
				AxoConfig.set("Axolotl.How-Long-Will-I-Love-You", (int) 600);
				AxoConfig.set("Axolotl.Max-Health", (double) 100);
				AxoConfig.set("Axolotl.Speed", (double) 0.2);
			} 
		} catch (FileNotFoundException e) {
			chat.SendConsoleMessage("&c&oAxolotl.yml does not exist.");
			chat.SendConsoleMessage("&a&oCreating one...");
			createAxolotlConfig();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	public void saveAxolotlConfig() {
		try {
			AxoConfig.save(AxoConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
