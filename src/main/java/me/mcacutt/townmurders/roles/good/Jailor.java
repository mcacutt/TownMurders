package me.mcacutt.townmurders.roles.good;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.arena.Lobby;
import me.mcacutt.townmurders.arena.SpawnPoints;
import me.mcacutt.townmurders.players.Townie;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.UUID;

public class Jailor extends Townie {

    private final TownMurders plugin;
    private Player jailedTarget;

    public Jailor(final TownMurders plugin) {
        this.plugin = plugin;
    }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "Jailor");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.GREEN + "Jailor"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are GOOD"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Day: Jail a player"
                + nl + "Night: Interrogate"
                + nl + "your target and"
                + nl + "execute them if you"
                + nl + "find them suspicious"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all EVILS!"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public void setJailedTarget(Player jailedTarget) {
        this.jailedTarget = jailedTarget;
    }

    private void runRoleTask2(Player player, Player jailedTarget) {
        this.jailedTarget = jailedTarget;
        if (jailedTarget == null) return;
        jailedTarget.setHealth(0);
        SpawnPoints spawns = new SpawnPoints(plugin);
        spawns.tpPlayerToSpec(jailedTarget);
        plugin.getPlayerManager().getPlayersDead().add(jailedTarget);
        plugin.getPlayerManager().getPlayersAlive().remove(jailedTarget);
        plugin.getPlayerManager().getPlayersDeadLastNight().add(jailedTarget);
        plugin.getPlayerManager().getEvils().remove(jailedTarget);
        ChatChannels.GLOBAL.removeFromChannel(jailedTarget.getUniqueId());
        ChatChannels.DEAD.addToChannel(jailedTarget.getUniqueId());
        if (Lobby.serialKiller == jailedTarget.getUniqueId()) Lobby.serialKiller = null;
        plugin.getPlayerManager().getTownies().remove(jailedTarget);
    }
}
