package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;

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
