package me.mcacutt.townmurders.arena;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SpawnPoints {

    private final TownMurders plugin;

    public final Map<UUID, Location> mapSpawns = new HashMap<>();
    private final Map<UUID, Location> mapHomes = new HashMap<>();

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
            int i = 0;
            while (mapSpawns.get(player.getUniqueId()) == null) {
                Location value = getSpawns().get(i);
                if (!(mapSpawns.containsValue(value))) {
                    mapSpawns.put(player.getUniqueId(), getSpawns().get(i));
                    System.out.print(getSpawns().get(i));
                } else i++;
            }
        }
    }

    public void assignHomes () {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int i = 0;
            while (mapHomes.get(player.getUniqueId()) == null) {
                if (!(mapHomes.containsValue(getHomes().get(i)))) {
                    mapHomes.put(player.getUniqueId(), getHomes().get(i));
                    System.out.print(getHomes().get(i));
                } else i++;
            }
        }
    }


    public void tpPlayersToSpawns() {
        for (Player player : Bukkit.getOnlinePlayers())
            if (!plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isDead()) {
                player.teleport(mapSpawns.get(player.getUniqueId()));
            }
    }

    public void tpPlayersToHomes() {
        for (Player player : Bukkit.getOnlinePlayers())
            if (!plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isDead()) {
                player.teleport(mapHomes.get(player.getUniqueId()));
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
