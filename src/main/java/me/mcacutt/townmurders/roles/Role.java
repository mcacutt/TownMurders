package me.mcacutt.townmurders.roles;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.players.BaseGamePlayer;
import me.mcacutt.townmurders.roles.evils.*;
import me.mcacutt.townmurders.roles.good.*;
import me.mcacutt.townmurders.roles.neutral.SerialKiller;
import org.bukkit.plugin.java.JavaPlugin;

public enum Role {

    ESCORT(new Escort(JavaPlugin.getPlugin(TownMurders.class)), "Escort", 1),
    LOOKOUT(new Lookout(JavaPlugin.getPlugin(TownMurders.class)), "Lookout", 2),
    MEDIC(new Medic(JavaPlugin.getPlugin(TownMurders.class)), "Medic", 3),
    JAILOR(new Jailor(JavaPlugin.getPlugin(TownMurders.class)),"Jailor", 4),
    VIGILANTE(new Vigilante(JavaPlugin.getPlugin(TownMurders.class)), "Vigilante", 5),
    HUNTER(new Hunter(JavaPlugin.getPlugin(TownMurders.class)), "Hunter", 6),
    WHISPERER(new Whisperer(JavaPlugin.getPlugin(TownMurders.class)), "Whisperer", 7),
    SHERIFF(new Sheriff(JavaPlugin.getPlugin(TownMurders.class)), "Sheriff", 8),
    INVESTIGATOR(new Investigator(JavaPlugin.getPlugin(TownMurders.class)),"Investigator", 9),

    SILENCER(new Silencer(JavaPlugin.getPlugin(TownMurders.class)),"Silencer", 21),
    DISTRACTOR(new Distractor(JavaPlugin.getPlugin(TownMurders.class)),"Distractor", 22),
    ADVISOR(new Advisor(JavaPlugin.getPlugin(TownMurders.class)),"Advisor", 23),
    GRUNT(new Grunt(JavaPlugin.getPlugin(TownMurders.class)),"Grunt", 24),
    KINGPIN(new Kingpin(JavaPlugin.getPlugin(TownMurders.class)),"Kingpin", 25),

    SERIAL_KILLER(new SerialKiller(JavaPlugin.getPlugin(TownMurders.class)), "Serial Killer", 40);

    private final String role;
    private final int roleNum;
    private BaseGamePlayer baseGamePlayer;

    Role(BaseGamePlayer baseGamePlayer, String role, int roleNumber) {
        this.role = role;
        this.roleNum = roleNumber;
        this.baseGamePlayer = baseGamePlayer;
    }

    public String getRoleName() {
        return role;
    }

    public int getRoleNum() {
        return roleNum;
    }

    public BaseGamePlayer getBaseGamePlayer() { return baseGamePlayer; }

}
