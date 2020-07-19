package me.mcacutt.townmurders.players.chatchannels;

import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public enum ChatChannels {

    DEAD("Dead"),
    MAFIA("Mafia"),
    GLOBAL("Town"),
    JAIL("Jail"),
    WHISPER("Whisper");

    private final String name;
    private final Set<UUID> players = new HashSet<>();

    ChatChannels(String name) {
        this.name = name;
    }

    public void addToChannel(UUID uuid) {
        players.add(uuid);
    }

    public void removeFromChannel(UUID uuid) {
        players.remove(uuid);
    }

    public boolean inChannel(UUID uuid) {
        return players.contains(uuid);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        players.forEach(uuid -> Bukkit.getPlayer(uuid).sendMessage(message));
    }

}
