package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ke.co.azureeworld.azuregreen.R;

public class FarmerMarketDetailActivity extends AppCompatActivity {

    TextView crop_name, crop_description, _status, sell_date, _kgs, _price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_market_detail);

        crop_name = (TextView) findViewById(R.id.order_details_title);
        crop_description = (TextView) findViewById(R.id.order_details_description);
        _status = (TextView) findViewById(R.id.status_value);
        sell_date = (TextView) findViewById(R.id.order_details_date);
        _kgs = (TextView) findViewById(R.id.order_details_sub_title);
        _price = (TextView) findViewById(R.id.order_details_price);

        Intent intent = getIntent();
        String cropName = intent.getStringExtra("cropName");
        String cropDescription = intent.getStringExtra("cropDescription");
        String sellDate = intent.getStringExtra("sellDate");
        String status = intent.getStringExtra("status");
        String kgs = intent.getStringExtra("kgs");
        String price = intent.getStringExtra("price");

        crop_name.setText(cropName);
        crop_description.setText(cropDescription);
        _status.setText(status);
        sell_date.setText(sellDate);
        _kgs.setText(kgs + " Kgs required");
        _price.setText("Ksh. "+price + "/Kg");
    }
}