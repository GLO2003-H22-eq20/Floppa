package ulaval.glo2003.controllers.exceptionMappers.response;

public class ExceptionResponse {
    private String code;
    private String description;

    public ExceptionResponse() {}

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