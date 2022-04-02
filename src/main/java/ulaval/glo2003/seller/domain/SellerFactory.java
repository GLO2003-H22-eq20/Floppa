package ulaval.glo2003.seller.domain;

import ulaval.glo2003.exceptions.InvalidParameterException;
import ulaval.glo2003.exceptions.MissingParameterException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class SellerFactory {
    public Seller createSeller(String name, String bio, LocalDate birthDate) {
        validateName(name);
        validateBio(bio);
        validateBirthDate(birthDate);

        return new Seller(UUID.randomUUID(), name, bio, birthDate, Instant.now());
    }

    private void validateName(String name) {
        if (name == null) {
            throw new MissingParameterException("Missing name");
        } else if (name.isEmpty()) {
            throw new InvalidParameterException("Name is empty");
        }
    }

    private void validateBio(String bio) {
        if (bio == null) {
            throw new MissingParameterException("Missing bio");
        } else if (bio.isEmpty()) {
            throw new InvalidParameterException("Bio is empty");
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new MissingParameterException("Missing birthdate");
        } else if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidParameterException("Seller must be at least 18 years old");
        }
    }
}
