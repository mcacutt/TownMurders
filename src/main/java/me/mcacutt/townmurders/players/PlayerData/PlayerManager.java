package me.mcacutt.townmurders.players.PlayerData;


import me.mcacutt.townmurders.players.BaseGamePlayer;
import me.mcacutt.townmurders.players.Mafia;
import me.mcacutt.townmurders.players.Townie;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {

    public final List<String> rolesList = new ArrayList<>();
    private final List<UUID> playersInGame = new ArrayList<>();
    private final List<Player> playersDead = new ArrayList<>();
    private final List<Player> playersAlive = new ArrayList<>();
    private final List<Player> playersDeadLastNight = new ArrayList<>();
    private final List<Player> playersInLobby = new ArrayList<>();
    private final List<UUID> playersLeftGame = new ArrayList<>();
    private final Set<BaseGamePlayer> playerNumber = new LinkedHashSet<>();
    private final Set<Mafia> evils = new HashSet<>();
    private final Set<Townie> goods = new HashSet<>();

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