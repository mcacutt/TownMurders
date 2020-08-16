package me.mcacutt.townmurders.players.PlayerData;


import me.mcacutt.townmurders.players.BaseGamePlayer;
import me.mcacutt.townmurders.players.Mafia;
import me.mcacutt.townmurders.players.Townie;
import me.mcacutt.townmurders.util.Utils;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerManager {

    private final List<String> rolesList = Utils.newStringSyncList();
    private final List<UUID> playersInGame = Utils.newUUIDSyncList();
    private final List<Player> playersDead = Utils.newPlayerSyncList();
    private final List<Player> playersAlive = Utils.newPlayerSyncList();
    private final List<Player> playersDeadLastNight = Utils.newPlayerSyncList();
    private final List<Player> playersInLobby = Utils.newPlayerSyncList();
    private final List<BaseGamePlayer> players = Collections.synchronizedList(new CopyOnWriteArrayList<>());
    private final Set<UUID> evils = Collections.synchronizedSet(new HashSet<>());
    private final Set<UUID> goods = Collections.synchronizedSet(new HashSet<>());
    private Map<UUID, BaseGamePlayer> roles = Collections.synchronizedMap(new HashMap<>());

    public List<UUID> getPlayersInGame() {
        return this.playersInGame;
    }
    public List<Player> getPlayersInLobby() {
        return this.playersInLobby;
    }

    public List<Player> getPlayersDead() {
        return this.playersDead;
    }
    public List<Player> getPlayersDeadLastNight() {
        return this.playersDeadLastNight;
    }
    public List<Player> getPlayersAlive() {
        return this.playersAlive;
    }

    public List<String> getRolesList() {
        return this.rolesList;
    }

    public Set<UUID> getEvils() {return evils; }
    public Set<UUID> getTownies() { return goods; }

    public BaseGamePlayer getBasePlayer(UUID uuid) {
        Optional<BaseGamePlayer> optional = players.stream().filter(baseGamePlayer ->
                baseGamePlayer.getUUID() == uuid).findFirst();
        return optional.orElse(null);
    }
}