package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.util.Countdown;
import me.mcacutt.townmurders.util.UtilPlayer;
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
       // GiveBooks books = new GiveBooks();
        //books.giveBooks();
        for (Player player : plugin.getPlayerManager().getPlayersInLobby()) {
            UtilPlayer.sendTitle(plugin.getPlayerManager().getPlayersInLobby(), ChatColor.BOLD + "Your Role Is...", ChatColor.GOLD + "" + plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole(), 20, 100, 20);
            plugin.getPlayerManager().getPlayersInGame().add(player.getUniqueId());
            plugin.getPlayerManager().getPlayersAlive().add(player);
            plugin.getFinalWill().givePersonalWill(player);
            plugin.getPlayerManager().getPlayersInLobby().remove(player);
        }
        Countdown roleTitles = Countdown.of(completeTask -> {
            plugin.getArena().setInProgress(true);
            plugin.getDaySequence().setDayCount(1);
            plugin.getDaySequence().start();
            completeTask.cancel();
        } , 3);
    }
}
