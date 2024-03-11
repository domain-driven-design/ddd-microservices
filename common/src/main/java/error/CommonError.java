package error;

public enum CommonError implements IError {

    BAD_REQUEST(400, "请求参数异常"),
    UNAUTHORIZED_ACCESS(401, "权限验证未通过"),
    FORBIDDEN_ACCESS(403, "无权访问"),
    RESOURCE_NOT_FOUND(404, "资源不存在"),
    SYSTEM_INTERNAL_ERROR(500, "系统异常"),
    GATEWAY_ERROR(502, "网关异常"),
    SERVER_NOT_AVAILABLE(503, "服务不可用");

    private final int errorCodeNumber;
    private final String message;

    CommonError(int errorCodeNumber, String message) {
        this.errorCodeNumber = errorCodeNumber;
        this.message = message;
    }

    @Override
    public ErrorCodePrefix getErrorCodePrefix() {
        return ErrorCodePrefix.COMMON;
    }

    @Override
    public int getErrorCodeNumber() {
        return this.errorCodeNumber;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
