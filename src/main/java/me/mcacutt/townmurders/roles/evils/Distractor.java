package me.mcacutt.townmurders.roles.evils;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.Mafia;
import me.mcacutt.townmurders.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class Distractor extends Mafia {

    private final TownMurders plugin;

    public Distractor(final TownMurders plugin) {
        this.plugin = plugin;
    }

    @Override
    public Role getRole() { return Role.DISTRACTOR; }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.RED + "Distractor");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.RED + "Distractor"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are EVIL"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Distract your"
                + nl + "target to stop them"
                + nl + "from performing"
                + nl + "their action"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all not in"
                + nl + "line with the Mafia"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void runRoleTask(Player player, Player target) {
                plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).setBlocked(true);
    }

}
