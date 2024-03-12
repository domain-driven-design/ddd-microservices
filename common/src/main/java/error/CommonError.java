package error;

public enum CommonError implements IError {

    BAD_REQUEST("请求参数异常"),
    UNAUTHORIZED_ACCESS("权限验证未通过"),
    FORBIDDEN_ACCESS("无权访问"),
    RESOURCE_NOT_FOUND("资源不存在"),
    SYSTEM_INTERNAL_ERROR("系统异常"),
    GATEWAY_ERROR("网关异常"),
    SERVER_NOT_AVAILABLE("服务不可用");

    private final String message;

    CommonError(String message) {
        this.message = message;
    }

    @Override
    public ErrorGroup getErrorGroup() {
        return ErrorGroup.COMMON;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getSubCode() {
        return this.name();
    }

}
