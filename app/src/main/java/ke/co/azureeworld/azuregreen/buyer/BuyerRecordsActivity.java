package ke.co.azureeworld.azuregreen.buyer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerAllRecordsFragment;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerRecordsActivity;
import ke.co.azureeworld.azuregreen.fragments.BuyerAllRecordsFragment;
import ke.co.azureeworld.azuregreen.fragments.BuyerNewRecordsFragment;
import ke.co.azureeworld.azuregreen.fragments.FarmerNewRecordFragment;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;
import ke.co.azureeworld.azuregreen.view_models.FarmerRecordsViewModel;

public class BuyerRecordsActivity extends AppCompatActivity {

    RelativeLayout home, orders;
    Button btn_submissions, btn_saved, btn_market, all_records;
    ImageView new_record;
    FarmerRecordsViewModel farmerRecordsViewModel;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference("buyer_records");


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerSettingsActivity.class));
                return false;
            }
        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerRecordsActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_records);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        orders = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        btn_submissions = (Button) findViewById(R.id.btn_submissions_nav);
        btn_saved = (Button) findViewById(R.id.btn_saved_nav);
        btn_market = (Button) findViewById(R.id.btn_market_nav);
        new_record = (ImageView) findViewById(R.id.add_new_crop);
        all_records = (Button) findViewById(R.id.all_crops);

        allRecords();

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerOrdersActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        btn_submissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerSavedActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerRecordsActivity.this, BuyerMarketActivity.class));
            }
        });

        new_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRecord();
            }
        });

        all_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allRecords();
            }
        });

        farmerRecordsViewModel = new ViewModelProvider(this).get(FarmerRecordsViewModel.class);
        farmerRecordsViewModel.getRecord().observe(this, record ->{
            mRef.push().setValue(record).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(BuyerRecordsActivity.this, "Record created!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BuyerRecordsActivity.this, "Failed! Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


    }

    private void addNewRecord(){
        all_records.setTextColor(getResources().getColor(R.color.azure_light_green));
        new_record.setColorFilter(getResources().getColor(R.color.azure_orange));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BuyerNewRecordsFragment buyerNewRecordsFragment = new BuyerNewRecordsFragment();
        fragmentTransaction.replace(R.id.records_container, buyerNewRecordsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void allRecords(){
        all_records.setTextColor(getResources().getColor(R.color.azure_orange));
        new_record.setColorFilter(getResources().getColor(R.color.azure_light_green));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BuyerAllRecordsFragment buyerAllRecordsFragment = new BuyerAllRecordsFragment();
        fragmentTransaction.replace(R.id.records_container, buyerAllRecordsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}