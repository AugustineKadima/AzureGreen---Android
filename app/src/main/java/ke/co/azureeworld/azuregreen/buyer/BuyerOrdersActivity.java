package ke.co.azureeworld.azuregreen.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketActivity;
import ke.co.azureeworld.azuregreen.fragments.AddCropToStallFarmerFragment;
import ke.co.azureeworld.azuregreen.fragments.BuyerAllOrdersFragment;
import ke.co.azureeworld.azuregreen.fragments.BuyerNewOrderFragment;
import ke.co.azureeworld.azuregreen.fragments.BuyerOpenOrdersFragment;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;
import ke.co.azureeworld.azuregreen.view_models.BuyerOrderViewModel;

public class BuyerOrdersActivity extends AppCompatActivity {

    RelativeLayout records, home;
    Button  all_orders, btn_submissions, btn_saved, btn_market;
    ImageView new_order;
    BuyerOrderViewModel orderViewModel;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference("orders");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerSettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerOrdersActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_orders);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        all_orders = (Button) findViewById(R.id.all_crops);
        new_order = (ImageView) findViewById(R.id.add_new_crop);
        btn_submissions = (Button) findViewById(R.id.btn_submissions_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);

        allOrders();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerRecordsActivity.class));
            }
        });



        new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newOrder();
            }
        });

        all_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allOrders();
            }
        });

        btn_submissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerSavedActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerMarketActivity.class));
            }
        });

        orderViewModel = new ViewModelProvider(this).get(BuyerOrderViewModel.class);
        orderViewModel.getOrder().observe(this, order ->{
            mRef.push().setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(BuyerOrdersActivity.this, "Order created successfully!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BuyerOrdersActivity.this, "Failed! Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


    }

    private void allOrders() {
        all_orders.setTextColor(getResources().getColor(R.color.azure_orange));
        new_order.setColorFilter(getResources().getColor(R.color.azure_light_green));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BuyerAllOrdersFragment buyerAllOrdersFragment = new BuyerAllOrdersFragment();
        fragmentTransaction.replace(R.id.buyer_orders_container, buyerAllOrdersFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void newOrder() {
        all_orders.setTextColor(getResources().getColor(R.color.azure_light_green));
        new_order.setColorFilter(getResources().getColor(R.color.azure_orange));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BuyerNewOrderFragment buyerNewOrderFragment = new BuyerNewOrderFragment();
        fragmentTransaction.replace(R.id.buyer_orders_container, buyerNewOrderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}