package me.Coderforlife.ControlAllMobs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.ControlAllMobs.Commands.KillMobs;
import me.Coderforlife.ControlAllMobs.Commands.KillZombies;
import me.Coderforlife.ControlAllMobs.Events.MobEgg;
import me.Coderforlife.ControlAllMobs.Events.MobSpawn;
import me.Coderforlife.ControlAllMobs.Events.PlayerQuit;
import me.Coderforlife.ControlAllMobs.Mobs.BatMob;
import me.Coderforlife.ControlAllMobs.Mobs.CowMob;
import me.Coderforlife.ControlAllMobs.Mobs.CreeperMob;
import me.Coderforlife.ControlAllMobs.Mobs.SkeletonMob;
import me.Coderforlife.ControlAllMobs.Mobs.VexMob;
import me.Coderforlife.ControlAllMobs.Mobs.ZombieMob;
import me.Coderforlife.ControlAllMobs.TabCommands.Tabs;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;

public class Main extends JavaPlugin {

	public File mobConfigFile;
	public FileConfiguration mobConfig;
	
	private File CreatureConfigFile;
	private FileConfiguration CreatureConfig;
	
	public File mobsFolder = new File(getDataFolder(), "Mobs");
	
	ChatUtils chat = new ChatUtils();
	
	public CreeperMob cm;
	public SkeletonMob sm;
	public CowMob cowm;
	public BatMob batm;
	public VexMob vexm;
	public ZombieMob zm;


	@Override
	public void onEnable() {
		registerEvents();
		createConfig();
		createMobsConfig();
		//Runs All Mob Setups
		SetupMobs();
	}

	@Override
	public void onDisable() {}
	@Override
	public void onLoad() {}

	private void createMobsConfig() {
		if (!mobsFolder.exists()) {
			mobsFolder.getParentFile().mkdir();
		}
		for (EntityType type : EntityType.values()) {
			if (type.isAlive() && !type.name().equalsIgnoreCase("armor_stand")
					&& !type.name().equalsIgnoreCase("player")) {
				String filename = type.name().toLowerCase() + ".yml";
				CreatureConfigFile = new File(getDataFolder() + File.separator + "Mobs", filename);
				if (!CreatureConfigFile.exists()) {
					CreatureConfigFile.getParentFile().mkdir();
					try {
						CreatureConfigFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				CreatureConfig = new YamlConfiguration();
				try {
					CreatureConfig.load(CreatureConfigFile);
				} catch (IOException | InvalidConfigurationException e) {
					e.printStackTrace();
				}
     		}
		}
	}

	private void createConfig() {
		mobConfigFile = new File(getDataFolder(), "config.yml");
		if (!mobConfigFile.exists()) {
			mobConfigFile.getParentFile().mkdir();

			saveResource("config.yml", false);
		}
		mobConfig = new YamlConfiguration();
		try {
			mobConfig.load(mobConfigFile);

		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();

		}
	}

	public FileConfiguration getCustomConfig() {
		return mobConfig;
	}

	public FileConfiguration getCreatureConfig() {
		return CreatureConfig;
	}

	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new MobEgg(this), this);
		this.getServer().getPluginManager().registerEvents(new MobSpawn(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		this.getCommand("killzombies").setExecutor(new KillZombies(this));
		this.getCommand("killmobs").setExecutor(new KillMobs(this));
		this.getCommand("killmobs").setTabCompleter(new Tabs(this));
	}
	
	private void SetupMobs() {
		//Setting Up Bat
		this.batm = new BatMob(this);
		batm.createBatConfig();
		batm.saveBatConfig();
		//Setting Up Cow
		this.cowm = new CowMob(this);
		cowm.createCowConfig();
		cowm.saveCowConfig();
		//Setting Up Creeper
		this.cm = new CreeperMob(this);
		cm.createCreeperConfig();
		cm.saveCreeperConfig();
		//Setting Up Skeleton
		this.sm = new SkeletonMob(this);
		sm.createSkeletonConfig();
		sm.saveSkeletonConfig();
		//Setting Up Vex
		this.vexm = new VexMob(this);
		vexm.createVexConfig();
		vexm.saveVexConfig();
		//Setting Up Zombie
		this.zm = new ZombieMob(this);
		zm.createZombieConfig();
		zm.saveZombieConfig();
		
	}


}
