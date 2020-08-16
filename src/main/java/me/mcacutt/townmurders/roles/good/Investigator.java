package me.mcacutt.townmurders.roles.good;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.Townie;
import me.mcacutt.townmurders.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Investigator extends Townie {

    private final TownMurders plugin;

    public Investigator(final TownMurders plugin) {
        this.plugin = plugin;
    }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "Investigator");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.GREEN + "Investigator"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are GOOD"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Investigate"
                + nl + "and reveal the role"
                + nl + "of your target"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all EVILS!"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void runRoleTask(Player player, Player target) {
                Role targetRole = plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).getRole();
                if (targetRole == Role.DISTRACTOR || targetRole == Role.ESCORT || targetRole == Role.LOOKOUT) {
                    player.sendMessage("Your Target Could Be A Distractor, Escort or Lookout");
                }
                if (targetRole == Role.SHERIFF || targetRole == Role.INVESTIGATOR || targetRole == Role.ADVISOR) {
                    player.sendMessage("Your Target Could Be A Sheriff, Investigator or Advisor");
                }
                if (targetRole == Role.SILENCER || targetRole == Role.WHISPERER || targetRole == Role.KINGPIN) {
                    player.sendMessage("Your Target Could Be A Silencer, Whisperer or Kingpin");
                }
                if (targetRole == Role.JAILOR || targetRole == Role.VIGILANTE || targetRole == Role.GRUNT) {
                    player.sendMessage("Your Target Could Be A Jailor, Vigilante or Grunt");
                }
                if (targetRole == Role.HUNTER || targetRole == Role.MEDIC || targetRole == Role.SERIAL_KILLER) {
                    player.sendMessage("Your Target Could Be A Hunter, Medic or Serial Killer");
        };
    }
}
