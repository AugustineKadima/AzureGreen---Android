package ke.co.azureeworld.azuregreen.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.User;
import ke.co.azureeworld.azuregreen.view_models.BuyerOrderViewModel;
import ke.co.azureeworld.azuregreen.view_models.EmailViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyerNewOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyerNewOrderFragment extends Fragment {
    BuyerOrderViewModel orderViewModel;
    EditText crop_name, crop_description, _kgs, _price, _location;
    Button btn_create_order;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference buyerRef = firebaseDatabase.getReference().child("buyers");
    User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyerNewOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyerNewOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyerNewOrderFragment newInstance(String param1, String param2) {
        BuyerNewOrderFragment fragment = new BuyerNewOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buyer_new_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderViewModel =  new ViewModelProvider(getActivity()).get(BuyerOrderViewModel.class);
        crop_name = view.findViewById(R.id.crop_name_buyer);
        crop_description = view.findViewById(R.id.crop_description_buyer);
        _kgs = view.findViewById(R.id.kgs_buyer);
        _price = view.findViewById(R.id.price_buyer);
        _location = view.findViewById(R.id.crop_location_buyer);
        btn_create_order = view.findViewById(R.id.btn_create_order_buyer);

        user = new User();
        buyerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User newBuyer = dataSnapshot.getValue(User.class);
                    if(newBuyer.getEmail().equals(EmailViewModel.email)){
                        user = newBuyer;
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_create_order.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String cropName = crop_name.getText().toString().trim();
                String cropDescription = crop_description.getText().toString().trim();
                String kgs = _kgs.getText().toString().trim();
                String price = _price.getText().toString().trim();
                String location = _location.getText().toString().trim();
                LocalDate orderDate = LocalDate.now();
                LocalTime orderTime = LocalTime.now();

                if(cropDescription.isEmpty() && cropName.isEmpty() && kgs.isEmpty() && price.isEmpty() && location.isEmpty()){
                    Toast.makeText(getContext(), "Failed! Fill all input fields to continue.", Toast.LENGTH_SHORT).show();
                }else if(cropName.isEmpty()){
                    crop_name.setError("Name required!");
                    crop_name.requestFocus();
                }else if(cropDescription.isEmpty()){
                    crop_description.setError("Description required!");
                    crop_description.requestFocus();
                }else if(kgs.isEmpty()){
                    _kgs.setError("Quantity required!");
                    _kgs.requestFocus();
                }else if(price.isEmpty()){
                    _price.setError("Price required!");
                    _price.requestFocus();
                }else if(location.isEmpty()){
                    _location.setError("Location required!");
                    _location.requestFocus();
                }else{
                    orderViewModel.setData(cropName,cropDescription,kgs,orderDate.toString(), orderTime.toString(),price, "Open", location, user.getEmail(), user.getPhoneNumber());
                    crop_name.setText("");
                    crop_description.setText("");
                    _kgs.setText("");
                    _price.setText("");
                    _location.setText("");
                }
            }
        });
    }
}