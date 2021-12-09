package ke.co.azureeworld.azuregreen.setup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;

public class CreateAccountActivity extends AppCompatActivity {

    EditText name, email, phone_number, password, confirm_password, location;
    TextView attach_photo, farmer_new_user_create_account;
    RadioButton radio_farmer, radio_buyer;
    Button btn_create_account;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

       name = (EditText) findViewById(R.id.create_account_name);
       email = (EditText) findViewById(R.id.create_account_email);
       phone_number = (EditText) findViewById(R.id.create_account_phone);
       password = (EditText) findViewById(R.id.create_account_password);
       confirm_password = (EditText) findViewById(R.id.create_account_confirm_password);
       location = (EditText) findViewById(R.id.create_account_location);
       attach_photo = (TextView) findViewById(R.id.apply_attach_photo);
       radio_buyer = (RadioButton) findViewById(R.id.create_account_buyer);
       radio_farmer = (RadioButton) findViewById(R.id.create_account_farmer);
       btn_create_account = (Button) findViewById(R.id.btn_create_account_get_started);
       farmer_new_user_create_account = (TextView) findViewById(R.id.farmer_new_user_create_account);

        farmer_new_user_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
            }
        });



       btn_create_account.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String userName = name.getText().toString().trim();
               String userEmail = email.getText().toString().trim();
               String phoneNumber = phone_number.getText().toString().trim();
               String userPassword = password.getText().toString().trim();
               String confirmPassword = confirm_password.getText().toString().trim();
               String userLocation = location.getText().toString().trim();

               HashMap<String, String> user = new HashMap<>();
               user.put("userName", userName);
               user.put("userEmail", userEmail);
               user.put("phoneNumber", phoneNumber);
               user.put("userPassword", userPassword);
               user.put("userLocation", userLocation);
               if(radio_buyer.isChecked()){
                   myRef = database.getReference("buyers");
                   myRef.push().setValue(user);
               }else if(radio_farmer.isChecked()){
                   myRef = database.getReference("farmers");
                   myRef.push().setValue(user);
               }
           }
       });

    }
}