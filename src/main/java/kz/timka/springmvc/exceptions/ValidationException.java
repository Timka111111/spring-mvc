package kz.timka.springmvc.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    private List<String> errorsFieldsMessage;
    public ValidationException(List<String> errorsFieldsMessage) {
        super(errorsFieldsMessage.stream().collect(Collectors.joining(", ")));
        this.errorsFieldsMessage = errorsFieldsMessage;
    }
}
