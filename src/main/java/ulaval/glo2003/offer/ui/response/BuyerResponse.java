package ulaval.glo2003.offer.ui.response;

public class BuyerResponse {
    private String name;
    private String email;
    private String phoneNumber;

    public BuyerResponse() {
    }

    public BuyerResponse(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}