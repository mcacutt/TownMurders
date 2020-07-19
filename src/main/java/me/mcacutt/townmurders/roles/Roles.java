package me.mcacutt.townmurders.roles;

import me.mcacutt.townmurders.TownMurders;
import me.mcacutt.townmurders.roles.evils.*;
import me.mcacutt.townmurders.roles.good.*;
import me.mcacutt.townmurders.roles.good.Vigilante;
import me.mcacutt.townmurders.roles.neutral.SerialKiller;

import javax.management.relation.Role;
import java.util.Optional;

public enum Roles {

	KINGPIN(new Kingpin(TownMurders.getInstance()), false, "Kingpin"),
	GRUNT(new Grunt(TownMurders.getInstance()), false, "Grunt"),
	ADVISOR(new Advisor(TownMurders.getInstance()), false, "Advisor"),
	DISTRACTOR(new Distractor(TownMurders.getInstance()), false, "Distractor"),
	SILENCER(new Silencer(TownMurders.getInstance()), false, "Silencer"),
	INVESTIGATOR(new Investigator(TownMurders.getInstance()), true, "Investigator"),
	SHERIFF(new Sheriff(TownMurders.getInstance()), true, "Sheriff"),
	WHISPERER(null, true, "Whisperer"),
	HUNTER(new Hunter(TownMurders.getInstance()), true, "Hunter"),
	VIGILANTE(new Vigilante(TownMurders.getInstance()), true, "Vigilante"),
	JAILOR(new Jailor(TownMurders.getInstance()), true, "Jailor"),
	SERIAL_KILLER(new SerialKiller(TownMurders.getInstance()), false, "Serial Killer"),
	MEDIC(new Medic(TownMurders.getInstance()), true, "Medic"),
	LOOKOUT(new Lookout(TownMurders.getInstance()), true, "Lookout"),
	ESCORT(new Escort(TownMurders.getInstance()), true, "Escort");


	private final RoleActionBase roleAction;
	private final boolean good;
	private final String name;

	Roles(RoleActionBase roleAction, boolean good, String name) {
		this.roleAction = roleAction;
		this.good = good;
		this.name = name;
	}

	public boolean isGood() {
		return good;
	}

	public String getName() {
		return name;
	}

	public Optional<RoleActionBase> getRoleAction() { return Optional.ofNullable(roleAction); }
	}


