package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.Role;
import me.mcacutt.townmurders.util.Countdown;
import me.mcacutt.townmurders.util.UtilPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NightSequence {

    private TownMurders plugin;

    private int nightCount;

    public NightSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Bukkit.getServer().getWorld("MF-1").setTime(18000);
        plugin.getSpawnPoints().tpPlayersToHomes();
        final List<Player> players = plugin.getPlayerManager().getPlayersInGame().stream().map(Bukkit::getPlayer).collect(Collectors.toList());
        UtilPlayer.sendTitle(players, ChatColor.DARK_RED + "Night " + getNightCount(), "", 20, 100, 20);
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.WOLF_HOWL, 1, 1);
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.EXPLODE, 1, 1);
            ChatChannels.GLOBAL.removeFromChannel(uuid);
            if (plugin.getPlayerManager().getBasePlayer(uuid).guilty() || plugin.getPlayerManager().getBasePlayer(uuid).getRole().equals(Role.WHISPERER)) {
                ChatChannels.MAFIA.addToChannel(uuid);
            }
            if (plugin.getPlayerManager().getBasePlayer(uuid).isJailed() || plugin.getPlayerManager().getBasePlayer(uuid).getRole().equals(Role.JAILOR)) {
                ChatChannels.JAIL.addToChannel(uuid);
            }
        }
    }

    Countdown nightCountdown = Countdown.of(completeTask -> {
        for (UUID uuid: plugin.getPlayerManager().getPlayersInGame()) {
            ChatChannels.MAFIA.removeFromChannel(uuid);
            ChatChannels.JAIL.removeFromChannel(uuid);
            ChatChannels.GLOBAL.addToChannel(uuid);
            plugin.getPlayerManager().getBasePlayer(uuid).setJailed(false);
        }
        plugin.getDaySequence().setDayCount(plugin.getDaySequence().getDayCount() + 1);
        plugin.getEndOfNightSequence().start();
        completeTask.cancel();
    }, 40);

    public int getNightCount() {
        return nightCount;
    }

    public void setNightCount(int nightCount) { this.nightCount = nightCount; }
}

