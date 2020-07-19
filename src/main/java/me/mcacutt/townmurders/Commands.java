package me.mcacutt.townmurders;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import me.mcacutt.townmurders.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Command("mafia")
public class Commands extends CommandBase {
    private final TownMurders plugin;
    public Location stand = null;
    public Location lobby = null;
    public Location spec = null;
    public List<Location> spawns = new ArrayList<>();
    public List<Location> homes = new ArrayList<>();

    public Commands(TownMurders plugin) {
        this.plugin = plugin;
    }

    @Default
    public void defaultCommand(final CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "MAFIA TOWN MURDERS VERSION 1.0");
    }

    @SubCommand("resetSpawns")
    @Permission("mafia.admin")
    public void setResetSpawnsCommand(final Player player) {
        spawns.clear();
    }

    @SubCommand("resetHomes")
    @Permission("mafia.admin")
    public void setResetHomesCommand(final Player player) {
        homes.clear();
    }

    @SubCommand("resetSpec")
    @Permission("mafia.admin")
    public void setResetSpecCommand(final Player player) {
        spec = null;
    }

    @SubCommand("resetLobby")
    @Permission("mafia.admin")
    public void setResetLobbyCommand(final Player player) {
        lobby = null;
    }

    @SubCommand("resetStand")
    @Permission("mafia.admin")
    public void setResetCommand(final Player player) {
        stand = null;
    }

    @SubCommand("setSpawn")
    @Permission("mafia.admin")
    public void setSpawnCommand(final Player player) {
        if (!(spawns.size() < 12)) {
            player.sendMessage("All spawns are taken, if you messed up, please type /mafia resetSpawns");
        } else {
            spawns.add(player.getLocation());
            player.sendMessage("spawn " + spawns.size() + " set successfully");
        }
    }

    @SubCommand("setHome")
    @Permission("mafia.admin")
    public void setHomeCommand(final Player player) {
        if (!(homes.size() < 12)) {
            player.sendMessage("All homes are taken, if you messed up, please type /mafia resetHomes");
        } else {
            homes.add(player.getLocation());
            player.sendMessage("home " + homes.size() + " set successfully");
        }
    }

    @SubCommand("setStand")
    @Permission("mafia.admin")
    public void setStandCommand(final Player player) {
        stand = player.getLocation();
        player.sendMessage("stand set sucessfully");
    }

    @SubCommand("setLobby")
    @Permission("mafia.admin")
    public void setLobbyCommand(final Player player) {
        lobby = player.getLocation();
        player.sendMessage("lobby set sucessfully");
    }

    @SubCommand("setSpec")
    @Permission("mafia.admin")
    public void setSpecCommand(final Player player) {
        spec = player.getLocation();
        player.sendMessage("Spec set sucessfully");
    }

    @SubCommand("bigRedButton")
    @Permission("mafia.admin")
    public void buttonCommand(final CommandSender sender) {
        plugin.getServer().getPluginManager().disablePlugin(plugin);
    }

    @SubCommand("save")
    @Permission("mafia.admin")
    public void saveMapToConfigCommand(final CommandSender sender) {
        plugin.getDataManager().getDataConfig().set("spawns", spawns);
        plugin.getDataManager().getDataConfig().set("homes", homes);
        plugin.getDataManager().getDataConfig().set("lobby", lobby);
        plugin.getDataManager().getDataConfig().set("spec", spec);
        plugin.getDataManager().getDataConfig().set("stand", stand);
        plugin.getDataManager().saveDataConfigFile();
    }
}
