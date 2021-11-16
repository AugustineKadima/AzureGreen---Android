package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.adapters.FarmerMarketAdapter;
import ke.co.azureeworld.azuregreen.modules.Sell;

public class FarmerMarketActivity extends AppCompatActivity {

    Button orders, sell, saved;
    RecyclerView recyclerView;
    FarmerMarketAdapter adapter;
    List<Sell> on_sell_items;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_market);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        orders = (Button) findViewById(R.id.btn_orders_nav);
        sell = (Button) findViewById(R.id.btn_sell_nav);
        saved = (Button) findViewById(R.id.btn_saved_nav);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_orders2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        on_sell_items = new ArrayList<>();

        adapter = new FarmerMarketAdapter(on_sell_items, this);
        recyclerView.setAdapter(adapter);


        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketActivity.this, FarmerHomeActivity.class));
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketActivity.this, FarmerSellActivity.class));
            }
        });

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerMarketActivity.this, FarmerSavedActivity.class));
            }
        });
    }
}