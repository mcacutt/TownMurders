package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.util.Countdown;
import org.bukkit.Bukkit;

import java.util.UUID;

public class VoteSequence {

    private final TownMurders plugin;
    private Countdown voteCountdown;

    public VoteSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
            if (!plugin.getPlayerManager().getBasePlayer(uuid).isDead()) {
                plugin.getArrowListener().giveArrow(Bukkit.getPlayer(uuid));
            }
        }
        voteCountdown = Countdown.of(completeTask -> {
            plugin.getNightSequence().setNightCount(plugin.getNightSequence().getNightCount() + 1);
            plugin.getNightSequence().start();
                completeTask.cancel();
    }, 20);
}

    public Countdown getVoteCountdown() { return voteCountdown; }

}
