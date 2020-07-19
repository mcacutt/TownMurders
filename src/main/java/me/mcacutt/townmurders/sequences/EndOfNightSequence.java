package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;

import java.util.UUID;

public class EndOfNightSequence {

    private final TownMurders plugin;

    public EndOfNightSequence(TownMurders plugin) {
        this.plugin = plugin;
    }

    public void start() {
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
            plugin.getPlayerManager().getBasePlayer(uuid).getRole().getRoleAction()
                    .ifPresent(roleActionBase -> roleActionBase.runRoleTask(Bukkit.getPlayer(uuid),
                            plugin.getTarget().getTarget(Bukkit.getPlayer(uuid))));
        }
        plugin.getMorningSequence().start();
    }
}
