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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.buyer.BuyerMarketActivity;
import ke.co.azureeworld.azuregreen.buyer.BuyerOrdersActivity;
import ke.co.azureeworld.azuregreen.buyer.BuyerRecordsActivity;
import ke.co.azureeworld.azuregreen.buyer.BuyerSavedActivity;
import ke.co.azureeworld.azuregreen.buyer.BuyerSubmissionsActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerRecordsActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerSavedActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerSellActivity;
import ke.co.azureeworld.azuregreen.farmer.MyStallActivity;
import ke.co.azureeworld.azuregreen.modules.User;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class BuyerProfileActivity extends AppCompatActivity {

    Button submissions, saved, market;
    RelativeLayout home, orders, records;
    User user1;
    ImageView profileImage;
    TextView name, email, phone;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("buyers");


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.azure_menu, menu);

        MenuItem logout = menu.findItem(R.id.logout);
        MenuItem settings = menu.findItem(R.id.settings);

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerProfileActivity.this, SettingsActivity.class));
                return false;
            }
        });

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(BuyerProfileActivity.this, LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        submissions = (Button) findViewById(R.id.btn_submissions_nav);
        saved = (Button) findViewById(R.id.btn_saved_nav);
        market = (Button) findViewById(R.id.btn_market_nav);
        home = (RelativeLayout) findViewById(R.id.home_wrapper_farmer);
        orders = (RelativeLayout) findViewById(R.id.my_stall_wrapper_farmer);
        records = (RelativeLayout) findViewById(R.id.my_records_wrapper_farmer);
        profileImage = (ImageView) findViewById(R.id.buyer_image_orders);
        name = (TextView) findViewById(R.id.farmer_name);
        email = (TextView) findViewById(R.id.farmer_email);
        phone = (TextView) findViewById(R.id.farmer_phone);



        user1 = new User();

        name.setText(user1.getUserName());
        email.setText(user1.getEmail());
        phone.setText(user1.getPhoneNumber());

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerProfileActivity.this, BuyerSubmissionsActivity.class));
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerProfileActivity.this, BuyerOrdersActivity.class));
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerProfileActivity.this, BuyerRecordsActivity.class));
            }
        });
        submissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerProfileActivity.this, BuyerSubmissionsActivity.class));
            }
        });



        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerProfileActivity.this, BuyerSavedActivity.class));
            }
        });

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerProfileActivity.this, BuyerMarketActivity.class));
            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    user1 = user;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}