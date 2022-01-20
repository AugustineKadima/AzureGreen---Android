package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class FarmerSavedDetailActivity extends AppCompatActivity {

    TextView crop_name, crop_description, _status, order_date, _kgs, _price;
    Button btn_apply;

    ImageView btn_back;
    Button  btn_orders, btn_sell, btn_saved, btn_market;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, BuyerSettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, BuyerProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_saved_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crop_name = (TextView) findViewById(R.id.order_details_title);
        crop_description = (TextView) findViewById(R.id.order_details_description);
        _status = (TextView) findViewById(R.id.status_value);
        order_date = (TextView) findViewById(R.id.order_details_date);
        _kgs = (TextView) findViewById(R.id.order_details_sub_title);
        _price = (TextView) findViewById(R.id.order_details_price);
        btn_apply = (Button) findViewById(R.id.btn_apply_order_details);
        btn_back = (ImageView) findViewById(R.id.order_details_back);
        btn_orders = (Button) findViewById(R.id.btn_orders_nav);
        btn_sell = (Button) findViewById(R.id.btn_sell_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);

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

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, FarmerMarketActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, FarmerMarketActivity.class));
            }
        });
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, FarmerSavedActivity.class));
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, FarmerSellActivity.class));
            }
        });

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedDetailActivity.this, FarmerHomeActivity.class));
            }
        });

    }
}