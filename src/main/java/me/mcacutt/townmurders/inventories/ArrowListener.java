package me.mcacutt.townmurders.inventories;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArrowListener extends ListenerBase {

    public ArrowListener(TownMurders plugin) {
        super(plugin);
    }

    public ItemStack getArrow() {
        return new ItemBuilder(Material.ARROW)
                .setName(ChatColor.GOLD + "Vote").build();
    }

    public void removeArrow(Player player) {
        player.getInventory().remove(getArrow());
    }

    public void giveArrow(Player player) {
        player.getInventory().addItem(getArrow());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
        if (event.getPlayer().getInventory().getItemInHand().isSimilar(getArrow())) {
            plugin.getVote().getGui().open(event.getPlayer());
        }
        }
    }
}
