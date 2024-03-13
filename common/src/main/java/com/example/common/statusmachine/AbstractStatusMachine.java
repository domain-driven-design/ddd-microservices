package com.example.common.statusmachine;

import com.example.common.error.BusinessException;
import lombok.Getter;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.example.common.error.CommonError.MAP_KEY_NOT_EXIST;
import static com.example.common.error.CommonError.NULL_OBJECT;

public abstract class AbstractStatusMachine<E extends Enum<E>, T extends Enum<T>> {

    @Getter
    private final Map<E, EnumMap<T, T>> transition;

    protected AbstractStatusMachine(Class<E> eventClass, Class<T> statusClass,
                                    StatusTransitionConfigurer<E, T> configurer) {
        this.transition = new EnumMap<>(eventClass);
        EnumSet.allOf(eventClass).forEach(event -> transition.put(event, new EnumMap<>(statusClass)));
        configureTransitions(configurer);
    }

    private void configureTransitions(StatusTransitionConfigurer<E, T> configurer) {
        StatusTransitionBuilder<E, T> builder = new StatusTransitionBuilder<>(this);
        configurer.configure(builder);
    }

    protected void addTransition(E event, Set<T> froms, T to) {
        checkTransition(event);
        froms.forEach(from -> transition.get(event).put(from, to));
    }

    protected void addTransition(E event, T from, T to) {
        checkTransition(event);
        transition.get(event).put(from, to);
    }

    public T getNextStatus(E event, T currentStatus) {
        T nextStatus = transition.get(event).get(currentStatus);
        if (Objects.isNull(nextStatus)) {
            throw new BusinessException(NULL_OBJECT, currentStatus.toString());
        }
        return nextStatus;
    }

    public boolean isFinalStatus(T status) {
        for (Map.Entry<E, EnumMap<T, T>> transitionEntry : transition.entrySet()) {
            if (transitionEntry.getValue().containsKey(status)) {
                // 如果给定状态作为起始状态存在，表示它不是最终状态
                return false;
            }
        }
        // 如果给定状态不作为任何起始状态，那么它是最终状态
        return true;
    }

    public void checkTransition(E event) {
        if (!transition.containsKey(event)) {
            throw new BusinessException(MAP_KEY_NOT_EXIST, event.toString());
        }
    }

}
