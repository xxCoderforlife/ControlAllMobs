package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;

public class ZombieMob implements Listener{

	private Main plugin;
	Messages m = new Messages();
	ChatUtils chat = new ChatUtils();
	
	public ZombieMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	private File ZombieConfigFile;
	private FileConfiguration ZombieConfig;
	
	@EventHandler
	public void onZombieSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity().getType() == EntityType.ZOMBIE)) {
			return;
		}
		Zombie zom = (Zombie) e.getEntity();
		zom.setCustomName(ZombieConfig.getString("Zombie.Name"));
	}
	
	public void createZombieConfig() {
		ZombieConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "zombie.yml");
		if(ZombieConfigFile == null) {
			try {
				ZombieConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadZombieConfig();
		
	}
	public void saveZombieConfig() {
		try {
			ZombieConfig.save(ZombieConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadZombieConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oLoading Zombie Configuration...");
		ZombieConfig = new YamlConfiguration();
		try {
			ZombieConfig.load(ZombieConfigFile);
			if(!ZombieConfig.contains("Zombie.Max-Health")) {
			ZombieConfig.set("Zombie.Name", (String) "8735");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
