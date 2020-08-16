package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EndOfNightSequence {

    private final TownMurders plugin;

    public EndOfNightSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        for (Player player : plugin.getPlayerManager().getPlayersAlive()) {
            Player target = plugin.getTarget().getTarget(player);
            plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole().getBaseGamePlayer().runRoleTask(player, target);
        }
        plugin.getMorningSequence().start();
    }
}
