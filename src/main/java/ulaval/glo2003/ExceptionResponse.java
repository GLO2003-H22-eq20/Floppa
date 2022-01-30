package ulaval.glo2003;

import org.eclipse.persistence.exceptions.JPARSErrorCodes;

public class ExceptionResponse {
    private final String code;
    private final String description;

    public ExceptionResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
