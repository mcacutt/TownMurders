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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Vigilante extends Townie {

    private final TownMurders plugin;
    private int shots = 3;

    public Vigilante(final TownMurders plugin) {
        this.plugin = plugin;
    }

    public static void giveBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The Reaper");
        bookMeta.setTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "Vigilante");
        ArrayList<String> pages = new ArrayList<>();
        final String nl = "\n";
        pages.add(ChatColor.BOLD + "" + ChatColor.GREEN + "Vigilante"
                + nl + ChatColor.RESET + "" + ChatColor.BLACK + "--------------------"
                + nl + nl + "You are GOOD"
                + nl + ChatColor.GOLD + "ACTION: "
                + nl + ChatColor.BLACK + "Night: Shoot your"
                + nl + "target but if they"
                + nl + "are a town member"
                + nl + "you'll die of grief!"
                + nl + ChatColor.MAGIC + "Win" + ChatColor.RESET + " Win " + ChatColor.MAGIC + "Win"
                + nl + ChatColor.RESET + "Kill all EVILS!"
        );
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public int getRemainingShots() {
        return shots;
    }
    public void removeShot() { shots--; }

    public void runRoleTask(Player player, Player target) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getRemainingShots() == 0) {
                    return;
                }
                if (!plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).isHealed()) {
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
                    target.sendMessage("You Were Shot By A Vigilante");
                } else {
                    target.sendMessage("You Were Attacked But Someone Nursed You Back To Health");
                    plugin.getPlayerManager().getBasePlayer(target.getUniqueId()).setHealed(false);
                }
                removeShot();
                if (player instanceof Townie) {
                    player.setHealth(0);
                    SpawnPoints spawns = new SpawnPoints(plugin);
                    spawns.tpPlayerToSpec(player);
                    plugin.getPlayerManager().getPlayersDead().add(player);
                    plugin.getPlayerManager().getPlayersDeadLastNight().add(player);
                    plugin.getPlayerManager().getEvils().remove(player);
                    ChatChannels.GLOBAL.removeFromChannel(player.getUniqueId());
                    ChatChannels.DEAD.addToChannel(player.getUniqueId());
                    if (Lobby.serialKiller == player.getUniqueId()) Lobby.serialKiller = null;
                    plugin.getPlayerManager().getTownies().remove(player);
                    player.sendMessage("You Shot A Town Member And Died Of Grief");
                }
            }
        };
    }
}
