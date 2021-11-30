package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.fragments.AddCropToStallFarmerFragment;
import ke.co.azureeworld.azuregreen.fragments.AllCropsInMyStallFarmerFragment;
import ke.co.azureeworld.azuregreen.fragments.OnSellMyStallFarmerFragment;

public class MyStallActivity extends AppCompatActivity {

    RelativeLayout home, records;
    ImageView add_new_crop;
    Button btn_all_crops, btn_on_sell;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
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