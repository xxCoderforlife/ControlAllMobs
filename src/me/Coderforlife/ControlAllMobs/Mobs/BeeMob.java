package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class BeeMob implements Listener {

	private Main plugin;
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();

	File BeeConfigFile;
	FileConfiguration BeeConfig;

	public BeeMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBeeSpawn(EntitySpawnEvent e) {
		if (!(e.getEntity().getType() == EntityType.BEE)) {
			return;
		}
		
		Bee b = (Bee) e.getEntity();
		b.setCustomName(ChatColor.translateAlternateColorCodes('&', BeeConfig.getString("Bee.Name")));
		b.setCustomNameVisible(BeeConfig.getBoolean("Bee.Custom-Name-Visable"));
		b.setAge(BeeConfig.getInt("Bee.Age"));
		b.setAgeLock(BeeConfig.getBoolean("Bee.Age-Lock"));
		b.setAI(BeeConfig.getBoolean("Bee.Has-A-Brain"));
		b.setBreed(BeeConfig.getBoolean("Bee.Can-Breed"));
		b.setCanPickupItems(BeeConfig.getBoolean("Bee.Can-PickUp-Items"));
		b.setGlowing(BeeConfig.getBoolean("Bee.Glowing"));
		b.setSilent(BeeConfig.getBoolean("Bee.NoBuzzing"));
		b.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(BeeConfig.getDouble("Bee.Max-Health"));
		b.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(BeeConfig.getDouble("Bee.Speed"));
	}

	public void createBeeConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oLoading Bee Configuration...");
		BeeConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "bee.yml");
		if (!BeeConfigFile.exists()) {
			try {
				BeeConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadBeeConfig();
	}
	
	public void loadBeeConfig() {
		BeeConfig = new YamlConfiguration();
		try {
			BeeConfig.load(BeeConfigFile);
			if(!BeeConfig.contains("Bee.Max-Health")) {
				BeeConfig.set("Bee.Name", "&6&oBee");
				BeeConfig.set("Bee.Custom-Name-Visable", (boolean) true);
				BeeConfig.set("Bee.Age", (int) 1);
				BeeConfig.set("Bee.Age-Lock", (boolean) false);
				BeeConfig.set("Bee.Has-A-Brain", (boolean) true);
				BeeConfig.set("Bee.Can-Breed", (boolean) true);
				BeeConfig.set("Bee.Can-PickUp-Items", (boolean) false);
				BeeConfig.set("Bee.Glowing", (boolean) false);
				BeeConfig.set("Bee.NoBuzzing", (boolean) false);
				BeeConfig.set("Bee.Speed", (double) 0.2);
				BeeConfig.set("Bee.Max-Health", (double) 100);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	public void saveBeeConfig() {
		try {
			BeeConfig.save(BeeConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
