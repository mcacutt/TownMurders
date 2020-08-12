package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.util.Skull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.IdentityHashMap;
import java.util.Map;

public class Vote extends ListenerBase {

    final Map<Player, Player> votes = new IdentityHashMap<>();
    private final Gui gui;

    public Vote(TownMurders plugin) {
        super(plugin);
        gui = new Gui(plugin, 4, "Vote");
    }

    void vote(Player player, Player target) {
        votes.put(player, target);
    }

    public int votes(Player target) {
        int count = 0;
        for (Player player : votes.values())
            if (player == target) count++;
        return count;
    }

    public Gui getGui() {
        addSkull();
        return gui;
    }

    public void addSkull() {
        for (Player player : plugin.getPlayerManager().getPlayersAlive()) {
            gui.addItem(new GuiItem(new ItemBuilder(Skull.getPlayerSkull(
                    player.getDisplayName()
            )).setName(player.getDisplayName()).build(), event -> {
                event.setCancelled(true);
                Player player1 = (Player) event.getWhoClicked();
                Player target = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
                if (player1 == target) {
                    player1.sendMessage("You can't vote for yourself silly!");
                    return;
                }
                vote(player1, target);
                if (votes(target) >= (Math.ceil(plugin.getPlayerManager().getPlayersAlive().size()) / 2)) {
                    plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).setOnStand(true);
                    plugin.getSpawnPoints().tpPlayerToStand(target);
                }
                plugin.getVoteSequence().getVoteCountdown().pause();
                plugin.getStandSequence().start();
            }
            ));
        }
    }
}
