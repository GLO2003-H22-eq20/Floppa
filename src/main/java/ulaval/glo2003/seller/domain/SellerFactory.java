package ulaval.glo2003.seller.domain;

import ulaval.glo2003.exceptions.InvalidParameterException;
import ulaval.glo2003.exceptions.MissingParameterException;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class SellerFactory {
    public Seller createSeller(String name, String bio, String birthDate) {
        validateName(name);
        validateBio(bio);
        validateBirthDate(birthDate);

        return new Seller(UUID.randomUUID(), name, bio, LocalDate.parse(birthDate), Instant.now());
    }

    private void validateName(String name) {
        if (name == null) {
            throw new MissingParameterException("Missing name");
        } else if (name.isBlank()) {
            throw new InvalidParameterException("Name is empty");
        }
    }

    private void validateBio(String bio) {
        if (bio == null) {
            throw new MissingParameterException("Missing bio");
        } else if (bio.isBlank()) {
            throw new InvalidParameterException("Bio is empty");
        }
    }

    private void validateBirthDate(String birthDate) {
        LocalDate testingDate;
        if (birthDate == null) {
            throw new MissingParameterException("Missing birthdate");
        }
        try {
            testingDate = LocalDate.parse(birthDate);
        }
        catch (DateTimeException e) {
            throw new InvalidParameterException("Birth date is not valid");
        }
        if (testingDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidParameterException("Seller must be at least 18 years old");
        }
    }
}
