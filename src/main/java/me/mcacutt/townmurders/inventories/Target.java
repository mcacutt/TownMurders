package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.Roles;
import me.mcacutt.townmurders.roles.good.Jailor;
import me.mcacutt.townmurders.util.Skull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

public class Target implements Listener {

    private final Gui gui;
    private final TownMurders plugin;
    final Map<Player, Player> roleVisits = new IdentityHashMap<>();

    public Target(TownMurders plugin) {
        this.plugin = plugin;
        gui = new Gui(plugin, 4, "Target");
    }

    void pair(Player player, Player target) {
        roleVisits.put(player, target);
    }

    Player getTarget(Player player) {
        return roleVisits.get(player);
    }

    public Gui getGui() {
        return gui;
    }

    public void addSkull() {
       for(int i=0; i<=11; i++){
           UUID uuid = new ArrayList<>(plugin.getPlayerManager().getPlayerNumber()).get(i).getUUID();
           gui.setItem(5, new GuiItem(new ItemBuilder(Material.BARRIER).build()));
           gui.setItem(10+i, new GuiItem(new ItemBuilder(Skull.getPlayerSkull(
                   Bukkit.getPlayer(uuid).getName()
           )).setName(Bukkit.getPlayer(uuid).getName()).build(),event -> {
               event.setCancelled(true);
               new BukkitRunnable() {
                   @Override
                   public void run() {
                       Player player = (Player) event.getWhoClicked();
                       Player target = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
                       Roles role = plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole();
                       if(target.getDisplayName() == "Barrier Block") {
                           gui.close(player);
                           return;
                       }
                       if(player == target && role != Roles.MEDIC && role != Roles.HUNTER) {
                           player.sendMessage("You can't target yourself silly!");
                           gui.close(player);
                       }
                       else if(plugin.getPlayerManager().getBasePlayer(event.getWhoClicked().getUniqueId()).getRole() == Roles.JAILOR) {
                           roleVisits.put(player, target);
                           ((Jailor) Roles.JAILOR.getRoleAction().get()).setJailedTarget(getTarget(player));
                       }
                       else {
                           roleVisits.put(player, target);
                           plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole().getRoleAction()
                                   .ifPresent(roleActionBase -> roleActionBase.runRoleTask(player, getTarget(player)));
                       }
                   }
               };
           }
           ));
       }
    }
}



