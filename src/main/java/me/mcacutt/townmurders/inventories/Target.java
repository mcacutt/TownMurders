package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.Roles;
import me.mcacutt.townmurders.roles.good.Jailor;
import me.mcacutt.townmurders.util.Skull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.IdentityHashMap;
import java.util.Map;

public class Target extends ListenerBase {

    final Map<Player, Player> roleVisits = new IdentityHashMap<>();
    private final Gui gui;

    public Target(TownMurders plugin) {
        super(plugin);
        gui = new Gui(plugin, 4, "Target");
    }

    public Player getTarget(Player player) {
        return roleVisits.get(player);
    }

    public Gui getGui() {
        addSkull();
        return gui;
    }

    public void addSkull() {
        for (Player player : plugin.getPlayerManager().getPlayersAlive()) {
            gui.setItem(32, new GuiItem(new ItemBuilder(Material.BARRIER).build()));
            gui.addItem(new GuiItem(new ItemBuilder(Skull.getPlayerSkull(
                    player.getDisplayName()
            )).setName(player.getDisplayName()).build(), event -> {
                event.setCancelled(true);
                Player player1 = (Player) event.getWhoClicked();
                Player target = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
                Roles role = plugin.getPlayerManager().getBasePlayer(player1.getUniqueId()).getRole();
                if (target.getDisplayName().equals("Barrier Block")) {
                    gui.close(player1);
                    return;
                }
                if (player1 == target && role != Roles.MEDIC && role != Roles.HUNTER) {
                    player1.sendMessage("You can't target yourself silly!");
                    gui.close(player);
                } else if (plugin.getPlayerManager().getBasePlayer(event.getWhoClicked().getUniqueId()).getRole() == Roles.JAILOR) {
                    roleVisits.put(player1, target);
                    ((Jailor) Roles.JAILOR.getRoleAction().get()).setJailedTarget(getTarget(player1));
                } else {
                    roleVisits.put(player1, target);
                };
            }
            ));
        }
    }
}



