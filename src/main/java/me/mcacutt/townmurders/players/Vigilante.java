package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Roles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vigilante extends BaseGamePlayer {
    private final List<UUID> visits = new ArrayList<>();
    private int shots = 3;
    private boolean healed = false;
    private boolean blocked = false;
    private boolean muted = false;
    private final boolean dead = false;
    private boolean jailed = false;
    private boolean onStand = false;

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
        return dead;
    }

    @Override
    public void setHealed(boolean healed) { this.healed = healed; }

    @Override
    public boolean isHealed() {
        return healed;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean hasDefense() {
        return false;
    }

    @Override
    public void addRole(Roles roles) {
    }

    @Override
    public Roles getRole() {
        return null;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    public int getRemainingShots() {
        return shots;
    }

    @Override
    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    @Override
    public boolean muted() {
        return muted;
    }

    public void removeShot() {
        shots--;
    }

    @Override
    public boolean isJailed() {
        return jailed;
    }

    @Override
    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    @Override
    public boolean isOnStand() {
        return onStand;
    }

    @Override
    public void setOnStand(boolean onStand) {
        this.onStand = onStand;
    }

}
