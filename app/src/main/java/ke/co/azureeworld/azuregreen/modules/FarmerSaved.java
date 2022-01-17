package ke.co.azureeworld.azuregreen.modules;

public class FarmerSaved {

    private String cropName;
    private String cropDescription;
    private String status;
    private String orderDate;
    private String kgs;
    private String price;
    private String email;

    public FarmerSaved(){}

    public FarmerSaved(String cropName, String cropDescription, String status, String orderDate, String kgs, String price, String email) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.status = status;
        this.orderDate = orderDate;
        this.kgs = kgs;
        this.price = price;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getKgs() {
        return kgs;
    }

    public void setKgs(String kgs) {
        this.kgs = kgs;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
