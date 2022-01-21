package ke.co.azureeworld.azuregreen.buyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketDetailActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.modules.BuyerSaved;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class BuyerSavedDetailActivity extends AppCompatActivity {
    TextView crop_name, crop_description, _kgs, _price, order_date;
    ImageView back;
    RelativeLayout home, orders, records;
    Button btn_submissions, btn_market,  btn_saved;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerSettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_saved_detail);

        crop_name = (TextView) findViewById(R.id.order_details_title);
        crop_description = (TextView) findViewById(R.id.order_details_description);
        _kgs = (TextView) findViewById(R.id.order_details_sub_title);
        _price = (TextView) findViewById(R.id.order_details_price);
        order_date = (TextView) findViewById(R.id.order_details_date);
        back = (ImageView) findViewById(R.id.order_details_back);
        btn_submissions = (Button) findViewById(R.id.btn_submissions_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        orders = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);

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
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerSavedActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerOrdersActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerRecordsActivity.class));
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerSavedActivity.class));
            }
        });

        btn_submissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedDetailActivity.this, BuyerMarketActivity.class));
            }
        });
    }
}