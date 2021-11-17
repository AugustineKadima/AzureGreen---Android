package ke.co.azureeworld.azuregreen.modules;

public class Sell {

    private String cropName;
    private String cropDescription;
    private String price;
    private String Kgs;
    private String sellDate;
    private String sellTime;

    public Sell(){}

    public Sell(String cropName, String cropDescription, String price, String kgs, String sellDate, String sellTime) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.price = price;
        this.Kgs = kgs;
        this.sellDate = sellDate;
        this.sellTime = sellTime;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKgs() {
        return Kgs;
    }

    public void setKgs(String kgs) {
        Kgs = kgs;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }
}
