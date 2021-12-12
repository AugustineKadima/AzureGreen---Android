package ke.co.azureeworld.azuregreen.setup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.ImageUrlModel;

public class CreateAccountActivity extends AppCompatActivity {

    EditText name, email, phone_number, password, confirm_password, location;
    TextView attach_photo, farmer_new_user_create_account ,upload_photo;
    RadioButton radio_farmer, radio_buyer;
    Button btn_create_account;
    ImageView photo_link;
    private Uri imageUri;
    String imageDownloadLink;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            photo_link.setImageURI(imageUri);
        }
    }

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
       attach_photo = (TextView) findViewById(R.id.attach_photo);
       photo_link = (ImageView) findViewById(R.id.photo_link);
       radio_buyer = (RadioButton) findViewById(R.id.create_account_buyer);
       radio_farmer = (RadioButton) findViewById(R.id.create_account_farmer);
       btn_create_account = (Button) findViewById(R.id.btn_create_account_get_started);
       farmer_new_user_create_account = (TextView) findViewById(R.id.farmer_new_user_create_account);
       upload_photo = (TextView) findViewById(R.id.upload_photo);

       upload_photo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(imageUri != null){
                   uploadToFirebase(imageUri);
                   Toast.makeText(CreateAccountActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();


               }else{
                   Toast.makeText(CreateAccountActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
               }
           }
       });

        farmer_new_user_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
            }
        });



       btn_create_account.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onClick(View view) {
               String userName = name.getText().toString().trim();
               String userEmail = email.getText().toString().trim();
               String phoneNumber = phone_number.getText().toString().trim();
               String userPassword = password.getText().toString().trim();
               String confirmPassword = confirm_password.getText().toString().trim();
               String userLocation = location.getText().toString().trim();
               LocalDate date = LocalDate.now();


               HashMap<String, String> user = new HashMap<>();
               user.put("userName", userName);
               user.put("userEmail", userEmail);
               user.put("phoneNumber", phoneNumber);
               user.put("userPassword", userPassword);
               user.put("userLocation", userLocation);
               user.put("imageUrl", imageDownloadLink);
               user.put("accountCreationDate", date.toString());

               if(radio_buyer.isChecked()){
                   myRef = database.getReference("buyers");
                   myRef.push().setValue(user);
               }else if(radio_farmer.isChecked()){
                   myRef = database.getReference("farmers");
                   myRef.push().setValue(user);
               }
           }
       });

       attach_photo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent();
               intent.setAction(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(intent, 2);
           }
       });



    }

    private void uploadToFirebase(Uri uri) {
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                            imageDownloadLink = uri.toString();
//                        ImageUrlModel model = new ImageUrlModel(uri.toString());
//                        String modelId = root.push().getKey();
//                        root.child(modelId).setValue(model);
//                        Toast.makeText(CreateAccountActivity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateAccountActivity.this, "Uploading failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}