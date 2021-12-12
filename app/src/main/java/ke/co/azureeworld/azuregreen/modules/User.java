package ke.co.azureeworld.azuregreen.modules;

public class User {
    private String userName;
    private String userEmail;
    private String userLocation;
    private String userPassword;
    private String phoneNumber;
    private String imageUrl;
    private String accountCreationDate;

    public  User(){}

    public User(String userName, String userEmail, String userLocation, String userPassword, String phoneNumber, String imageUrl, String accountCreationDate) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLocation = userLocation;
        this.userPassword = userPassword;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.accountCreationDate = accountCreationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
}
