package me.mcacutt.townmurders.arena;

import me.mcacutt.townmurders.ListenerBase;
import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.BaseGamePlayer;
import me.mcacutt.townmurders.players.Mafia;
import me.mcacutt.townmurders.players.Neutral;
import me.mcacutt.townmurders.players.Townie;
import me.mcacutt.townmurders.roles.Role;
import me.mcacutt.townmurders.util.Countdown;
import me.mcacutt.townmurders.util.RandomUtil;
import me.mcacutt.townmurders.util.UtilPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Lobby extends ListenerBase {

    private static final int PLAYERS_NEEDED = 12;
    UUID currentUUID;
    public static UUID serialKiller;
    Countdown gameStartCountdown;
    Townie townie;

    public Lobby(TownMurders plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();
        if (plugin.getArena().isInProgress()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
        } else {
            plugin.getPlayerManager().getTownies().add(uuid);
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().getPlayersInLobby().add(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getSpawnPoints().tpPlayerToLobby(player);
        if ((plugin.getPlayerManager().getPlayersInLobby().size() == PLAYERS_NEEDED)) {
            for (Player p : plugin.getPlayerManager().getPlayersInLobby()) {
                UtilPlayer.sendTitle(plugin.getPlayerManager().getPlayersInLobby(),
                        ChatColor.GREEN + "Game Starting In 10 Seconds", "", 20, 100, 20);
            }
            gameStartCountdown = Countdown.of(completeTask -> {
                plugin.getSpawnPoints().assignSpawns();
                plugin.getSpawnPoints().assignHomes();
                for (Player p : plugin.getPlayerManager().getPlayersInLobby()) {
                    p.sendMessage("Game Started");
                }
                plugin.getGameStartSequence().start();
                completeTask.cancel();
            }, 10);
        }
        for (Player p : plugin.getPlayerManager().getPlayersInLobby()) {
            String remaining = "" + (PLAYERS_NEEDED - plugin.getPlayerManager().getPlayersInLobby().size());
            p.sendMessage(ChatColor.RED + remaining + " Players Needed To Start Game");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();
        if (plugin.getPlayerManager().getPlayersInLobby().size() == PLAYERS_NEEDED) {
            plugin.getPlayerManager().getPlayersInLobby().remove(player);
            for (Player p : plugin.getPlayerManager().getPlayersInLobby()) {
                p.sendTitle("Game Start Cancelled", "");
            }
            gameStartCountdown.cancel();
            for (Player p : plugin.getPlayerManager().getPlayersInLobby()) {
                String remaining = "" + (PLAYERS_NEEDED - plugin.getPlayerManager().getPlayersInLobby().size());
                p.sendMessage(ChatColor.RED + remaining + " Players Needed To Start Game");
            }
        } else plugin.getPlayerManager().getPlayersInLobby().remove(player);
    }

    public void getEvils() {
        while (plugin.getPlayerManager().getEvils().size() < 4) {
            Random r = new Random();
            int result = r.nextInt(11);
            plugin.getPlayerManager().getEvils().add(plugin.getPlayerManager()
                    .getPlayersInLobby().get(result).getUniqueId());
            plugin.getPlayerManager().getTownies().remove(plugin.getPlayerManager()
                    .getPlayersInLobby().get(result).getUniqueId());
        }
    }

    public void getSK() {
        Random r = new Random();
        boolean result = r.nextBoolean();
        if (result) {
            int playerResult = RandomUtil.getRandom().nextInt(11);
            serialKiller = new ArrayList<UUID>(plugin.getPlayerManager().getTownies()).get(playerResult);
            plugin.getPlayerManager().getBasePlayer(serialKiller).setRole(Role.SERIAL_KILLER);
                plugin.getPlayerManager().getTownies().remove(serialKiller);
                plugin.getPlayerManager().getEvils().remove(serialKiller);
        }

    }

    public void assignRoles() {
        int result;
        for (Player player : plugin.getPlayerManager().getPlayersInLobby()) {
            if (!(plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getTeam().equals("Mafia"))) {
                result = 1 + RandomUtil.getRandom().nextInt(8);
            }
            else {
                result = 20 + RandomUtil.getRandom().nextInt(5);
            }
                for (Role role : Role.values()) {
                    if (role.getRoleNum() == result) {
                        plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).setRole(role);
                    }
                }
        }
    }
}
