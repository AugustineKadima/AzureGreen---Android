package ke.co.azureeworld.azuregreen.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.adapters.BuyerSavedAdapter;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerProfileActivity;
import ke.co.azureeworld.azuregreen.menu.BuyerSettingsActivity;
import ke.co.azureeworld.azuregreen.menu.ProfileActivity;
import ke.co.azureeworld.azuregreen.menu.SettingsActivity;
import ke.co.azureeworld.azuregreen.modules.BuyerSaved;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;
import ke.co.azureeworld.azuregreen.view_models.EmailViewModel;

public class BuyerSavedActivity extends AppCompatActivity {

    Button btn_submissions, btn_market;
    RecyclerView recyclerView;
    BuyerSavedAdapter adapter;
    List<BuyerSaved> savedList;
    RelativeLayout orders, records,home;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference root = firebaseDatabase.getReference().child("buyer_saved_items");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerSettingsActivity.class));
                return false;
            }

        });

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerProfileActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerSavedActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_saved);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_market = (Button) findViewById(R.id.btn_market_nav);
        btn_submissions = (Button) findViewById(R.id.btn_submissions_nav);
        orders = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_submissions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        savedList = new ArrayList<>();
        adapter = new BuyerSavedAdapter(savedList, this);
        recyclerView.setAdapter(adapter);

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerOrdersActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerRecordsActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerSubmissionsActivity.class));
            }
        });


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BuyerSaved saved = dataSnapshot.getValue(BuyerSaved.class);
                    if(EmailViewModel.email.equals(saved.getEmail())){
                        savedList.add(saved);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_submissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerSavedActivity.this, BuyerMarketActivity.class));
            }
        });
    }
}