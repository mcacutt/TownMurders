package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameStartSequence {

    private final TownMurders plugin;

    public GameStartSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        plugin.getLobby().getEvils();
        plugin.getLobby().getSK();
        plugin.getLobby().assignRoles();
        for (Player p : plugin.getPlayerManager().getPlayersInLobby()) {
            p.sendTitle(ChatColor.BOLD + "Your Role Is...", "");
        }
        for (Player player : plugin.getPlayerManager().getPlayersInLobby()) {
            player.sendTitle(ChatColor.LIGHT_PURPLE + "" + plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole(), "");
        }
        for (Player player : plugin.getPlayerManager().getPlayersInLobby()) {
            plugin.getPlayerManager().getPlayersInGame().add(player.getUniqueId());
            plugin.getPlayerManager().getPlayersAlive().add(player);
            plugin.getFinalWill().givePersonalWill(player);
            plugin.getPlayerManager().getPlayersInLobby().remove(player);

        }
        plugin.getArena().setInProgress(true);
        plugin.getDaySequence().setDayCount(1);
        plugin.getDaySequence().start();
    }
}
