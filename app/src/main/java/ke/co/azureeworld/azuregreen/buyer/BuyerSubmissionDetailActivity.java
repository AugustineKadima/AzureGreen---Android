package ke.co.azureeworld.azuregreen.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ke.co.azureeworld.azuregreen.R;

public class BuyerSubmissionDetailActivity extends AppCompatActivity {

    TextView crop_name, crop_description, _kgs, _price, order_date;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_submission_detail);

        crop_name = (TextView) findViewById(R.id.order_details_title);
        crop_description = (TextView) findViewById(R.id.order_details_description);
        _kgs = (TextView) findViewById(R.id.order_details_sub_title);
        _price = (TextView) findViewById(R.id.order_details_price);
        order_date = (TextView) findViewById(R.id.order_details_date);
        back = (ImageView) findViewById(R.id.order_details_back);

        Intent intent = getIntent();

        String cropName = intent.getStringExtra("cropName");
        String cropDescription = intent.getStringExtra("cropDescription");
        String kgs = intent.getStringExtra("kgs");
        String price = intent.getStringExtra("price");
        String orderDate = intent.getStringExtra("orderDate");

        crop_name.setText(cropName);
        crop_description.setText(cropDescription);
        _kgs.setText(kgs +" Kgs available");
        _price.setText("Ksh. "+price+"/Kg");
        order_date.setText(orderDate);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSubmissionDetailActivity.this, BuyerSubmissionsActivity.class));
            }
        });

    }
}