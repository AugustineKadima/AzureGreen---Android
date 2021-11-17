package ke.co.azureeworld.azuregreen.farmer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;

public class FarmerSellActivity extends AppCompatActivity {

    Button sell;
    ImageView back;
    EditText product_name,product_description, kgs, price;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("on_sell_items");

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
        product_name = (EditText) findViewById(R.id.apply_product_name_farmer);
        product_description = (EditText) findViewById(R.id.apply_describe_farmer);
        kgs = (EditText) findViewById(R.id.apply_kgs_farmer);
        price = (EditText) findViewById(R.id.apply_price_per_kg);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerSellActivity.this, FarmerHomeActivity.class));
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String cropName = product_name.getText().toString().trim();
                String cropDescription = product_description.getText().toString().trim();
                String Kgs = kgs.getText().toString().trim();
                String Price = price.getText().toString().trim();
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();

                if(cropDescription.isEmpty() && cropName.isEmpty() && Kgs.isEmpty() && Price.isEmpty()){
                    Toast.makeText(FarmerSellActivity.this, "Failed! Fill all form inputs to proceed!", Toast.LENGTH_SHORT).show();
                }else if(cropName.isEmpty()){
                    product_name.setError("Crop name required!");
                    product_name.requestFocus();
                }else if(cropDescription.isEmpty()){
                    product_description.setError("Crop description required!");
                    product_description.requestFocus();
                }else if(Kgs.isEmpty()){
                    kgs.setError("Quantity required!");
                    kgs.requestFocus();
                }else if(Price.isEmpty()){
                    price.setError("Price required!");
                    price.requestFocus();
                }else{
                    HashMap<String, String> itemOnSell = new HashMap<>();
                    itemOnSell.put("cropName", cropName);
                    itemOnSell.put("cropDescription", cropDescription);
                    itemOnSell.put("Kgs", Kgs);
                    itemOnSell.put("price", Price);
                    itemOnSell.put("sellDate", date.toString());
                    itemOnSell.put("sellTime", time.toString());

                    myRef.push().setValue(itemOnSell).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FarmerSellActivity.this, "Added to market successfully!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(FarmerSellActivity.this, "Failed! Try again.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }
}