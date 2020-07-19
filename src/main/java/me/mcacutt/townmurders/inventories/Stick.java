package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.Roles;
import me.mcacutt.townmurders.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class Stick extends ListenerBase {

    public Stick(final TownMurders plugin) {
        super(plugin);
    }

    public ItemStack getStick() {
        return new ItemBuilder(Material.STICK)
                .setName(ChatColor.GOLD + "Target Stick").build();
    }

    public void removeStick(Player player) {
        player.getInventory().remove(getStick());
    }

    @EventHandler
    public void playerTeleport(PlayerTeleportEvent event) {
        boolean day = Utils.isDay(event.getPlayer().getWorld());
        if (!(plugin.getPlayerManager().getBasePlayer(event.getPlayer().getUniqueId()).getRole() == Roles.WHISPERER)) {
            if (day) {
                boolean b1 = plugin.getPlayerManager().getBasePlayer(event.getPlayer().getUniqueId()).getRole() == Roles.JAILOR;
                if (!b1) {
                    removeStick(event.getPlayer());
                } else event.getPlayer().getInventory().addItem(getStick());
            } else {
                boolean b2 = plugin.getPlayerManager().getBasePlayer(event.getPlayer().getUniqueId()).getRole() == Roles.JAILOR;
                if (!b2) {
                    event.getPlayer().getInventory().addItem(getStick());
                } else {
                    removeStick(event.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR && event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if (event.getPlayer().getInventory().getItemInHand().isSimilar(getStick())) {
            new Target(plugin).getGui().open(event.getPlayer());
        }
    }
}
