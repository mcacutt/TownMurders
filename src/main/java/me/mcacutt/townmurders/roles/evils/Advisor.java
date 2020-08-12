package me.mcacutt.townmurders.roles.evils;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.Mafia;
import me.mcacutt.townmurders.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Advisor extends Mafia {

    private final TownMurders plugin;

    public Advisor(final TownMurders plugin) {
        this.plugin = plugin;
    }

    @Override
    public Role getRole() { return Role.ADVISOR; }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.RED + "Advisor");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.RED + "Advisor"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are EVIL"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Investigate"
                + nl + "your target and"
                + nl + "reveal their role"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all not in"
                + nl + "line with the Mafia"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void runRoleTask(Player player, Player target) {
        new BukkitRunnable() {
            @Override
            public void run() {
                String targetRole = plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).getRole().getRoleName();
                player.sendMessage("The Role Of Your Target Is: " + targetRole);
            }
        };
    }
}
