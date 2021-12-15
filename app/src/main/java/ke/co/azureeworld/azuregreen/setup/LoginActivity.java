package ke.co.azureeworld.azuregreen.setup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.buyer.BuyerSubmissionsActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerHomeActivity;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private RadioButton radio_farmer, radio_buyer;
    private TextView farmer_new_user_create_account;
    private EditText email, password;
    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        // Build a GoogleSignInClient with the options specified by gso.
//        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        // Check for existing Google Sign In account, if the user is already signed in
//// the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        updateUI(account);


        login = (Button) findViewById(R.id.btn_login_farmer);
        radio_buyer = (RadioButton) findViewById(R.id.login_buyer);
        radio_farmer = (RadioButton) findViewById(R.id.login_farmer);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        farmer_new_user_create_account = (TextView) findViewById(R.id.farmer_new_user_create_account);

        // ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(firebaseAuth.getCurrentUser() != null){
//
//                }
//            }
//        };

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
                    String Email = email.getText().toString().trim();
                    String Password = password.getText().toString().trim();
                    if(Email.isEmpty() && Password.isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please enter login details", Toast.LENGTH_SHORT).show();
                    }
                    else if(Email.isEmpty()){
                        email.setError("Email required!");
                        email.requestFocus();
                    }else if(Password.isEmpty()){
                        password.setError("Password required!");
                        password.requestFocus();
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                        email.setError("Enter a valid email address!");
                        email.requestFocus();
                    }else if(Password.length() < 6){
                        password.setError("Password should contain a minimum of 6 values");
                        password.requestFocus();
                    }
                    mAuth.signInWithEmailAndPassword(Email, Password);
                    startActivity(new Intent(LoginActivity.this, FarmerHomeActivity.class));
                }else if(radio_buyer.isChecked()){
                    String Email = email.getText().toString().trim();
                    String Password = password.getText().toString().trim();
                    if(Email.isEmpty() && Password.isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please enter login details", Toast.LENGTH_SHORT).show();
                    }
                    else if(Email.isEmpty()){
                        email.setError("Email required!");
                        email.requestFocus();
                    }else if(Password.isEmpty()){
                        password.setError("Password required!");
                        password.requestFocus();
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                        email.setError("Enter a valid email address!");
                        email.requestFocus();
                    }else if(Password.length() < 6){
                        password.setError("Password should contain a minimum of 6 values");
                        password.requestFocus();
                    }else{
                        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(LoginActivity.this, BuyerSubmissionsActivity.class));

                                }else{
                                    Toast.makeText(LoginActivity.this, "Login Failed! Check your credentials and try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
//
//                switch (v.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                    // ...
//                }
//            }
//
//            private void signIn() {
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//            }
            }
        });
    }
}