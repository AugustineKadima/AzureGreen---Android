package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import ke.co.azureeworld.azuregreen.R;

public class FarmerMarketActivity extends AppCompatActivity {

    Button orders, sell, saved;

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