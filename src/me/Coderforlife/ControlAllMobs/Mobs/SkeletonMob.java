package me.Coderforlife.ControlAllMobs.Mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.Coderforlife.ControlAllMobs.Main;
import me.Coderforlife.ControlAllMobs.Utils.ChatUtils;
import me.Coderforlife.ControlAllMobs.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class SkeletonMob implements Listener,CommandExecutor{
	
	private Main plugin;
	
	public SkeletonMob(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	private File SkeletonConfigFile;
	private FileConfiguration SkeletonConfig;
	
	ChatUtils chat = new ChatUtils();
	Messages m = new Messages();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSkeletonSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity().getType() == EntityType.SKELETON)) {
			return;
		}
		if(SkeletonConfig.getBoolean("Skeleton.Can-Spawn") == true) {
		Skeleton skel = (Skeleton) e.getEntity();
		skel.setCustomName(ChatColor.translateAlternateColorCodes('&',SkeletonConfig.getString("Skeleton.Name")));
		skel.setAI(SkeletonConfig.getBoolean("Skeleton.Has-A-Brain"));
		skel.setCanPickupItems(SkeletonConfig.getBoolean("Skeleton.Can-PickUp-Items"));
		skel.setCustomNameVisible(SkeletonConfig.getBoolean("Skeleton.Custom-Name-Visable"));
		skel.setGlowing(SkeletonConfig.getBoolean("Skeleton.Glowing"));
		skel.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(SkeletonConfig.getDouble("Skeleton.Follow-Range"));
		skel.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(SkeletonConfig.getDouble("Skeleton.Speed"));
		skel.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(SkeletonConfig.getDouble("Skeleton.Max-Health"));
		//skel.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue();
		}else {
			e.setCancelled(true);
		}
	}
	
	public void createSkeletonConfig() {
		chat.SendConsoleMessage(m.prefix + "&a&oLoading Skeleton Configuration...");
		SkeletonConfigFile = new File(plugin.getDataFolder() + File.separator + "Mobs", "skeleton.yml");
		if(!SkeletonConfigFile.exists()) {
			try {
				SkeletonConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SkeletonConfig = new YamlConfiguration();
		try {
			if(!SkeletonConfig.contains("Skeleton.Max-Health")) {
			SkeletonConfig.load(SkeletonConfigFile);
			SkeletonConfig.set("Skeleton.Name", (String) "&7&oSkeleton");
			SkeletonConfig.set("Skeleton.Has-A-Brain", (boolean) true);
			SkeletonConfig.set("Skeleton.Can-PickUp-Items", (boolean) false);
			SkeletonConfig.set("Skeleton.Custom-Name-Visable", (boolean) true);
			SkeletonConfig.set("Skeleton.Glowing", (boolean) false);
			SkeletonConfig.set("Skeleton.Attack-Speed", (double) 1);
			SkeletonConfig.set("Skeleton.Follow-Range", (double) 12);
			SkeletonConfig.set("Skeleton.Speed", (double) 0.2);
			SkeletonConfig.set("Skeleton.Can-Spawn", (boolean) true);
			SkeletonConfig.set("Skeleton.Max-Health", (double) 100);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	public void saveSkeletonConfig() {
		try {
			SkeletonConfig.save(SkeletonConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("edit-skeleton")) {
			if(sender instanceof Player) {
				chat.SendConsoleMessage("&4SUCK MY ASS");
			}
		}
		return false;
	}
	
}
