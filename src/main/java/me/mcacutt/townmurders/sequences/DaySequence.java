package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.Roles;
import me.mcacutt.townmurders.util.Countdown;
import me.mcacutt.townmurders.util.UtilPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaySequence {

    private final TownMurders plugin;

    private int dayCount;

    public DaySequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Bukkit.getServer().getWorld("MF-1").setTime(6000);
        plugin.getSpawnPoints().tpPlayersToSpawns();
        final List<Player> players = plugin.getPlayerManager().getPlayersInGame().stream().map(Bukkit::getPlayer).collect(Collectors.toList());
            UtilPlayer.sendTitle(players, ChatColor.DARK_RED + "Day " + getDayCount(), "", 20, 100, 20);
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.WITHER_SPAWN, 1, 1);
            ChatChannels.GLOBAL.addToChannel(uuid);
            if (plugin.getPlayerManager().getBasePlayer(uuid).getRole().equals(Roles.WHISPERER))
                ChatChannels.WHISPER.addToChannel(uuid);
        }
        if (dayCount == 1) {
            Countdown day1CountDown = Countdown.of(completeTask -> {
                plugin.getNightSequence().setNightCount(1);
                plugin.getNightSequence().start();
                completeTask.cancel();//cancel it
            }, 20);
        }
        if (dayCount > 1) {
            Countdown dayCountDown = Countdown.of(completeTask -> {
                plugin.getVoteSequence().start();
                completeTask.cancel();//cancel it
            }, 40);
        }
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) { this.dayCount = dayCount; }

}
