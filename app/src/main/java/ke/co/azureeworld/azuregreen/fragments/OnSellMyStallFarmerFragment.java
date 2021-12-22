package ke.co.azureeworld.azuregreen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.adapters.FarmerOnSellAdapter;
import ke.co.azureeworld.azuregreen.modules.Sell;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnSellMyStallFarmerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnSellMyStallFarmerFragment extends Fragment {
    RecyclerView recyclerView;
    FarmerOnSellAdapter adapter;
    List<Sell> on_sell_items;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference().child("on_sell_items");


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnSellMyStallFarmerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnSellMyStallFarmerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnSellMyStallFarmerFragment newInstance(String param1, String param2) {
        OnSellMyStallFarmerFragment fragment = new OnSellMyStallFarmerFragment();
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
        return inflater.inflate(R.layout.fragment_on_sell_my_stall_farmer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_on_sell);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        on_sell_items = new ArrayList<>();
        adapter = new FarmerOnSellAdapter(on_sell_items, getContext());
        recyclerView.setAdapter(adapter);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Sell item = dataSnapshot.getValue(Sell.class);
                    on_sell_items.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}