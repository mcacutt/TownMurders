package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.Roles;
import me.mcacutt.townmurders.util.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.UUID;

public class DaySequence {

    private final TownMurders plugin;

    private int dayCount;

    public DaySequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Bukkit.getServer().getWorld("MF-1").setTime(6000);
        plugin.getSpawnPoints().tpPlayersToSpawns();
        for (UUID p : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(p).sendTitle(ChatColor.DARK_RED + "Day" + getDayCount(), "");
        }
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
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

    public void setDayCount(int dayCount) {
    }

}
