package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.sequences.StandSequence;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.IdentityHashMap;
import java.util.Map;

public class FinalVote {

    final Map<Player, Boolean> votes = new IdentityHashMap<>();
    private final Gui gui;
    private final TownMurders plugin;

    public FinalVote(TownMurders plugin) {
        this.plugin = plugin;
        gui = new Gui(plugin, 1, ChatColor.BLACK + "Guilty?");
    }

    void vote(Player player, Boolean guilty) {
        votes.put(player, guilty);
    }

    public int votes(Boolean guilty) {
        int count = 0;
        for (Boolean player : votes.values())
            if (player) count++;
        return count;
    }

    public Gui getGui() {
        addOptions();
        return gui;
    }

    public void addOptions() {
        gui.setItem(3, new GuiItem(new ItemBuilder(Material.IRON_AXE).setName(ChatColor.RED + "Guilty").build()));
        gui.setItem(7, new GuiItem(new ItemBuilder(Material.EMERALD).setName(ChatColor.GREEN + "Innocent").build(), event -> {
            event.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    Player player = (Player) event.getWhoClicked();
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Innocent")) {
                        vote(player, false);
                        gui.close(player);
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Guilty")) {
                        vote(player, true);
                        gui.close(player);
                    }
                }
            };
        }));
    }
}
