package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Roles;

import java.util.List;
import java.util.UUID;

public abstract class BaseGamePlayer {

    public abstract List<UUID> getVisits();
    public abstract void addVisit(UUID uuid);
    public abstract boolean muted();
    public abstract boolean isDead();
    public abstract boolean isHealed();
    public abstract boolean isBlocked();
    public abstract boolean isJailed();
    public abstract boolean isOnStand();
    public abstract boolean hasDefense();
    public abstract void setOnStand(boolean onStand);
    public abstract void setBlocked(boolean blocked);
    public abstract void setMuted(boolean muted);
    public abstract void setJailed(boolean jailed);
    public abstract void addRole();
    public abstract Roles getRole();
    public abstract UUID getUUID();
    public abstract boolean guilty();

}
