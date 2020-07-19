package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.arena.Lobby;
import me.mcacutt.townmurders.util.Countdown;
import org.bukkit.entity.Player;

public class StandSequence {

    private final TownMurders plugin;
    public Boolean isRunning;
    private Countdown finalVoteCountdown;
    private Countdown finalWordsCountdown;

    public StandSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        isRunning = true;
        for (Player player : plugin.getPlayerManager().getPlayersAlive()) {
            plugin.getArrowListener().removeArrow(player);
            plugin.getFinalVote().getGui().open(player);
            player.sendMessage("type /revote to change your vote");
        }
        finalVoteCountdown = Countdown.of(completeTask -> {
            isRunning = false;
            if (plugin.getFinalVote().votes(true) >= Math.ceil(plugin.getPlayerManager().getPlayersAlive().size()) / 2) {
                startLastWords();
            }
            plugin.getSpawnPoints().tpPlayersToSpawns();
            plugin.getVoteSequence().start();
            plugin.getVoteSequence().getVoteCountdown().play();
            completeTask.cancel();
        }, 20);
    }

    public void startLastWords() {
        finalWordsCountdown = Countdown.of(completeTask -> {
            for (Player player : plugin.getPlayerManager().getPlayersAlive()) {
                if (plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isOnStand()) {
                    player.setHealth(0);
                    plugin.getSpawnPoints().tpPlayerToSpec(player);
                    plugin.getPlayerManager().getPlayersDead().add(player);
                    plugin.getPlayerManager().getPlayersAlive().remove(player);
                    plugin.getPlayerManager().getEvils().remove(player);
                    if (Lobby.serialKiller == player.getUniqueId())
                        Lobby.serialKiller = null;
                    plugin.getPlayerManager().getTownies().remove(player);

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
                    plugin.getNightSequence().start();
                }
            }
            completeTask.cancel();
        }, 10);
    }
}

