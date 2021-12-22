package ke.co.azureeworld.azuregreen.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class CropViewModel extends ViewModel {
    private MutableLiveData<HashMap<String, String>> crop = new MutableLiveData<>();
    private HashMap<String,String> data = new HashMap<>();

    public void setData(String cropName, String cropDescription, String cropLocation){
        data.put("cropName", cropName);
        data.put("cropDescription", cropDescription);
        data.put("cropLocation", cropLocation);
        crop.setValue(data);
    }

    public MutableLiveData<HashMap<String, String>> getCrop() {
        return crop;
    }
}
