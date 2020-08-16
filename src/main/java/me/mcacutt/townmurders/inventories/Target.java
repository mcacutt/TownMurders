package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.Role;
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

    public Map<Player, Player> getRoleVisits() {
        return roleVisits;
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
                Role role = plugin.getPlayerManager().getBasePlayer(player1.getUniqueId()).getRole();
                if (target.getDisplayName().equals("Barrier Block")) {
                    gui.close(player1);
                    return;
                }
                if (player1 == target && role != Role.MEDIC && role != Role.HUNTER) {
                    player1.sendMessage("You can't target yourself silly!");
                    gui.close(player);
                } else if (plugin.getPlayerManager().getBasePlayer(event.getWhoClicked().getUniqueId()).getRole() == Role.JAILOR) {
                    plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).setJailed(true);
                    roleVisits.put(player1, target);
                } else {
                    roleVisits.put(player1, target);
                }
            }
            ));
        }
    }
}



