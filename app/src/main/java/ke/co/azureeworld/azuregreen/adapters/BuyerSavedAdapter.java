package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.buyer.BuyerSavedDetailActivity;
import ke.co.azureeworld.azuregreen.buyer.BuyerSubmissionDetailActivity;
import ke.co.azureeworld.azuregreen.modules.BuyerSaved;

public class BuyerSavedAdapter extends RecyclerView.Adapter<BuyerSavedAdapter.ViewHolder> {

    List<BuyerSaved> savedList;
    Context mContext;
    private List<String> userIds;

    public BuyerSavedAdapter(List<BuyerSaved> savedList, Context mContext) {
        this.savedList = savedList;
        this.mContext = mContext;
        userIds = new ArrayList<>();
    }

    @NonNull
    @Override
    public BuyerSavedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_saved_buyer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerSavedAdapter.ViewHolder holder, int position) {
        BuyerSaved savedItem = savedList.get(position);
        holder.cropName.setText(savedItem.getCropName());
        holder.cropDescription.setText(savedItem.getCropDescription());
        holder.date.setText(savedItem.getOrderDate());
        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "You have bought.", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BuyerSavedDetailActivity.class);
                intent.putExtra("cropName",savedItem.getCropName());
                intent.putExtra("cropDescription", savedItem.getCropDescription());
                intent.putExtra("kgs", savedItem.getKgs());
                intent.putExtra("price", savedItem.getPrice());
                intent.putExtra("orderDate", savedItem.getOrderDate());
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
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("buyer_saved_items");

        mRef.child(userIds.get(position)).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cropName, cropDescription, date;
        Button btn_buy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cropName = itemView.findViewById(R.id.buyer_order_title_orders);
            cropDescription = itemView.findViewById(R.id.buyer_order_description_orders);
            date = itemView.findViewById(R.id.buyer_date_orders);
            btn_buy = itemView.findViewById(R.id.btn_buy_order_recycler);
        }
    }
}
