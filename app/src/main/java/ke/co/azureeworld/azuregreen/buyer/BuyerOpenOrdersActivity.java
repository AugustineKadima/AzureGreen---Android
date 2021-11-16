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

public class BuyerOpenOrdersActivity extends AppCompatActivity {

    TextView all_orders, new_order;
    ImageView back;
    RelativeLayout records, home;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_open_orders);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new_order = (TextView) findViewById(R.id.add_crop);
        all_orders = (TextView) findViewById(R.id.all_crops);
        back = (ImageView) findViewById(R.id.order_apply_back);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);

        new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOpenOrdersActivity.this, BuyerNewOrderActivity.class));
            }
        });

        all_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOpenOrdersActivity.this, BuyerOrdersActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOpenOrdersActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOpenOrdersActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerOpenOrdersActivity.this, BuyerRecordsActivity.class));
            }
        });

    }
}