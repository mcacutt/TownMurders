package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.Roles;
import me.mcacutt.townmurders.util.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class NightSequence {

    private final TownMurders plugin;

    private int nightCount;

    public NightSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Bukkit.getServer().getWorld("MF-1").setTime(18000);
        plugin.getSpawnPoints().tpPlayersToHomes();
        for (UUID p : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(p).sendTitle(ChatColor.DARK_RED + "Night" + getNightCount(), "");
        }
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
            ChatChannels.GLOBAL.removeFromChannel(uuid);
            plugin.getPlayerManager().getBasePlayer(uuid).getRole().isGood().ifPresent(aBoolean -> {
                    if(!aBoolean || plugin.getPlayerManager().getBasePlayer(uuid).getRole().equals(Roles.WHISPERER)){
                        ChatChannels.MAFIA.addToChannel(uuid);
                    }
            });

        }
        Countdown nightCountdown = Countdown.of(completeTask -> {
            setNightCount(plugin.getNightSequence().getNightCount() + 1);
            plugin.getDaySequence().setDayCount(plugin.getDaySequence().getDayCount() + 1);
            plugin.getEndOfNightSequence().start();
            completeTask.cancel();
        }, 40);
    }

    public int getNightCount() {
        return nightCount;
    }

    public void setNightCount(int nightCount) {
    }
}

