package ke.co.azureeworld.azuregreen.buyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ke.co.azureeworld.azuregreen.R;

public class BuyerOrdersActivity extends AppCompatActivity {

    RelativeLayout records, home;
    TextView open_orders, new_order;
    ImageView back;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
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
        open_orders = (TextView) findViewById(R.id.on_sell_crops);
        new_order = (TextView) findViewById(R.id.add_crop);
        back = (ImageView) findViewById(R.id.order_apply_back);

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

        open_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerOpenOrdersActivity.class));
            }
        });

        new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerNewOrderActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOrdersActivity.this, BuyerSubmissionsActivity.class));
            }
        });

    }
}