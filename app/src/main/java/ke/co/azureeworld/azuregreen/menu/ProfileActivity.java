package ke.co.azureeworld.azuregreen.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerOrderDetailActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerRecordsActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerSavedActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerSellActivity;
import ke.co.azureeworld.azuregreen.farmer.MyStallActivity;
import ke.co.azureeworld.azuregreen.modules.User;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class ProfileActivity extends AppCompatActivity {
    Button orders, sell, saved, market;
    RelativeLayout home, my_stall, records;
    ImageView farmerImage;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference().child("farmers");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);

        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(ProfileActivity.this, SettingsActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        orders = (Button) findViewById(R.id.btn_orders_nav);
        sell = (Button) findViewById(R.id.btn_sell_nav);
        saved = (Button) findViewById(R.id.btn_saved_nav);
        market = (Button) findViewById(R.id.btn_market_nav);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        my_stall = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        farmerImage = (ImageView) findViewById(R.id.buyer_image_orders);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FarmerHomeActivity.class));
            }
        });

        my_stall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MyStallActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FarmerRecordsActivity.class));
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FarmerHomeActivity.class));
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FarmerSellActivity.class));
            }
        });

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FarmerSavedActivity.class));
            }
        });

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FarmerMarketActivity.class));
            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    if(user != null){
                        String link = user.getImageUrl();
                        Picasso.get().load(link).into(farmerImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}