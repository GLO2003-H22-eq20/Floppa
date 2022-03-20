package ulaval.glo2003.application.offer;

import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Offer;

import java.util.Objects;
import java.util.Optional;

public class OfferFactory {
    private final Integer MIN_MESSAGE_LENGTH = 100;
    private final Integer PHONENUMBER_LENGTH = 11;
    private final String PHONENUMBER_REGEX = "[0-9]+";
    private final String EMAIL_REGEX = "^(.+)@(.+)[.](.+)$";

    public Offer createOffer(String productId,
                             String name,
                             String email,
                             String phoneNumber,
                             Double amount,
                             String message) {
        validateName(name);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validateAmount(amount);
        validateMessage(message);
        return new Offer(productId, name, email, phoneNumber, amount, message);
    }

    private void validateName(String name) {
        if (Optional.ofNullable(name).isEmpty()) {
            throw new MissingParameterException("Missing name");
        }
    }

    private void validateEmail(String email) {
        if (Optional.ofNullable(email).isEmpty()) {
            throw new MissingParameterException("Missing email");
        } else if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidParameterException("Invalid email format");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (Optional.ofNullable(phoneNumber).isEmpty()) {
            throw new MissingParameterException("Missing phone number");
        } else if (!Objects.equals(phoneNumber.length(), PHONENUMBER_LENGTH)) {
            throw new InvalidParameterException("Phone number is not 11 characters");
        } else if (!phoneNumber.matches(PHONENUMBER_REGEX)) {
            throw new InvalidParameterException("Phone number contains non-digits");
        }
    }

    private void validateAmount(Double amount) {
        if (Optional.ofNullable(amount).isEmpty()) {
            throw new MissingParameterException("Missing amount");
        }
        // TODO: validate that the amount is higher than the suggested price of the product
    }

    private void validateMessage(String message) {
        if (Optional.ofNullable(message).isEmpty()) {
            throw new MissingParameterException("Missing message");
        } else if (message.length() < MIN_MESSAGE_LENGTH) {
            throw new InvalidParameterException("Message is less than 100 characters.");
        }
    }
}
