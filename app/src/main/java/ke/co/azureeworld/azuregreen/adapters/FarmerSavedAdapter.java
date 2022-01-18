package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerOrderApplyActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerSavedDetailActivity;
import ke.co.azureeworld.azuregreen.modules.FarmerSaved;

public class FarmerSavedAdapter extends RecyclerView.Adapter<FarmerSavedAdapter.ViewHolder> {

    Context mContext;
    List<FarmerSaved> saved_crops;
    List<String> userIds;

    public FarmerSavedAdapter(Context mContext, List<FarmerSaved> saved_crops) {
        this.mContext = mContext;
        this.saved_crops = saved_crops;
        userIds = new ArrayList<>();
    }

    @NonNull
    @Override
    public FarmerSavedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_saved_farmer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerSavedAdapter.ViewHolder holder, int position) {
        FarmerSaved crop = saved_crops.get(position);
        holder.cropName.setText(crop.getCropName());
        holder.status.setText("Status: "+crop.getStatus());
        holder.orderDate.setText(crop.getOrderDate());
        holder.cropDescription.setText(crop.getCropDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FarmerSavedDetailActivity.class);
                intent.putExtra("cropName", crop.getCropName());
                intent.putExtra("cropDescription", crop.getCropDescription());
                intent.putExtra("status", crop.getStatus());
                intent.putExtra("orderDate", crop.getOrderDate());
                intent.putExtra("kgs", crop.getKgs());
                intent.putExtra("price", crop.getPrice());
                mContext.startActivity(intent);
            }
        });

        holder.btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FarmerOrderApplyActivity.class);
                intent.putExtra("price", crop.getPrice());
                intent.putExtra("cropName", crop.getCropName());
                mContext.startActivity(intent);
            }
        });
    }

    public void setIds(List<String> ids){
        userIds.clear();
        userIds.addAll(ids);
    }

    //    Delete Item
    public void deleteItem(int position){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("farmer_saved_crops");

        mRef.child(userIds.get(position)).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return saved_crops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cropName, cropDescription, status, orderDate;
        Button btn_apply;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cropName = itemView.findViewById(R.id.saved_title);
            cropDescription = itemView.findViewById(R.id.saved_description);
            status = itemView.findViewById(R.id.saved_status);
            orderDate = itemView.findViewById(R.id.saved_date);
            btn_apply = itemView.findViewById(R.id.btn_apply_order_recycler);
        }
    }
}
