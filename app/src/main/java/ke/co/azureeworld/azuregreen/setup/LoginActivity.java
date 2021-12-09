package ke.co.azureeworld.azuregreen.setup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.buyer.BuyerSubmissionsActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;

public class LoginActivity extends AppCompatActivity {

    Button login;
    RadioButton radio_farmer, radio_buyer;
    TextView farmer_new_user_create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login_farmer);
        radio_buyer = (RadioButton) findViewById(R.id.login_buyer);
        radio_farmer = (RadioButton) findViewById(R.id.login_farmer);
        farmer_new_user_create_account = (TextView) findViewById(R.id.farmer_new_user_create_account);

        farmer_new_user_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!radio_farmer.isChecked() && !radio_buyer.isChecked()){
                    Toast.makeText(LoginActivity.this, "Are you logging in as a farmer or buyer? Select the appropriate option above.", Toast.LENGTH_LONG).show();
                }
                else if(radio_farmer.isChecked()){
                    startActivity(new Intent(LoginActivity.this, FarmerHomeActivity.class));
                }else if(radio_buyer.isChecked()){
                    startActivity(new Intent(LoginActivity.this, BuyerSubmissionsActivity.class));
                }
            }
        });
    }
}