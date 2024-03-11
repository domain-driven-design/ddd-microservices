package error;

import lombok.Getter;
import lombok.Setter;

/**
 * ClientException represents exceptions that occur due to client-side errors
 * during interaction with an API or a web service. This exception can be
 * thrown when the client sends a request that is incorrect,
 * or asks for resources or operations that are not valid given the current context.
 */
@Getter
@Setter
public class ClientException extends AbstractException {

    public ClientException(IError errorCode) {
        super(errorCode);
    }

    public ClientException(IError errorCode, Object... params) {
        super(errorCode, params);
    }

}
