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
    private final List<UUID> playersLeftGame = Utils.newUUIDSyncList();
    private final Set<BaseGamePlayer> playerNumber = Collections.synchronizedSet(new LinkedHashSet<>());
    private final Set<Mafia> evils = Collections.synchronizedSet(new HashSet<>());
    private final Set<Townie> goods = Collections.synchronizedSet(new HashSet<>());

    public List<UUID> getPlayersInGame() {
        return this.playersInGame;
    }

    public List<Player> getPlayersInLobby() {
        return this.playersInLobby;
    }

    public List<Player> getPlayersDead() {
        return this.playersDead;
    }

    public List<Player> getPlayersAlive() {
        return this.playersAlive;
    }

    public List<String> getRolesList() {
        return this.rolesList;
    }

    public List<Player> getPlayersDeadLastNight() {
        return this.playersDeadLastNight;
    }

    public List<UUID> getPlayersLeftGame() {
        return this.playersLeftGame;
    }

    public Set<Mafia> getEvils() {
        return evils;
    }

    public Set<Townie> getTownies() {
        return goods;
    }

    public Set<BaseGamePlayer> getPlayerNumber() {
        return this.playerNumber;
    }

    public int getPlayerNumber(UUID uuid) {
        Optional<BaseGamePlayer> optional = playerNumber.stream().filter(baseGamePlayer -> baseGamePlayer.getUUID() == uuid).findFirst();
        return optional.map(baseGamePlayer -> new ArrayList<>(playerNumber).indexOf(baseGamePlayer)).orElse(-1);
    }

    public BaseGamePlayer getBasePlayer(UUID uuid) {
        Optional<BaseGamePlayer> optional = playerNumber.stream().filter(baseGamePlayer -> baseGamePlayer.getUUID() == uuid).findFirst();
        return optional.orElse(null);
    }
}