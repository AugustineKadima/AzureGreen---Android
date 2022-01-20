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
import ke.co.azureeworld.azuregreen.buyer.BuyActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class FarmerMarketDetailActivity extends AppCompatActivity {

    TextView crop_name, crop_description, _status, sell_date, _kgs, _price;
    Button btn_buy;
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
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerSettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

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
        btn_buy = (Button) findViewById(R.id.btn_apply_order_details);
        btn_back = (ImageView) findViewById(R.id.order_details_back);
        btn_orders = (Button) findViewById(R.id.btn_orders_nav);
        btn_sell = (Button) findViewById(R.id.btn_sell_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyActivity.class));
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, FarmerMarketActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, FarmerMarketActivity.class));
            }
        });
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, FarmerSavedActivity.class));
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, FarmerSellActivity.class));
            }
        });

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, FarmerHomeActivity.class));
            }
        });
    }
}