package me.mcacutt.townmurders.players.PlayerData;


import me.mcacutt.townmurders.players.BaseGamePlayer;
import me.mcacutt.townmurders.players.Mafia;

import java.util.*;

public class PlayerManager {

	private final List<UUID> playersInGame = new ArrayList<>();
	private final List<UUID> playersLeftGame = new ArrayList<>();
	private final Set<BaseGamePlayer> playerNumber = new LinkedHashSet<>();
	private final Set<Mafia> evils = new HashSet<>();
    public final List<String> rolesList = new ArrayList<>();
    public List<UUID> getPlayersInGame() { return this.playersInGame; }
	public List<UUID> getPlayersLeftGame() { return this.playersLeftGame; }
	public Set<Mafia> getEvils() { return evils; }
	public Set<BaseGamePlayer> getPlayerNumber() { return this.playerNumber; }

	public int getPlayerNumber(UUID uuid) {
		Optional<BaseGamePlayer> optional = playerNumber.stream().filter(baseGamePlayer -> baseGamePlayer.getUUID() == uuid).findFirst();
		return optional.map(baseGamePlayer -> new ArrayList<>(playerNumber).indexOf(baseGamePlayer)).orElse(-1);
	}
	public BaseGamePlayer getBasePlayer(UUID uuid) {
		Optional<BaseGamePlayer> optional = playerNumber.stream().filter(baseGamePlayer -> baseGamePlayer.getUUID() == uuid).findFirst();
		return optional.orElse(null);
	}
}