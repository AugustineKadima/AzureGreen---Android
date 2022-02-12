package ke.co.azureeworld.azuregreen.buyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ke.co.azureeworld.azuregreen.R;

public class BuyActivity extends AppCompatActivity {

    TextView farmer_email, farmer_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        farmer_email = (TextView) findViewById(R.id.farmer_email);
        farmer_phone = (TextView) findViewById(R.id.farmer_phone);
        Intent contactIntent = getIntent();
        String farmerEmail = contactIntent.getStringExtra("email");
        String farmerPhone = contactIntent.getStringExtra("farmerPhone");
        farmer_email.setText(farmerEmail);
        farmer_phone.setText(farmerPhone);

        farmer_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] recepients = {farmerEmail};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, recepients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "TEST SUBJECT");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi there");
                startActivity(intent);
            }
        });

        farmer_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "tel:"+farmerPhone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phone));
                startActivity(intent);
            }
        });

    }
}