package ke.co.azureeworld.azuregreen.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class BuyerOrderViewModel extends ViewModel {
    MutableLiveData<HashMap<String, String>>  order = new MutableLiveData<>();
    HashMap<String, String> data = new HashMap<>();
    public void setData(String cropName, String cropDescription, String kgs, String orderDate, String orderTime, String price, String status, String location, String buyerEmail, String buyerPhone ){
        data.put("cropName", cropName);
        data.put("cropDescription", cropDescription);
        data.put("kgs", kgs);
        data.put("orderDate", orderDate);
        data.put("orderTime", orderTime);
        data.put("price",price);
        data.put("status", status);
        data.put("location", location);
        data.put("email", buyerEmail);
        data.put("buyerPhone", buyerPhone);
        order.setValue(data);
    }

    public MutableLiveData<HashMap<String, String>> getOrder() {
        return order;
    }
}
