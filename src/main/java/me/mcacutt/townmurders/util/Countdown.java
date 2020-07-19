package me.mcacutt.townmurders.util;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Countdown {

    private final Consumer<Countdown> consumer;
    private final BukkitTask timerTask;
    private final AtomicInteger countdown;
    private final TownMurders plugin;
    private boolean isPaused;
    private boolean cancelled;

    private Countdown(int seconds, Consumer<Countdown> consumer) {
        cancelled = false;
        isPaused = false;
        plugin = JavaPlugin.getPlugin(TownMurders.class);
        countdown = new AtomicInteger(seconds-1);
        this.consumer = consumer;
        timerTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if(!cancelled) {
                if (countdown.get() == 0) {
                    if (consumer != null) {
                        consumer.accept(Countdown.this);
                    }
                    return;
                }
                if (!isPaused) {
                    countdown.set(countdown.get() - 1);
                }
            }
        },20L,20L);
    }


    public void cancel() {
        cancelled = true;
    }

    public boolean isPaused() { return isPaused; }

    public void pause() { isPaused = true; }

    public void play() { isPaused = false; }

    public void cancel(Runnable runnable) {
        cancel();
        Bukkit.getScheduler().runTask(plugin, runnable);
    }

    public static Countdown of(Consumer<Countdown> consumer, int seconds) {
        return new Countdown(seconds, consumer);
    }

}