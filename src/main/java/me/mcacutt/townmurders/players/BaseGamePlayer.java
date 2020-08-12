package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Role;

import java.util.List;
import java.util.UUID;

public abstract class BaseGamePlayer {

    public abstract String getTeam();
    public abstract Role getRole();

    public abstract List<UUID> getVisits();
    public abstract void addVisit(UUID uuid);

    public abstract boolean muted();
    public abstract void setMuted(boolean muted);

    public abstract boolean isHealed();
    public abstract void setHealed(boolean healed);

    public abstract boolean isDead();

    public abstract boolean hasDefense();
    public abstract void setDefense(boolean defense);

    public abstract boolean isBlocked();
    public abstract void setBlocked(boolean blocked);

    public abstract boolean isJailed();
    public abstract void setJailed(boolean jailed);

    public abstract boolean isOnStand();
    public abstract void setOnStand(boolean onStand);

    public abstract boolean guilty();

}
