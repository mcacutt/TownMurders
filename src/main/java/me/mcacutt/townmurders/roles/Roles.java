package me.mcacutt.townmurders.roles;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.evils.*;
import me.mcacutt.townmurders.roles.good.*;
import me.mcacutt.townmurders.roles.neutral.SerialKiller;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.html.Option;
import java.util.Optional;

public enum Roles {

    ESCORT(new Escort(JavaPlugin.getPlugin(TownMurders.class)), true, "Escort", 1),
    LOOKOUT(new Lookout(JavaPlugin.getPlugin(TownMurders.class)), true, "Lookout", 2),
    MEDIC(new Medic(JavaPlugin.getPlugin(TownMurders.class)), true, "Medic", 3),
    JAILOR(new Jailor(JavaPlugin.getPlugin(TownMurders.class)), true, "Jailor", 4),
    VIGILANTE(new Vigilante(JavaPlugin.getPlugin(TownMurders.class)), true, "Vigilante", 5),
    HUNTER(new Hunter(JavaPlugin.getPlugin(TownMurders.class)), true, "Hunter", 6),
    WHISPERER(null, true, "Whisperer", 7),
    SHERIFF(new Sheriff(JavaPlugin.getPlugin(TownMurders.class)), true, "Sheriff", 8),
    INVESTIGATOR(new Investigator(JavaPlugin.getPlugin(TownMurders.class)), true, "Investigator", 9),

    SILENCER(new Silencer(JavaPlugin.getPlugin(TownMurders.class)), false, "Silencer", 20),
    DISTRACTOR(new Distractor(JavaPlugin.getPlugin(TownMurders.class)), false, "Distractor", 21),
    ADVISOR(new Advisor(JavaPlugin.getPlugin(TownMurders.class)), false, "Advisor", 22),
    GRUNT(new Grunt(JavaPlugin.getPlugin(TownMurders.class)), false, "Grunt", 23),
    KINGPIN(new Kingpin(JavaPlugin.getPlugin(TownMurders.class)), false, "Kingpin", 24),

    SERIAL_KILLER(new SerialKiller(JavaPlugin.getPlugin(TownMurders.class)), null, "Serial Killer", 30);

    private final RoleActionBase roleAction;
    private final Optional<Boolean> good;
    private final String name;
    private final int roleNumber;

    Roles(RoleActionBase roleAction, Boolean good, String name, int roleNumber) {
        this.roleAction = roleAction;
        this.good = Optional.ofNullable(good);
        this.name = name;
        this.roleNumber = roleNumber;
    }

    public Optional<Boolean> isGood() {
        return good;
    }

    public String getName() {
        return name;
    }

    public int getRoleNumber() {
        return roleNumber;
    }

    public Optional<RoleActionBase> getRoleAction() {
        return Optional.ofNullable(roleAction);
    }
}


