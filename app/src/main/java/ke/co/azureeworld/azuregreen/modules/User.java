package ke.co.azureeworld.azuregreen.modules;

public class User {
    private String userName;
    private String email;
    private String userLocation;
    private String password;
    private String phoneNumber;
    private String imageUrl;
    private String accountCreationDate;

    public  User(){}

    public User(String userName, String email, String userLocation, String password, String phoneNumber, String imageUrl, String accountCreationDate) {
        this.userName = userName;
        this.email = email;
        this.userLocation = userLocation;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
