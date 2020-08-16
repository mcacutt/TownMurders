package me.mcacutt.townmurders.roles.good;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.arena.Lobby;
import me.mcacutt.townmurders.arena.SpawnPoints;
import me.mcacutt.townmurders.players.Townie;
import me.mcacutt.townmurders.players.chatchannels.ChatChannels;
import me.mcacutt.townmurders.roles.Role;
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

    public void runRoleTask(Player player, Player target) {
        if(plugin.getAxe().getExecute()) {
            this.jailedTarget = target;
            if (target == null) return;
            target.setHealth(0);
            SpawnPoints spawns = new SpawnPoints(plugin);
            spawns.tpPlayerToSpec(target);
            plugin.getPlayerManager().getPlayersDead().add(target);
            plugin.getPlayerManager().getPlayersAlive().remove(target);
            plugin.getPlayerManager().getPlayersDeadLastNight().add(target);
            plugin.getPlayerManager().getEvils().remove(target.getUniqueId());
            ChatChannels.GLOBAL.removeFromChannel(target.getUniqueId());
            ChatChannels.DEAD.addToChannel(target.getUniqueId());
            if (Lobby.serialKiller == target.getUniqueId()) Lobby.serialKiller = null;
            plugin.getPlayerManager().getTownies().remove(target.getUniqueId());
        }
        else if (plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).getRole() == Role.SERIAL_KILLER) {
            player.setHealth(0);
            SpawnPoints spawns = new SpawnPoints(plugin);
            spawns.tpPlayerToSpec(player);
            plugin.getPlayerManager().getPlayersDead().add(player);
            plugin.getPlayerManager().getPlayersAlive().remove(player);
            plugin.getPlayerManager().getPlayersDeadLastNight().add(player);
            plugin.getPlayerManager().getEvils().remove(player.getUniqueId());
            ChatChannels.GLOBAL.removeFromChannel(player.getUniqueId());
            ChatChannels.DEAD.addToChannel(player.getUniqueId());
            plugin.getPlayerManager().getTownies().remove(player.getUniqueId());
        }
    }
}
