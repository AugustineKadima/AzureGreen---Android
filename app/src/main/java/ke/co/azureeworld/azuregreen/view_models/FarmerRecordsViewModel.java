package ke.co.azureeworld.azuregreen.view_models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FarmerRecordsViewModel extends ViewModel {
    private MutableLiveData<HashMap<String, String>> record = new MutableLiveData<>();
    private HashMap<String, String> data = new HashMap<>();

    public void setData(String activityName, String activityDescription, String cost, String date){
        data.put("activityName", activityName);
        data.put("activityDescription", activityDescription);
        data.put("cost", cost);
        data.put("date", date);
        record.setValue(data);

    }

    public MutableLiveData<HashMap<String, String>> getRecord() {
        return record;
    }
}
