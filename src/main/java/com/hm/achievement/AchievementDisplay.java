package com.hm.achievement;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import com.hm.achievement.language.Lang;

public class AchievementDisplay {

	private AdvancedAchievements plugin;

	public AchievementDisplay(AdvancedAchievements achievement) {
		this.plugin = achievement;
	}

	public void displayAchievement(Player player, String name, String msg) {

		name = ChatColor.translateAlternateColorCodes('&', name);
		msg = ChatColor.translateAlternateColorCodes('&', msg);

		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE
				+ plugin.getIcon() + ChatColor.GRAY + "] "
				+ Lang.ACHIVEMENT_NEW + " " + ChatColor.WHITE + name);
		if (plugin.isChatMessage()) {
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				if (!p.getName().equals(player.getName())) {
					p.sendMessage(ChatColor.GRAY
							+ "["
							+ ChatColor.DARK_PURPLE
							+ plugin.getIcon()
							+ ChatColor.GRAY
							+ "] "
							+ Lang.ACHIEVEMENT_RECEIVED.toString().replace(
									"PLAYER", player.getName()) + " "
							+ ChatColor.WHITE + name);

				}
			}
		}

		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE
				+ plugin.getIcon() + ChatColor.GRAY + "] " + ChatColor.WHITE
				+ msg);

		if (plugin.isFirework()) {
			Location location = player.getLocation();
			location.setY(location.getY() - 1);

			Firework fw = player.getWorld().spawn(location, Firework.class);
			FireworkMeta fwm = fw.getFireworkMeta();
			FireworkEffect effect = FireworkEffect
					.builder()
					.flicker(false)
					.trail(false)
					.withColor(
							Color.WHITE.mixColors(Color.BLUE
									.mixColors(Color.NAVY)))
					.with(Type.BALL_LARGE).withFade(Color.PURPLE).build();
			fwm.addEffects(effect);
			fw.setVelocity(player.getLocation().getDirection().multiply(0));
			fw.setFireworkMeta(fwm);
		}
	}

}