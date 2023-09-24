package kz.timka.springmvc.validators;

import kz.timka.springmvc.dto.ProductDto;
import kz.timka.springmvc.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if(productDto.getPrice() < 1) {
            errors.add("цена продукта не может быть меньше 1");
        }

        if(productDto.getTitle().isBlank()) {
            errors.add("продукт не может иметь пустое название");
        }

        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
