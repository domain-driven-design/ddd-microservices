package com.example.common.utils;

import com.example.common.error.BusinessException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.example.common.error.CommonError.VALIDATION_FAILED;

public class PreconditionUtil {

    @Getter
    private final List<Exception> errors = new ArrayList<>();
    private final Mode mode;

    private PreconditionUtil(Mode mode) {
        this.mode = mode;
    }

    /**
     * 创建一个以FAIL_FAST模式开始的PreconditionUtil实例
     * @return 一个新的PreconditionUtil实例
     */
    public static PreconditionUtil begin() {
        return new PreconditionUtil(Mode.FAIL_FAST);
    }

    /**
     * 创建一个指定模式的PreconditionUtil实例
     * @param mode 指定的验证模式
     * @return 一个新的PreconditionUtil实例
     */
    public static PreconditionUtil begin(Mode mode) {
        return new PreconditionUtil(mode);
    }

    /**
     * 检查指定的对象是否为null，如果是则根据模式抛出异常或记录错误
     * @param value 待检查的对象
     * @param exceptionSupplier 异常
     * @return 当前PreconditionUtil实例，支持链式调用
     */
    public <T extends RuntimeException> PreconditionUtil checkNotNull(Object value,
                                                                      Supplier<? extends T> exceptionSupplier) {
        if (Objects.isNull(value)) {
            handleException(exceptionSupplier.get());
        }
        return this;
    }

    /**
     * 检查表达式是否为true，如果不是则根据模式抛出异常或记录错误
     * @param expression 待检查的表达式
     * @param exceptionSupplier 异常
     * @return 当前PreconditionUtil实例，支持链式调用
     */
    public <T extends RuntimeException> PreconditionUtil checkArgument(boolean expression,
                                                                       Supplier<? extends T> exceptionSupplier) {
        if (!expression) {
            handleException(exceptionSupplier.get());
        }
        return this;
    }

    private <T extends RuntimeException> void handleException(T exception) {
        if (mode == Mode.FAIL_FAST) {
            throw exception;
        } else {
            errors.add(exception);
        }
    }

    /**
     * 验证并结束链式调用。如果是FAIL_DEFER模式并且存在错误，则抛出一个汇总异常
     */
    public void verify() {
        if (!errors.isEmpty()) {
            List<String> collectedErrors = errors.stream().map(Throwable::getMessage).collect(Collectors.toList());
            throw new BusinessException(VALIDATION_FAILED, collectedErrors);
        }
    }

    /**
     * 允许用户自定义异常处理方式，如果存在错误则使用提供的异常生成异常并抛出
     * @param exceptionSupplier 用于生成自定义异常
     * @param <T> 异常类型
     */
    public <T extends RuntimeException> void verify(Supplier<T> exceptionSupplier) {
        if (!errors.isEmpty()) {
            T exception = exceptionSupplier.get();
            List<String> collectedErrors = errors.stream().map(Throwable::getMessage).collect(Collectors.toList());
            exception.addSuppressed(new BusinessException(VALIDATION_FAILED, collectedErrors));
            throw exception;
        }
    }

    /**
     * 验证模式枚举，定义了FAIL_FAST快速失败和FAIL_DEFER延迟失败两种模式。
     */
    public enum Mode {
        FAIL_FAST,
        FAIL_DEFER;
    }

}
