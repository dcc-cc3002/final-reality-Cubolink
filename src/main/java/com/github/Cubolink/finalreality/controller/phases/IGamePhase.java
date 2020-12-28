package com.github.Cubolink.finalreality.controller.phases;

public interface IGamePhase {
    void nextPhase();

    void prevPhase();

    boolean isWaitingPhase();

    boolean isEnemyPhase();

    String getPhaseInfo();

    String[] getPhaseOptions();
}
