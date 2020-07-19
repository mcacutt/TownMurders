package me.mcacutt.townmurders.inventories.books;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class FinalWill extends ListenerBase {

    public FinalWill(TownMurders plugin) {
        super(plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack writtenBook = getPersonalWill();
        ItemStack writableBook = new ItemStack(Material.BOOK_AND_QUILL);
        writableBook.getItemMeta().setDisplayName("Edit Will");
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR && event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!player.getItemInHand().isSimilar(writtenBook)) {
            return;
        }
        player.getInventory().setItemInHand(writableBook);
    }

    @EventHandler
    public void onBookSave(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        player.getItemInHand().setItemMeta(event.getNewBookMeta());
        player.getItemInHand().getItemMeta().setDisplayName("My Last Will");
    }

    public void givePlayerDeadWill(Player deadPlayer) {
        for (ItemStack item : deadPlayer.getInventory()) {
            if (!(item.getItemMeta().getDisplayName() == "My Last Will")) {
                return;
            }
            for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
                int num = 12;
                item.getItemMeta().setDisplayName(ChatColor.LIGHT_PURPLE + deadPlayer.getDisplayName() + "'s" + " Last Will");
                Bukkit.getPlayer(uuid).getInventory().addItem(item);
            }
        }
    }

    public void givePersonalWill(Player player) {
        if (!plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).isDead()) {
            getPersonalWill();
        }
    }

    private ItemStack getPersonalWill() {
        return new ItemBuilder(Material.WRITTEN_BOOK)
                .setName(ChatColor.LIGHT_PURPLE + "My Last Will")
                .setLore("Right click to edit").build();

    }
}
