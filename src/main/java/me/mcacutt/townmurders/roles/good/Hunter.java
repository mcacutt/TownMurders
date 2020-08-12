package me.mcacutt.townmurders.roles.good;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.Townie;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Hunter extends Townie {

    private final TownMurders plugin;

    public Hunter(final TownMurders plugin) {
        this.plugin = plugin;
    }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "Hunter");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.GREEN + "Hunter"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are GOOD"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Investigate"
                + nl + "and reveal if any"
                + nl + "player next to"
                + nl + "your target, or"
                + nl + "your target"
                + nl + "is suspicious"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all EVILS!"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void runRoleTask(Player player, Player target, Player left, Player right) {
                if (plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).guilty() ||
                        plugin.getPlayerManager().getBasePlayer(left.getUniqueId()).guilty() ||
                        plugin.getPlayerManager().getBasePlayer(right.getUniqueId()).guilty()) {
                    player.sendMessage("You Found Someone In The Area Acting Suspicious");
                }
                player.sendMessage("You Found No Suspicious Behaviour In The Area");
    }
}

