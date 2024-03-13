package com.example.common.statusmachine;

import java.util.Set;

public class StatusTransitionBuilder<E extends Enum<E>, T extends Enum<T>> {

    private final AbstractStatusMachine<E, T> statusMachine;

    public StatusTransitionBuilder(AbstractStatusMachine<E, T> statusMachine) {
        this.statusMachine = statusMachine;
    }

    public StatusTransitionBuilder<E, T> addTransition(E event, T from, T to) {
        statusMachine.addTransition(event, from, to);
        return this;
    }

    public StatusTransitionBuilder<E, T> addTransition(E event, Set<T> froms, T to) {
        statusMachine.addTransition(event, froms, to);
        return this;
    }

}
