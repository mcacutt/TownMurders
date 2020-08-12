package me.mcacutt.townmurders.players.chatchannels;

import org.bukkit.Bukkit;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public enum ChatChannels {

    DEAD("Dead"),
    MAFIA("Mafia"),
    GLOBAL("Town"),
    JAIL("Jail"),
    WHISPER("Whisper");

    private final String name;
    private final Set<UUID> playersInChannel = ConcurrentHashMap.newKeySet();

    ChatChannels(String name) { this.name = name; }

    public void addToChannel(UUID uuid) {
        playersInChannel.add(uuid);
    }

    public void removeFromChannel(UUID uuid) {
        playersInChannel.remove(uuid);
    }

    public boolean inChannel(UUID uuid) {
        return playersInChannel.contains(uuid);
    }

    public String getName() { return name; }

    public void sendMessage(String message) {
        playersInChannel.forEach(uuid -> Bukkit.getPlayer(uuid).sendMessage(message));
    }

}
