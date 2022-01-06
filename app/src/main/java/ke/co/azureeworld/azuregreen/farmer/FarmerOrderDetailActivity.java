package ke.co.azureeworld.azuregreen.farmer;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class FarmerOrderDetailActivity extends AppCompatActivity {

    ImageView order_details_back;
    RelativeLayout home, my_stall, records, detail_layout;
    Button  btn_orders, btn_sell, btn_saved, btn_market, btn_save;
    TextView crop_name, crop_description, order_date, order_time, _price, _kgs, _status;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference("farmer_saved_crops");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);

        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, SettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, ProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_order_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        order_details_back = (ImageView) findViewById(R.id.order_details_back);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        my_stall = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        btn_orders = (Button) findViewById(R.id.btn_orders_nav);
        btn_sell = (Button) findViewById(R.id.btn_sell_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);
        crop_name = (TextView) findViewById(R.id.order_details_title);
        crop_description = (TextView) findViewById(R.id.order_details_description);
        order_date = (TextView) findViewById(R.id.order_details_date);
//        order_time = (TextView) findViewById(R.id.);
        _price = (TextView) findViewById(R.id.order_details_price);
        _kgs = (TextView) findViewById(R.id.order_details_sub_title);
        _status = (TextView) findViewById(R.id.status_value);
        btn_save = (Button) findViewById(R.id.btn_save_order_details);
        detail_layout = (RelativeLayout) findViewById(R.id.order_detail_layout);

        order_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerHomeActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerHomeActivity.class));
            }
        });

        my_stall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, MyStallActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerRecordsActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerMarketActivity.class));
            }
        });
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerSavedActivity.class));
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerSellActivity.class));
            }
        });

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderDetailActivity.this, FarmerHomeActivity.class));
            }
        });

        Intent intent = getIntent();
        String cropName = intent.getStringExtra("cropName");
        String cropDescription = intent.getStringExtra("cropDescription");
        String orderDate = intent.getStringExtra("orderDate");
        String orderTime = intent.getStringExtra("orderTime");
        String price = intent.getStringExtra("price");
        String kgs = intent.getStringExtra("kgs");
        String status = intent.getStringExtra("status");

        crop_name.setText(cropName);
        crop_description.setText(cropDescription);
        order_date.setText(orderDate);
        _price.setText("KSh. "+price+"/Kg");
        _kgs.setText(kgs+" Kgs");
        _status.setText(status);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> cropMap = new HashMap<>();
                cropMap.put("cropName", cropName);
                cropMap.put("cropDescription", cropDescription);
                cropMap.put("orderDate", orderDate);
                cropMap.put("price", price);
                cropMap.put("kgs",  kgs);
                cropMap.put("status", status);

                mRef.push().setValue(cropMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar.make(detail_layout,"Saved successfully!", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).setActionTextColor(getResources().getColor(R.color.azure_orange)).show();
//                            Toast.makeText(FarmerOrderDetailActivity.this, "Saved successfully!", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(FarmerOrderDetailActivity.this, "Failed! Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}