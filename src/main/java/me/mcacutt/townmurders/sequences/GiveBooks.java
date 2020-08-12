package me.mcacutt.townmurders.sequences;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.evils.*;
import me.mcacutt.townmurders.roles.good.*;
import me.mcacutt.townmurders.roles.neutral.SerialKiller;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GiveBooks {

    private TownMurders plugin;

    public void giveBooks() {
        for (UUID uuid : plugin.getPlayerManager().getPlayersInGame()) {
            Player player = Bukkit.getPlayer(uuid);
            switch (plugin.getPlayerManager().getBasePlayer(player.getUniqueId()).getRole()) {
                case ADVISOR: Advisor.giveBook(player);
                case SERIAL_KILLER: SerialKiller.giveBook(player);
                case INVESTIGATOR: Investigator.giveBook(player);
                case WHISPERER: Whisperer.giveBook(player);
                case LOOKOUT: Lookout.giveBook(player);
                case DISTRACTOR: Distractor.giveBook(player);
                case KINGPIN: Kingpin.giveBook(player);
                case SHERIFF: Sheriff.giveBook(player);
                case SILENCER: Silencer.giveBook(player);
                case ESCORT: Escort.giveBook(player);
                case VIGILANTE: Vigilante.giveBook(player);
                case JAILOR: Jailor.giveBook(player);
                case HUNTER: Hunter.giveBook(player);
                case GRUNT: Grunt.giveBook(player);
                case MEDIC: Medic.giveBook(player);
            }
        }
    }
}