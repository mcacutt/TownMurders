package me.mcacutt.townmurders.players.PlayerData;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.files.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpawnPoints {

    private final DataManager data;
    private final TownMurders plugin;

    private Map<Player, Location> mapSpawns = new HashMap<>();

    public SpawnPoints(DataManager data, TownMurders plugin) {
        this.data = data;
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    public List<Location> getSpawns() {
        return (List<Location>) data.getConfig().get("spawns");
    }

    public void assignSpawns() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            for(int i=0;getSpawns().size()>i;i++) {
                mapSpawns.put(player, getSpawns().get(i));
            }
        }
    }

    public void tpPlayersToSpawns(){
        for(Player player : Bukkit.getOnlinePlayers())
        if(!plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isDead()) {
            player.teleport(mapSpawns.get(player));
        }

    }

    public void tpPToStand(){}


}
