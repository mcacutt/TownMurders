package me.mcacutt.townmurders.players.chatchannels;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatChannelListener implements Listener {

    private final TownMurders plugin;

    public ChatChannelListener(TownMurders plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(plugin.getPlayerManager().getBasePlayer(event.getPlayer().getUniqueId()).muted()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You Are Silenced!");
            return;
        }
        for (ChatChannels value : ChatChannels.values()) {
            if(value.inChannel(event.getPlayer().getUniqueId())){
                event.setCancelled(true);
                if(value == ChatChannels.WHISPER) {
                    ChatChannels.GLOBAL.sendMessage(event.getMessage());
                    return;
                }
                value.sendMessage(event.getMessage());
                ChatChannels.WHISPER.sendMessage(event.getMessage());
            }
        }
    }
}
