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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.modules.User;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;
import ke.co.azureeworld.azuregreen.view_models.EmailViewModel;

public class FarmerOrderApplyActivity extends AppCompatActivity {

    RelativeLayout home, my_stall, records;
    Button  btn_orders, btn_sell, btn_saved, btn_market, btn_apply;
    ImageView order_details_back;
    EditText product_name, price;
    
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference("submissions");
    DatabaseReference farmerRef = firebaseDatabase.getReference().child("farmers");
    User user;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);

        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, SettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, ProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_order_apply);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        my_stall = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        btn_orders = (Button) findViewById(R.id.btn_orders_nav);
        btn_sell = (Button) findViewById(R.id.btn_sell_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);
        order_details_back = (ImageView) findViewById(R.id.order_details_back);
        product_name = (EditText) findViewById(R.id.apply_product_name_farmer);
        price = (EditText) findViewById(R.id.apply_price_per_kg);
        btn_apply = (Button) findViewById(R.id.btn_apply_farmer);

        Intent orderIntent = getIntent();
        String _productName = orderIntent.getStringExtra("cropName");
        String _price = orderIntent.getStringExtra("price");

        price.setText(_price);
        product_name.setText(_productName);

        user = new User();

        farmerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User newFarmer = dataSnapshot.getValue(User.class);
                    if(newFarmer.getEmail().equals(EmailViewModel.email)){
                        user = newFarmer;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= getIntent();
                HashMap<String, String> application = new HashMap<>();

                application.put("cropName",  intent.getStringExtra("cropName"));
                application.put("cropDescription", intent.getStringExtra("cropDescription"));
                application.put("orderDate",intent.getStringExtra("orderDate"));
                application.put("orderTime",intent.getStringExtra("orderTime"));
                application.put("price",intent.getStringExtra("price"));
                application.put("kgs", intent.getStringExtra("Kgs"));
                application.put("farmerEmail", user.getEmail());
                application.put("farmerPhone", user.getPhoneNumber());
                application.put("buyerEmail", intent.getStringExtra("buyerEmail"));

                mRef.push().setValue(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FarmerOrderApplyActivity.this, "Your application was successful.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(FarmerOrderApplyActivity.this, "Failed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        order_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerHomeActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerHomeActivity.class));
            }
        });

        my_stall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, MyStallActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerRecordsActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerMarketActivity.class));
            }
        });
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerSavedActivity.class));
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerSellActivity.class));
            }
        });

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderApplyActivity.this, FarmerHomeActivity.class));
            }
        });
    }
}