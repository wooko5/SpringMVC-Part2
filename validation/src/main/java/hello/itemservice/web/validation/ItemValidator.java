package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        if(item.getPrice() == null || item.getPrice() < 1000){
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if(item.getQuantity() == null || item.getQuantity() > 10000){
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        if(item.getPrice() != null && item.getQuantity() != null){
            int result = item.getPrice() * item.getQuantity();
            if(result <10000){
                errors.reject("totalPriceMn", new Object[]{10000, result}, null);
            }
        }
    }
}
