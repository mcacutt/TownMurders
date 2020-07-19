package me.mcacutt.townmurders;

import me.mattstudios.mf.base.CommandManager;
import me.mcacutt.townmurders.players.PlayerData.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class TownMurders extends JavaPlugin implements CommandExecutor {

	private static final TownMurders INSTANCE = TownMurders.getPlugin(TownMurders.class);
	private final PlayerManager playerManager = new PlayerManager();

	public PlayerManager getPlayerManager() {
		return this.playerManager;
	}

	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\n\nTOWN MURDERS IS ENABLED\n\n");
		this.saveConfig();
		loadConfig();
		CommandManager commandManager =  new CommandManager(this);
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\n\nTOWN MURDERS IS DISABLED\n\n");
		this.saveConfig();
	}

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public static TownMurders getInstance() { return INSTANCE; }
}
