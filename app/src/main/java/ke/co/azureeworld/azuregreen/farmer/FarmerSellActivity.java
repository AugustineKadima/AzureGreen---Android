package ke.co.azureeworld.azuregreen.farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import ke.co.azureeworld.azuregreen.R;

public class FarmerSellActivity extends AppCompatActivity {

    Button sell;
    ImageView back;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_sell);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (ImageView) findViewById(R.id.order_apply_back);
        sell = (Button) findViewById(R.id.btn_apply_farmer);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerHomeActivity.class));
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FarmerSellActivity.this, "Added to market successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }
}