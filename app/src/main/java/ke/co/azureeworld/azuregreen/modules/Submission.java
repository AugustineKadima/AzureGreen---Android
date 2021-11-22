package ke.co.azureeworld.azuregreen.modules;

public class Submission {

    private String cropName;
    private String cropDescription;
    private String Kgs;
    private String orderDate;
    private String orderTime;
    private String price;

    public Submission(){}

    public Submission(String cropName, String cropDescription, String kgs, String orderDate, String orderTime, String price) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        Kgs = kgs;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.price = price;
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

    public String getKgs() {
        return Kgs;
    }

    public void setKgs(String kgs) {
        Kgs = kgs;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
