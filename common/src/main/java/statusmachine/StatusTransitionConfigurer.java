package statusmachine;

public interface StatusTransitionConfigurer<E extends Enum<E>, T extends Enum<T>> {

    void configure(StatusTransitionBuilder<E, T> builder);

}
