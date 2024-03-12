package error;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public abstract class AbstractException extends RuntimeException {

    private final transient IError error;
    private final transient String[] params;

    protected AbstractException(IError error) {
        super(error.getMessage());
        this.error = error;
        this.params = new String[0];
    }

    protected AbstractException(IError error, String... params) {
        super(MessageFormat.format(error.getMessage(), (Object[]) params));
        this.error = error;
        this.params = params;
    }

}
