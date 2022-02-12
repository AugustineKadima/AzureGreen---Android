package ke.co.azureeworld.azuregreen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.view_models.CropViewModel;
import ke.co.azureeworld.azuregreen.view_models.FarmerRecordsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCropToStallFarmerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCropToStallFarmerFragment extends Fragment {
    EditText crop_name, crop_description, crop_location;
    Button btn_add_crop;
    CropViewModel cropViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCropToStallFarmerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCropToStallFarmerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCropToStallFarmerFragment newInstance(String param1, String param2) {
        AddCropToStallFarmerFragment fragment = new AddCropToStallFarmerFragment();
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
        return inflater.inflate(R.layout.fragment_add_crop_to_stall_farmer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crop_name = view.findViewById(R.id.crop_name);
        crop_description = view.findViewById(R.id.crop_description);
        crop_location = view.findViewById(R.id.crop_location);
        btn_add_crop = view.findViewById(R.id.btn_add_crop);

        btn_add_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cropViewModel = new ViewModelProvider(requireActivity()).get(CropViewModel.class);
                String cropName = crop_name.getText().toString().trim();
                String cropDescription = crop_description.getText().toString().trim();
                String cropLocation = crop_location.getText().toString().trim();

                if(cropDescription.isEmpty() && cropName.isEmpty() && cropLocation.isEmpty()){
                    Toast.makeText(getContext(), "Failed! Fill all input fields to continue.", Toast.LENGTH_SHORT).show();
                }else if(cropName.isEmpty()){
                    crop_name.setError("Crop name required!");
                    crop_name.requestFocus();
                }else if(cropDescription.isEmpty()){
                    crop_description.setError("Crop description required!");
                    crop_description.requestFocus();
                }else if(cropLocation.isEmpty()){
                    crop_location.setError("Location required!");
                    crop_location.requestFocus();
                }else{
                    cropViewModel.setData(cropName, cropDescription, cropLocation);
                    crop_name.setText("");
                    crop_description.setText("");
                    crop_location.setText("");
                }
            }
        });

    }
}