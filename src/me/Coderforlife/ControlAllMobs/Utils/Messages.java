package me.Coderforlife.ControlAllMobs.Utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class Messages {

	
	public final String prefix = ChatColor.translateAlternateColorCodes('&', "&b&l[&a&l&oC.A.M&b&l]&r ");
	public final String perm = "CAM.";
	public final String dash = ChatColor.translateAlternateColorCodes('&', "&7&o&l- &7");
	public final String permessage = ChatColor.translateAlternateColorCodes('&',
			"&cYou do not have permission to use that command.");
	
	
	
	public TextComponent header = new TextComponent();
	public TextComponent mobs = new TextComponent();
	public TextComponent killallm = new TextComponent();
	public TextComponent killallun = new TextComponent();

	public TextComponent HeaderBuilder(TextComponent te) {
		te.setText("             ControlAllMobs              ");
		;
		te.setBold(true);
		te.setColor(ChatColor.GOLD);
		te.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor
				.translateAlternateColorCodes('&', "&6&l&oCONTROLALLMOBS\n" + "&d&lMade By &1&oxxCoderforlife"))));
		return te;
	}

	public TextComponent mobsBuilder(TextComponent te) {
		te.setText(dash + "/killmobs mobs <mobname>");
		te.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/killmobs mobs <mobname>"));
		te.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new Text(ChatColor.translateAlternateColorCodes('&', "&fKills only the mob you want."))));
		return te;

	}

	public TextComponent killallBuilder(TextComponent te) {
		te.setText(dash + "/killmobs killall");
		te.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/killmobs killall"));
		te.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new Text(ChatColor.translateAlternateColorCodes('&', "&fKills all mobs without a name."))));
		return te;

	}

	public TextComponent killallUNSBuilder(TextComponent te) {
		te.setText(dash + "/killmobs killall unsafe");
		te.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/killmobs killall unsafe"));
		te.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new Text(ChatColor.translateAlternateColorCodes('&', "&fRemoves all entites in the world"))));
		return te;

	}
	public TextComponent getHeaderText() {
		return header;
		
	}
	public TextComponent getMobsText() {
		return mobs;
		
	}
	public TextComponent getKillAllMobsText() {
		return killallm;
		
	}
	public TextComponent getKillAllUnSafeText() {
		return killallun;
		
	}
	
}
