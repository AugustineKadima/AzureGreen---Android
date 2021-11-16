package ke.co.azureeworld.azuregreen.modules;

public class Order {
    private String cropName;
    private String cropDescription;
    private String Kgs;
    private String orderDate;
    private String orderTime;
    private String price;
    private String status;

    public Order(){}

    public Order(String cropName, String cropDescription, String kgs, String orderDate, String orderTime, String price, String status) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.Kgs = kgs;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.price = price;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
