package ke.co.azureeworld.azuregreen.modules;

public class Crop {
    private String cropName;
    private String cropDescription;
    private String cropLocation;

    public Crop() {
    }

    public Crop(String cropName, String cropDescription, String cropLocation) {
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.cropLocation = cropLocation;
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

    public String getCropLocation() {
        return cropLocation;
    }

    public void setCropLocation(String cropLocation) {
        this.cropLocation = cropLocation;
    }
}
