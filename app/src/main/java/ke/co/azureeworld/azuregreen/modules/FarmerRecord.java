package ke.co.azureeworld.azuregreen.modules;

public class FarmerRecord {
    private String activityName;
    private String activityDescription;
    private String cost;
    private String date;

    public FarmerRecord(){}

    public FarmerRecord(String activityName, String activityDescription, String cost, String date) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.cost = cost;
        this.date = date;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
