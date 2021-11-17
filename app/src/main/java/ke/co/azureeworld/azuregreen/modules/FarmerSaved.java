package ke.co.azureeworld.azuregreen.modules;

public class FarmerSaved {

    private String cropName;
    private String cropDescription;
    private String status;
    private String orderDate;

    public FarmerSaved(){}

    public FarmerSaved(String cropName, String cropDescription, String status, String orderDate) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.status = status;
        this.orderDate = orderDate;
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
}
