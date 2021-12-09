package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.fragments.AddCropToStallFarmerFragment;
import ke.co.azureeworld.azuregreen.fragments.AllCropsInMyStallFarmerFragment;
import ke.co.azureeworld.azuregreen.fragments.OnSellMyStallFarmerFragment;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class MyStallActivity extends AppCompatActivity {

    RelativeLayout home, records;
    ImageView add_new_crop;
    Button btn_all_crops, btn_on_sell, btn_orders, btn_sell, btn_saved, btn_market;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MyStallActivity.this, SettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MyStallActivity.this, ProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MyStallActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stall);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        add_new_crop = (ImageView) findViewById(R.id.add_new_crop);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        btn_on_sell =(Button) findViewById(R.id.on_sell_crops);
        btn_all_crops = (Button) findViewById(R.id.all_crops);
        btn_orders = (Button) findViewById(R.id.btn_orders_nav);
        btn_sell = (Button) findViewById(R.id.btn_sell_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyStallActivity.this, FarmerMarketActivity.class));
            }
        });
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyStallActivity.this, FarmerSavedActivity.class));
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyStallActivity.this, FarmerSellActivity.class));
            }
        });

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyStallActivity.this, FarmerHomeActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyStallActivity.this, FarmerHomeActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyStallActivity.this, FarmerRecordsActivity.class));
            }
        });

        add_new_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCrop();
            }
        });

        btn_all_crops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCrops();
            }
        });

        btn_on_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSell();
            }
        });
    }

    private void onSell() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        OnSellMyStallFarmerFragment onSellMyStallFarmerFragment = new OnSellMyStallFarmerFragment();
        fragmentTransaction.replace(R.id.my_stall_container, onSellMyStallFarmerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void allCrops() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AllCropsInMyStallFarmerFragment allCropsInMyStallFarmerFragment = new AllCropsInMyStallFarmerFragment();
        fragmentTransaction.replace(R.id.my_stall_container, allCropsInMyStallFarmerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addNewCrop() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddCropToStallFarmerFragment addCropToStallFarmerFragment = new AddCropToStallFarmerFragment();
        fragmentTransaction.replace(R.id.my_stall_container, addCropToStallFarmerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}