package me.mcacutt.townmurders.players;

import me.mcacutt.townmurders.roles.Role;

import java.util.List;
import java.util.UUID;

public interface BaseGamePlayer {

    String getTeam();
    Role getRole();

    List<UUID> getVisits();
    void addVisit(UUID uuid);

    boolean muted();
    void setMuted(boolean muted);

    boolean isHealed();
    void setHealed(boolean healed);

    boolean isDead();

    boolean hasDefense();
    void setDefense(boolean defense);

    boolean isBlocked();
    void setBlocked(boolean blocked);

    boolean isJailed();
    void setJailed(boolean jailed);

    boolean isOnStand();
    void setOnStand(boolean onStand);

    boolean guilty();

}
