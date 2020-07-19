package me.mcacutt.townmurders.util;

import me.mcacutt.townmurders.arena.ArenaManager;
import me.mcacutt.townmurders.players.BaseGamePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utils {

    public static boolean isBlocked(BaseGamePlayer player) {
        return player.isBlocked();
    }

    public static boolean isDay(World world) {
        return world.getTime() < 12541 || world.getTime() > 23459;
    }

    public static boolean isInArena(Player player) {
        return new ArenaManager().getByPlayer(player) == null;
    }
}
