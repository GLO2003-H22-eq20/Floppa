package ulaval.glo2003.seller.application;

import ulaval.glo2003.exceptions.InvalidParameterException;
import ulaval.glo2003.exceptions.MissingParameterException;
import ulaval.glo2003.seller.Seller;

import java.time.LocalDate;

public class SellerFactory {
    public Seller createSeller(String name, String bio, LocalDate birthDate) {
        validateName(name);
        validateBio(bio);
        validateBirthDate(birthDate);

        return new Seller(name, bio, birthDate);
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
