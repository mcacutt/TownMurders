package me.mcacutt.townmurders;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.entity.Player;

@Command("revote")
public class Revote extends CommandBase {

    private final TownMurders plugin;

    public Revote(TownMurders plugin) {
        this.plugin = plugin;
    }

    @Default
    public void defaultCommand(final Player player) {
        if (!(plugin.getStandSequence().isRunning)) {
            return;
        }
        plugin.getFinalVote().getGui().open(player);
    }
}
