package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.arena.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MorningSequence {

    private final TownMurders plugin;

    public MorningSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        for (Player player : plugin.getPlayerManager().getPlayersDeadLastNight()) {
            plugin.getFinalWill().givePlayerDeadWill(player);
            for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
                Bukkit.getPlayer(uuid).sendTitle(ChatColor.BOLD + "" + ChatColor.DARK_RED + player.getDisplayName() + " Died Last Night", "");
            }
            plugin.getPlayerManager().getPlayersDeadLastNight().clear();

            if (plugin.getPlayerManager().getEvils().isEmpty() &&
                    Lobby.serialKiller == null) {
                plugin.getWinSequence().townWin();
            } else if (plugin.getPlayerManager().getEvils().isEmpty() &&
                    plugin.getPlayerManager().getPlayersAlive().size() == 1) {
                for (Player lastPlayer : plugin.getPlayerManager().getPlayersAlive()) {
                    if (Lobby.serialKiller == lastPlayer.getUniqueId())
                        plugin.getWinSequence().serialKillerWin();
                }
            } else if (!(plugin.getPlayerManager().getEvils().isEmpty()) &&
                    Lobby.serialKiller == null &&
                    plugin.getPlayerManager().getTownies().isEmpty()) {
                plugin.getWinSequence().mafiaWin();
            }
            plugin.getDaySequence().start();
        }
    }
}
