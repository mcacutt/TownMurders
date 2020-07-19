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
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

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
        return gui;
    }

    public void addSkull() {
        for (Player player : plugin.getPlayerManager().getPlayersAlive()) {
            gui.setItem(32, new GuiItem(new ItemBuilder(Material.BARRIER).build()));
            gui.addItem(new GuiItem(new ItemBuilder(Skull.getPlayerSkull(
                    player.getDisplayName()
            )).setName(player.getDisplayName()).build(), event -> {
                event.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Player player = (Player) event.getWhoClicked();
                        Player target = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
                        Roles role = plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole();
                        if (target.getDisplayName() == "Barrier Block") {
                            gui.close(player);
                            return;
                        }
                        if (player == target && role != Roles.MEDIC && role != Roles.HUNTER) {
                            player.sendMessage("You can't target yourself silly!");
                            gui.close(player);
                        } else if (plugin.getPlayerManager().getBasePlayer(event.getWhoClicked().getUniqueId()).getRole() == Roles.JAILOR) {
                            roleVisits.put(player, target);
                            ((Jailor) Roles.JAILOR.getRoleAction().get()).setJailedTarget(getTarget(player));
                        } else {
                            roleVisits.put(player, target);
                        }
                    }
                };
            }
            ));
        }
    }
}



