package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import ke.co.azureeworld.azuregreen.R;

public class FarmerSavedActivity extends AppCompatActivity {

    Button sell, orders, market;

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

    }
}