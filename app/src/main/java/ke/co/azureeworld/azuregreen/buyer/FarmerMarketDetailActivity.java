package ke.co.azureeworld.azuregreen.buyer;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FarmerMarketDetailActivity extends AppCompatActivity {

    RelativeLayout records, home;
    TextView open_orders, all_orders;
    ImageView back;
    EditText crop_name, crop_description, kgs, price;
    Button btn_order;
    LocalDate local_date;
    LocalTime local_time;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("orders");



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
        setContentView(R.layout.activity_buyer_new_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        open_orders = (TextView) findViewById(R.id.on_sell_crops);
        all_orders = (TextView) findViewById(R.id.all_crops);
        back = (ImageView) findViewById(R.id.order_apply_back);
        crop_name = (EditText) findViewById(R.id.crop_name_new_order);
        crop_description = (EditText) findViewById(R.id.crop_description_new_order);
        kgs = (EditText) findViewById(R.id.crop_kgs_new_order);
        price = (EditText) findViewById(R.id.crop_price_new_order);
        btn_order = (Button) findViewById(R.id.btn_order);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerRecordsActivity.class));
            }
        });

        open_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerOpenOrdersActivity.class));
            }
        });

        all_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerOrdersActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketDetailActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cropName = crop_name.getText().toString().trim();
                String cropDescription = crop_description.getText().toString().trim();
                String Kgs = kgs.getText().toString().trim();
                String Price = price.getText().toString().trim();
                local_date = LocalDate.now();
                local_time = LocalTime.now();

                if(cropName.isEmpty() && cropDescription.isEmpty() && Kgs.isEmpty() && Price.isEmpty()){
                    Toast.makeText(FarmerMarketDetailActivity.this, "Failed! Fill all input fields to create an order", Toast.LENGTH_LONG).show();
                }else if(cropName.isEmpty()){
                    crop_name.setError("Crop name required!");
                    crop_name.requestFocus();
                }else if(cropDescription.isEmpty()){
                    crop_description.setError("Crop description required!");
                    crop_description.requestFocus();
                }else if(Kgs.isEmpty()){
                    kgs.setError("Quantity required!");
                    kgs.requestFocus();
                }else if(Price.isEmpty()){
                    price.setError("Price required");
                    price.requestFocus();
                }else{
                    HashMap<String, String> order = new HashMap<>();
                    order.put("cropName", cropName);
                    order.put("cropDescription", cropDescription);
                    order.put("Kgs", Kgs);
                    order.put("price", Price);
                    order.put("status", "Open");
                    order.put("orderDate", local_date.toString());
                    order.put("orderTime", local_time.toString());

                    myRef.push().setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FarmerMarketDetailActivity.this, "Order was successfully placed!", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(FarmerMarketDetailActivity.this, "Failed! Try again.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
    }
}