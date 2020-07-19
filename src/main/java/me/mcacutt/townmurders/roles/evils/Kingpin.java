package me.mcacutt.townmurders.roles.evils;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.arena.Lobby;
import me.mcacutt.townmurders.arena.SpawnPoints;
import me.mcacutt.townmurders.files.DataManager;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.RoleActionBase;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Kingpin extends RoleActionBase {

    private final TownMurders plugin;
    private Player kingpinTarget;

    public Kingpin(final TownMurders plugin) {
        this.plugin = plugin;
    }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.RED + "Kingpin");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.RED + "Kingpin"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are EVIL"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Order"
                + nl + "your Grunt to kill"
                + nl + "your target."
                + nl + "you will show up as"
                + nl + "innocent if checked"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all not in"
                + nl + "line with the Mafia"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void runRoleTask(Player player, Player target) {
        kingpinTarget = target;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!plugin.getPlayerManager().rolesList.contains("GRUNT")) {
                    target.setHealth(0);
                    SpawnPoints spawns = new SpawnPoints(plugin);
                    spawns.tpPlayerToSpec(target);
                    plugin.getPlayerManager().getPlayersDead().add(target);
                    plugin.getPlayerManager().getPlayersAlive().remove(target);
                    plugin.getPlayerManager().getPlayersDeadLastNight().add(target);
                    plugin.getPlayerManager().getEvils().remove(target);
                    ChatChannels.GLOBAL.removeFromChannel(target.getUniqueId());
                    ChatChannels.DEAD.addToChannel(target.getUniqueId());
                    if (Lobby.serialKiller == target.getUniqueId()) Lobby.serialKiller = null;
                    plugin.getPlayerManager().getTownies().remove(target);
                }
            }
        };
    }

    public Player getKingpinTarget() {
        return this.kingpinTarget;
    }
}