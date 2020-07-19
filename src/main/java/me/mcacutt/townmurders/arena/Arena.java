package me.mcacutt.townmurders.arena;

import com.google.common.collect.ImmutableSet;
import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Arena {

    private final Set<UUID> players = new HashSet<>();
    private final TownMurders plugin;
    private boolean inProgress = false;

    public Arena(final TownMurders plugin) {
        this.plugin = plugin;
    }

    public ImmutableSet<UUID> getPlayers() {
        return ImmutableSet.copyOf(players);
    }


    public void lobbyWait() {
    }

    public boolean isInProgress() {
        return this.inProgress;
    }

    public void setInProgress(boolean progress) {
        this.inProgress = progress;
    }

}
