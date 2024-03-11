package error;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public abstract class AbstractException extends RuntimeException {

    private final transient IError error;
    private final transient Object[] params;

    protected AbstractException(IError error) {
        super(error.getMessage());
        this.error = error;
        this.params = new Object[0];
    }

    protected AbstractException(IError error, Object... params) {
        super(MessageFormat.format(error.getMessage(), params));
        this.error = error;
        this.params = params;
    }

}
