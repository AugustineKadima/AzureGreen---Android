package ke.co.azureeworld.azuregreen.farmer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;
import ke.co.azureeworld.azuregreen.view_models.EmailViewModel;

public class FarmerSellActivity extends AppCompatActivity {

    Button sell, order, saved, market;
    EditText product_name,product_description, kgs, price;
    RelativeLayout home, records , my_stall;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("on_sell_items");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerSellActivity.this, SettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerSellActivity.this, ProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerSellActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_sell);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sell = (Button) findViewById(R.id.btn_apply_farmer);
        product_name = (EditText) findViewById(R.id.apply_product_name_farmer);
        product_description = (EditText) findViewById(R.id.apply_describe_farmer);
        kgs = (EditText) findViewById(R.id.apply_kgs_farmer);
        price = (EditText) findViewById(R.id.apply_price_per_kg);
        order = (Button) findViewById(R.id.btn_orders_nav);
        saved = (Button) findViewById(R.id.btn_saved_nav);
        market = (Button) findViewById(R.id.btn_market_nav);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        my_stall = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerHomeActivity.class));
            }
        });

        my_stall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, MyStallActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerRecordsActivity.class));
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerHomeActivity.class));
            }
        });

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerSavedActivity.class));
            }
        });

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerMarketActivity.class));
            }
        });


        sell.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String cropName = product_name.getText().toString().trim();
                String cropDescription = product_description.getText().toString().trim();
                String Kgs = kgs.getText().toString().trim();
                String Price = price.getText().toString().trim();
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();

                if(cropDescription.isEmpty() && cropName.isEmpty() && Kgs.isEmpty() && Price.isEmpty()){
                    Toast.makeText(FarmerSellActivity.this, "Failed! Fill all form inputs to proceed!", Toast.LENGTH_SHORT).show();
                }else if(cropName.isEmpty()){
                    product_name.setError("Crop name required!");
                    product_name.requestFocus();
                }else if(cropDescription.isEmpty()){
                    product_description.setError("Crop description required!");
                    product_description.requestFocus();
                }else if(Kgs.isEmpty()){
                    kgs.setError("Quantity required!");
                    kgs.requestFocus();
                }else if(Price.isEmpty()){
                    price.setError("Price required!");
                    price.requestFocus();
                }else{
                    HashMap<String, String> itemOnSell = new HashMap<>();
                    itemOnSell.put("cropName", cropName);
                    itemOnSell.put("cropDescription", cropDescription);
                    itemOnSell.put("Kgs", Kgs);
                    itemOnSell.put("price", Price);
                    itemOnSell.put("sellDate", date.toString());
                    itemOnSell.put("sellTime", time.toString());
                    itemOnSell.put("status", "Open");
                    itemOnSell.put("email", EmailViewModel.email);

                    myRef.push().setValue(itemOnSell).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FarmerSellActivity.this, "Added to market successfully!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(FarmerSellActivity.this, "Failed! Try again.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }
}