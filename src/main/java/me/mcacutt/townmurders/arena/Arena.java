package me.mcacutt.townmurders.arena;

import me.mcacutt.townmurders.TownMurders;

public class Arena {

    private boolean inProgress = false;

    public Arena(final TownMurders plugin) {
    }

    public boolean isInProgress() {
        return this.inProgress;
    }

    public void setInProgress(boolean progress) {
        this.inProgress = progress;
    }

}
