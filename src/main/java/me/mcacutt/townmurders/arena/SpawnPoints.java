package me.mcacutt.townmurders.arena;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpawnPoints {

    private final TownMurders plugin;

    public final Map<Player, Location> mapSpawns = new HashMap<>();
    private final Map<Player, Location> mapHomes = new HashMap<>();

    public SpawnPoints(TownMurders plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    public List<Location> getSpawns() {
        return (List<Location>) plugin.getDataManager().getDataConfig().get("spawns");
    }

    @SuppressWarnings("unchecked")
    public List<Location> getHomes() {
        return (List<Location>) plugin.getDataManager().getDataConfig().get("homes");
    }

    public Location getLobby() {
        return (Location) plugin.getDataManager().getDataConfig().get("lobby");
    }

    public Location getSpec() {
        return (Location) plugin.getDataManager().getDataConfig().get("spec");
    }

    public Location getStand() {
        return (Location) plugin.getDataManager().getDataConfig().get("stand");
    }

    public void assignSpawns() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; getSpawns().size() > i; i++) {
                mapSpawns.put(player, getSpawns().get(i));
            }
        }
    }

    public void assignHomes() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; getHomes().size() > i; i++) {
                mapHomes.put(player, getHomes().get(i));
            }
        }
    }

    public void tpPlayersToSpawns() {
        for (Player player : Bukkit.getOnlinePlayers())
            if (!plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isDead()) {
                player.teleport(mapSpawns.get(player));
            }
    }

    public void tpPlayersToHomes() {
        for (Player player : Bukkit.getOnlinePlayers())
            if (!plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isDead()) {
                player.teleport(mapHomes.get(player));
            }
    }

    public void tpPlayerToSpec(Player player) {
        player.teleport(getSpec());
    }

    public void tpPlayerToLobby(Player player) {
        player.teleport(getLobby());
    }

    public void tpPlayerToStand(Player player) {
        player.teleport(getStand());
    }
}
