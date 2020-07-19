package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Roles;

import java.util.*;
import java.util.stream.Collectors;

public class Mafia extends BaseGamePlayer {

    private final List<Roles> filtered = Arrays.stream(Roles.values()).filter(roles1 -> ! roles1.isGood()).collect(Collectors.toList());
    private final UUID uuid;
    private Roles role;
    private boolean muted = false;
    private boolean healed = false;
    private boolean blocked = false;
    private boolean jailed = false;
    private boolean onStand = false;
    private final Random rand = new Random();
    public Mafia(UUID uuid) {
        this.uuid = uuid;
    }
    private final List<UUID> visits = new ArrayList<>();

    @Override
    public boolean guilty() { return this.role != Roles.KINGPIN; }

    @Override
    public List<UUID> getVisits() {
        return visits;
    }

    @Override
    public void addVisit(UUID uuid) {
        visits.add(uuid);
    }

    @Override
    public boolean isDead() { return false; }

    @Override
    public boolean isHealed() { return healed; }

    @Override
    public boolean isBlocked() { return blocked; }

    @Override
    public boolean hasDefense() { return false; }

    @Override
    public void setOnStand(boolean onStand) { this.onStand = onStand; }

    @Override
    public void setBlocked(boolean blocked) { this.blocked = blocked; }

    @Override
    public void addRole() {
        int result = rand.nextInt(filtered.size() - 1);
        role = filtered.get(result);
    }

    @Override
    public Roles getRole() { return role; }

    @Override
    public UUID getUUID() { return uuid; }

    @Override
    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    @Override
    public boolean muted() {
        return muted;
    }

    @Override
    public void setJailed(boolean jailed) { this.jailed = jailed; }

    @Override
    public boolean isJailed() { return jailed; }

    @Override
    public boolean isOnStand() { return onStand; }

}
