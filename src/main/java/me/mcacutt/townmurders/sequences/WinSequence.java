package me.mcacutt.townmurders.sequences;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.util.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WinSequence {

    private final TownMurders plugin;


    public WinSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void mafiaWin() {
        for (UUID p : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(p).sendTitle(ChatColor.BOLD + "" + ChatColor.RED + "MAFIA WIN", "");
        }
        endGame();
    }

    public void townWin() {
        for (UUID p : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(p).sendTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "TOWNIE WIN", "");
        }
        endGame();
    }

    public void serialKillerWin() {
        for (UUID p : plugin.getPlayerManager().getPlayersInGame()) {
            Bukkit.getPlayer(p).sendTitle(ChatColor.BOLD + "" + ChatColor.BLUE + "SERIAL KILLER WINS", "");
        }
        endGame();
    }

    public void endGame() {
        plugin.getArena().setInProgress(false);
        plugin.getPlayerManager().getTownies().clear();
        plugin.getPlayerManager().getEvils().clear();
        plugin.getPlayerManager().getPlayersInGame().clear();
        plugin.getPlayerManager().getPlayersAlive().clear();
        plugin.getPlayerManager().getPlayerNumber().clear();
        plugin.getPlayerManager().getPlayersDead().clear();
        plugin.getPlayerManager().getPlayersDeadLastNight().clear();
        Countdown endCountdown = Countdown.of(completeTask -> {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("MafiaHub");
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                completeTask.cancel();
                }
            }, 20);
        }

}


