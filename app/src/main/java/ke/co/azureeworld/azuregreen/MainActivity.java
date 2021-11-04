package ke.co.azureeworld.azuregreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ke.co.azureeworld.azuregreen.setup.CreateAccountActivity;
import ke.co.azureeworld.azuregreen.setup.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_login, create_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login_main);
        create_account = (Button) findViewById(R.id.btn_create_account_main);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
            }
        });
    }
}