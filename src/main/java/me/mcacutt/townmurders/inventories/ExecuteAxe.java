package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.Role;
import me.mcacutt.townmurders.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class ExecuteAxe extends ListenerBase {

    public boolean execute;

    public boolean getExecute() {
        return execute;
    }

    public ExecuteAxe(TownMurders plugin) {
        super(plugin);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (!Utils.isDay(event.getPlayer().getWorld())) {
            if (plugin.getPlayerManager().getBasePlayer(event.getPlayer().getUniqueId()).getRole() == Role.JAILOR) {
                event.getPlayer().getInventory().addItem(getAxe());
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
        if (event.getPlayer().getInventory().getItemInHand().isSimilar(getAxe())) {
            execute = !execute;
            if (execute) {
                event.getPlayer().sendMessage("You have decided to execute your prisoner!");
            }
            else event.getPlayer().sendMessage("You have changed your mind and decided " +
                    ChatColor.DARK_RED + "NOT " + "to execute your prisoner!");
        }
        }
    }

    private ItemStack getAxe() {
        return new ItemBuilder(Material.IRON_AXE).setName("Execute?").build();
    }

}
