package ke.co.azureeworld.azuregreen.buyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import ke.co.azureeworld.azuregreen.R;

public class BuyerSavedActivity extends AppCompatActivity {

    Button btn_submissions, btn_market;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);
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