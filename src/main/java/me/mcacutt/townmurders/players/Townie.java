package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Role;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Townie extends BaseGamePlayer {

    private List<UUID> visits = Collections.emptyList();
    private Role role;
    private boolean healed = false;
    private boolean blocked = false;
    private boolean muted = false;
    private boolean jailed = false;
    private boolean onStand = false;
    private boolean defense = false;

    @Override
    public String getTeam() { return "Townie"; }

    @Override
    public boolean guilty() {
        return false;
    }
    @Override
    public Role getRole() { return Role.ADVISOR; }

    @Override
    public List<UUID> getVisits() { return visits; }
    @Override
    public void addVisit(UUID uuid) {
        visits.add(uuid);
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isHealed() {
        return healed;
    }
    @Override
    public void setHealed(boolean healed) { this.healed = healed; }

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
    public void setDefense(boolean defense) {
        this.defense = defense;
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
