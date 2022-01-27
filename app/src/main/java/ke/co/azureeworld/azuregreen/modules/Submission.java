package ke.co.azureeworld.azuregreen.modules;

import org.parceler.Parcel;

@Parcel
public class Submission {

    private String cropName;
    private String cropDescription;
    private String Kgs;
    private String orderDate;
    private String orderTime;
    private String price;
    private String farmerEmail;
    private String buyerEmail;
    private String farmerPhone;

    public Submission(){}

    public Submission(String cropName, String cropDescription, String kgs, String orderDate, String orderTime, String price, String farmerEmail, String buyerEmail, String farmerPhone) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        Kgs = kgs;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.price = price;
        this.farmerEmail = farmerEmail;
        this.buyerEmail = buyerEmail;
        this.farmerPhone = farmerPhone;
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

    public String getFarmerEmail() {
        return farmerEmail;
    }

    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getFarmerPhone() {
        return farmerPhone;
    }

    public void setFarmerPhone(String farmerPhone) {
        this.farmerPhone = farmerPhone;
    }
}
