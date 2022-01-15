package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ke.co.azureeworld.azuregreen.R;

public class FarmerSavedDetailActivity extends AppCompatActivity {

    TextView crop_name, crop_description, _status, order_date, _kgs, _price;
    Button btn_apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_saved_detail);

        crop_name = (TextView) findViewById(R.id.order_details_title);
        crop_description = (TextView) findViewById(R.id.order_details_description);
        _status = (TextView) findViewById(R.id.status_value);
        order_date = (TextView) findViewById(R.id.order_details_date);
        _kgs = (TextView) findViewById(R.id.order_details_sub_title);
        _price = (TextView) findViewById(R.id.order_details_price);
        btn_apply = (Button) findViewById(R.id.btn_apply_order_details);

        Intent intent = getIntent();

        String cropName = intent.getStringExtra("cropName");
        String cropDescription = intent.getStringExtra("cropDescription");
        String status = intent.getStringExtra("status");
        String orderDate = intent.getStringExtra("orderDate");
        String kgs = intent.getStringExtra("kgs");
        String price = intent.getStringExtra("price");

        crop_name.setText(cropName);
        crop_description.setText(cropDescription);
        _status.setText(status);
        order_date.setText(orderDate);
        _kgs.setText(kgs + "Kgs required");
        _price.setText("KSh. " + price + "/Kg");

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(FarmerSavedDetailActivity.this, FarmerOrderApplyActivity.class);
                intent1.putExtra("price", price);
                intent1.putExtra("cropName", cropName);
                startActivity(intent1);
            }
        });

    }
}