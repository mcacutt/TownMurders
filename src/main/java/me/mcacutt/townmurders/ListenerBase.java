package me.mcacutt.townmurders;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.List;

public class ListenerBase implements Listener {
    private static final List<ListenerBase> SET = Lists.newArrayList();

    protected final TownMurders plugin;

    public ListenerBase(TownMurders plugin) {
        this.plugin = plugin;
        SET.add(this);
    }

    public static void registerAll() {
        SET.forEach(ListenerBase::register);
    }

    public static void clear() {
        SET.clear();
    }

    void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}