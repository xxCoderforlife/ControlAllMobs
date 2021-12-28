package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class VexMob implements Listener{

	private Main plugin;
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();
	
	private File VexConfigFile;
	private FileConfiguration VexConfig;
	
	public VexMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onVexSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity().getType() == EntityType.VEX)) {
			return;
		}
		if(VexConfig.getBoolean("Vex.Can-Spawn") == true) {
			Vex v = (Vex) e.getEntity();
			v.setCustomName(ChatColor.translateAlternateColorCodes('&', VexConfig.getString("Vex.Name")));
			v.setCustomNameVisible(VexConfig.getBoolean("Vex.Custom-Name-Visible"));
			v.setAI(VexConfig.getBoolean("Vex.Has-A-Brain"));
			v.setCanPickupItems(VexConfig.getBoolean("Vex.Can-PickUp-Items"));
			v.setGlowing(VexConfig.getBoolean("Vex.Glowing"));
			v.setSilent(VexConfig.getBoolean("Vex.Dont-Laugh"));
			v.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(VexConfig.getDouble("Vex.Speed"));
			v.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(VexConfig.getDouble("Vex.Max-Health"));
		}else {
			e.setCancelled(true);
		}
	}
	
	public void createVexConfig() {
		VexConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "vex.yml");
		if(!VexConfigFile.exists()) {
			try {
				VexConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadVexConfig();
		
	}
	
	public void loadVexConfig() {
		VexConfig = new YamlConfiguration();
		try {
			chat.SendConsoleMessage(m.prefix + "&a&oLoading Vex Configuration...");
			VexConfig.load(VexConfigFile);
			if(!VexConfig.contains("Vex.Max-Health")) {
				VexConfig.set("Vex.Name", (String) "&b&oVex");
				VexConfig.set("Vex.Can-Spawn", (boolean) true);
				VexConfig.set("Vex.Custom-Name-Visible", (boolean) true);
				VexConfig.set("Vex.Has-A-Brain", (boolean) true);
				VexConfig.set("Vex.Can-PickUp-Items", (boolean) false);
				VexConfig.set("Vex.Glowing", (boolean) false);
				VexConfig.set("Vex.Dont-Laugh", (boolean) false);
				VexConfig.set("Vex.Speed", (double) 0.2);
				VexConfig.set("Vex.Max-Health", (double) 100);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void saveVexConfig() {
		try {
			VexConfig.save(VexConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
