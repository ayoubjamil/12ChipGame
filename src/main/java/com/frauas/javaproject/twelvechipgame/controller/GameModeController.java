package com.frauas.javaproject.twelvechipgame.controller;

public class GameModeController {
    private boolean threePlayerMode;
    private boolean fourPlayerMode;

    public boolean isFourPlayerMode() {
        return fourPlayerMode;
    }

    public void setFourPlayerMode(boolean fourPlayerMode) {
        this.fourPlayerMode = fourPlayerMode;
    }

    public boolean isThreePlayerMode() {
        return threePlayerMode;
    }

    public void setThreePlayerMode(boolean threePlayerMode) {
        this.threePlayerMode = threePlayerMode;
    }

    public GameModeController( ) {
        threePlayerMode = false;
        fourPlayerMode = false;
    }
}
