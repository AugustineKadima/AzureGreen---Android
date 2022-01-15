package ke.co.azureeworld.azuregreen.fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.view_models.FarmerRecordsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyerNewRecordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyerNewRecordsFragment extends Fragment {
    FarmerRecordsViewModel recordsViewModel;
    Button btn_create_record;
    EditText activity_name, activity_description, cost, email;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyerNewRecordsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyerNewRecordsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyerNewRecordsFragment newInstance(String param1, String param2) {
        BuyerNewRecordsFragment fragment = new BuyerNewRecordsFragment();
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
        return inflater.inflate(R.layout.fragment_buyer_new_records, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recordsViewModel = new ViewModelProvider(requireActivity()).get(FarmerRecordsViewModel.class);
        activity_name = view.findViewById(R.id.apply_product_name_farmer);
        activity_description = view.findViewById(R.id.apply_describe_farmer);
        cost = view.findViewById(R.id.activity_cost_farmer);
        btn_create_record = view.findViewById(R.id.btn_apply_farmer);
        email = view.findViewById(R.id.buyer_email);

        btn_create_record.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String activityName = activity_name.getText().toString().trim();
                String activityDescription = activity_description.getText().toString().trim();
                String Cost = cost.getText().toString().trim();
                String Email = email.getText().toString().trim();
                LocalDate localDate = LocalDate.now();
                if(activityDescription.isEmpty() && activityName.isEmpty() && Cost.isEmpty() && Email.isEmpty()){
                    Toast.makeText(getContext(), "Fill in all inputs to continue.", Toast.LENGTH_SHORT).show();
                }else if(activityName.isEmpty()){
                    activity_name.setError("Activity name required!");
                    activity_name.requestFocus();
                }else if(activityDescription.isEmpty()){
                    activity_description.setError("Activity description required!");
                    activity_description.requestFocus();
                }else if(Cost.isEmpty()){
                    cost.setError("Cost required!");
                    cost.requestFocus();
                }else if(Email.isEmpty()){
                    email.setError("Email required!");
                    email.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("Use the correct email format!");
                    email.requestFocus();
                }
                else{
                    recordsViewModel.setData(activityName, activityDescription,Cost,localDate.toString(), Email);
                }

            }
        });
    }
}