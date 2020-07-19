package me.mcacutt.townmurders;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.mattstudios.mf.base.CommandManager;
import me.mcacutt.townmurders.arena.Arena;
import me.mcacutt.townmurders.arena.Lobby;
import me.mcacutt.townmurders.arena.SpawnPoints;
import me.mcacutt.townmurders.files.DataManager;
import me.mcacutt.townmurders.inventories.ArrowListener;
import me.mcacutt.townmurders.inventories.FinalVote;
import me.mcacutt.townmurders.inventories.Target;
import me.mcacutt.townmurders.inventories.Vote;
import me.mcacutt.townmurders.inventories.books.FinalWill;
import me.mcacutt.townmurders.players.PlayerData.PlayerManager;
import me.mcacutt.townmurders.sequences.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class TownMurders extends JavaPlugin implements PluginMessageListener {

    private PlayerManager playerManager;
    private Vote vote;
    private ArrowListener arrow;
    private VoteSequence voteSequence;
    private SpawnPoints spawnPoints;
    private StandSequence standSequence;
    private DaySequence daySequence;
    private DataManager dataManager;
    private GameStartSequence gameStartSequence;
    private Lobby lobby;
    private FinalWill finalWill;
    private Arena arena;
    private NightSequence nightSequence;
    private WinSequence winSequence;
    private EndOfNightSequence endOfNightSequence;
    private FinalVote finalVote;
    private MorningSequence morningSequence;
    private Target target;

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    @Override
    public void onEnable() {
        playerManager = new PlayerManager();
        arena = new Arena(this);
        finalWill = new FinalWill(this);
        daySequence = new DaySequence(this);
        spawnPoints = new SpawnPoints(this);
        standSequence = new StandSequence(this);
        voteSequence = new VoteSequence(this);
        gameStartSequence = new GameStartSequence(this);
        nightSequence = new NightSequence(this);
        dataManager = new DataManager(this);
        winSequence = new WinSequence(this);
        endOfNightSequence = new EndOfNightSequence(this);
        finalVote = new FinalVote(this);
        morningSequence = new MorningSequence(this);
        target = new Target(this);
        lobby = new Lobby(this);
        vote = new Vote(this);
        arrow = new ArrowListener(this);
        ListenerBase.registerAll();
        ListenerBase.clear();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\n\nTOWN MURDERS IS ENABLED\n\n");
        this.saveConfig();
        loadConfig();
        CommandManager commandManager = new CommandManager(this);
        commandManager.register(new Commands(this));
        commandManager.register(new Revote(this));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\n\nTOWN MURDERS IS DISABLED\n\n");
        this.saveConfig();
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("Connect")) {
        }
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public Vote getVote() {
        return vote;
    }

    public ArrowListener getArrowListener() {
        return arrow;
    }

    public VoteSequence getVoteSequence() {
        return voteSequence;
    }

    public StandSequence getStandSequence() {
        return standSequence;
    }

    public SpawnPoints getSpawnPoints() {
        return spawnPoints;
    }

    public DaySequence getDaySequence() {
        return daySequence;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public GameStartSequence getGameStartSequence() {
        return gameStartSequence;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public FinalWill getFinalWill() {
        return finalWill;
    }

    public Arena getArena() {
        return arena;
    }

    public NightSequence getNightSequence() {
        return nightSequence;
    }

    public WinSequence getWinSequence() {
        return winSequence;
    }

    public EndOfNightSequence getEndOfNightSequence() {
        return endOfNightSequence;
    }

    public FinalVote getFinalVote() {
        return finalVote;
    }

    public MorningSequence getMorningSequence() {
        return morningSequence;
    }

    public Target getTarget() {
        return target;
    }

}
