package me.mcacutt.townmurders.roles.evils;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.arena.Lobby;
import me.mcacutt.townmurders.arena.SpawnPoints;
import me.mcacutt.townmurders.players.Mafia;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Grunt extends Mafia {

    private final TownMurders plugin;

    public Grunt(final TownMurders plugin) {
        this.plugin = plugin;
    }

    @Override
    public Role getRole() { return Role.GRUNT; }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.RED + "Grunt");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.RED + "Grunt"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are EVIL"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Kill"
                + nl + "the target your boss"
                + nl + "orders you to or, if"
                + nl + "the boss is dead,"
                + nl + "your own target"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all not in"
                + nl + "line with the Mafia"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void runRoleTask(Player player, Player target) {
        final AtomicReference<Player> kingpinTarget = new AtomicReference<>(target);
                if (!((Role.KINGPIN.getBaseGamePlayer().getRole()) == null)) {
                    kingpinTarget.set(Kingpin.getKingpinTarget());
                }
                kingpinTarget.get().setHealth(0);
                SpawnPoints spawns = new SpawnPoints(plugin);
                spawns.tpPlayerToSpec((Player) kingpinTarget);
                plugin.getPlayerManager().getPlayersDead().add((Player) kingpinTarget);
                plugin.getPlayerManager().getPlayersAlive().remove(kingpinTarget);
                plugin.getPlayerManager().getPlayersDeadLastNight().add((Player) kingpinTarget);
                plugin.getPlayerManager().getEvils().remove(kingpinTarget);
                ChatChannels.GLOBAL.removeFromChannel(((Player) kingpinTarget).getUniqueId());
                ChatChannels.DEAD.addToChannel(((Player) kingpinTarget).getUniqueId());
                if (Lobby.serialKiller == ((Player) kingpinTarget).getUniqueId()) Lobby.serialKiller = null;
                plugin.getPlayerManager().getTownies().remove(kingpinTarget);
    }

}
