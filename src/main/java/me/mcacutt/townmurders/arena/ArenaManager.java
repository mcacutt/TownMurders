package me.mcacutt.townmurders.arena;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class ArenaManager {

    private static final Set<Arena> ARENAS = new HashSet<>();

    public void register(Arena arena) {
        ARENAS.add(arena);
    }

    public Arena getByPlayer(Player player) {
        return ARENAS.stream().filter(arena -> arena.getPlayers().contains(player.getUniqueId())).findFirst().orElse(null);
    }
}
