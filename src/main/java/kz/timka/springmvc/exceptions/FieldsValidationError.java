package kz.timka.springmvc.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FieldsValidationError {
    private List<String> errorFieldsMessage;

    public FieldsValidationError(List<String> errorFieldsMessage) {
        this.errorFieldsMessage = errorFieldsMessage;
    }
}
