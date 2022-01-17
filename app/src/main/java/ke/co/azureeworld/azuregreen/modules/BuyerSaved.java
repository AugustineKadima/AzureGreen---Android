package ke.co.azureeworld.azuregreen.modules;

public class BuyerSaved {
    private String cropName;
    private String cropDescription;
    private String orderDate;
    private String price;
    private String orderTime;
    private String Kgs;
    private String email;

    public BuyerSaved(){}

    public BuyerSaved(String cropName, String cropDescription, String orderDate, String price, String orderTime, String kgs, String email) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.orderDate = orderDate;
        this.price = price;
        this.orderTime = orderTime;
        Kgs = kgs;
        this.email = email;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropDescription() {
        return cropDescription;
    }

    public void setCropDescription(String cropDescription) {
        this.cropDescription = cropDescription;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getKgs() {
        return Kgs;
    }

    public void setKgs(String kgs) {
        Kgs = kgs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
