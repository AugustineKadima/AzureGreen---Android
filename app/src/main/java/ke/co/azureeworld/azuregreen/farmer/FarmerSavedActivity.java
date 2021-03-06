package ke.co.azureeworld.azuregreen.farmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.adapters.FarmerSavedAdapter;
import ke.co.azureeworld.azuregreen.modules.FarmerSaved;

public class FarmerSavedActivity extends AppCompatActivity {

    Button sell, orders, market;

    RecyclerView recyclerView;
    FarmerSavedAdapter adapter;
    List<FarmerSaved> saved_crops;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference root = firebaseDatabase.getReference().child("farmer_saved_crops");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_saved);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sell = (Button) findViewById(R.id.btn_sell_nav);
        orders = (Button) findViewById(R.id.btn_orders_nav);
        market = (Button) findViewById(R.id.btn_market_nav);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_saved3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        saved_crops = new ArrayList<>();
        adapter = new FarmerSavedAdapter(this, saved_crops);
        recyclerView.setAdapter(adapter);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedActivity.this, FarmerSellActivity.class));
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedActivity.this, FarmerHomeActivity.class));
            }
        });

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSavedActivity.this, FarmerMarketActivity.class));
            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    FarmerSaved saved = dataSnapshot.getValue(FarmerSaved.class);
                    saved_crops.add(saved);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}