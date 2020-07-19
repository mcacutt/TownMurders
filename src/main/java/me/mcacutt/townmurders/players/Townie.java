package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Roles;

import java.util.*;
import java.util.stream.Collectors;

public class Townie extends BaseGamePlayer {
    private Roles role;
    private final UUID uuid;
    private final Random rand = new Random();
    private final List<Roles> filtered = Arrays.stream(Roles.values()).filter(Roles::isGood).collect(Collectors.toList());
    private boolean healed = false;
    private boolean blocked = false;
    private boolean muted = false;
    private boolean jailed = false;
    private boolean onStand = false;
    private final List<UUID> visits = new ArrayList<>();

    public Townie(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean guilty() {
        return false;
    }

    @Override
    public List<UUID> getVisits() {
        return visits;
    }

    @Override
    public void addVisit(UUID uuid) {
        visits.add(uuid);
    }

    @Override
    public boolean isDead() {
        return false;
    }

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
    public Roles getRole() {
        return role;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

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
