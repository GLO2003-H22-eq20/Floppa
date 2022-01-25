package ulaval.glo2003;

import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class SellerRequest {
    @NotNull
    public String name;

    @NotNull
    public String bio;

    @NotNull
    public Date birthDate;
}
