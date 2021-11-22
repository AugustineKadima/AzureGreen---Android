package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerOrderDetailActivity;
import ke.co.azureeworld.azuregreen.modules.Order;

public class FarmerOrderAdapter extends RecyclerView.Adapter<FarmerOrderAdapter.ViewHolder> {

    List<Order> orders;
    Context mContext;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference root = firebaseDatabase.getReference("farmer_saved_crops");

    public FarmerOrderAdapter(List<Order> orders,Context mContext) {
        this.mContext = mContext;
        this.orders = orders;
    }

    @NonNull
    @Override
    public FarmerOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_farmer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerOrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.status.setText("Status: " + order.getStatus());
        holder.description.setText(order.getCropDescription());
        holder.title.setText(order.getCropName());

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> savedItems = new HashMap<>();
                savedItems.put("cropName", order.getCropName());
                savedItems.put("cropDescription", order.getCropDescription());
                savedItems.put("status", "Open");
                savedItems.put("orderDate", order.getOrderDate());
                savedItems.put("orderTime", order.getOrderTime());

                root.push().setValue(savedItems).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(mContext, "Saved successfully!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mContext, "Failed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = firebaseDatabase.getReference("submissions");
                HashMap<String, String> application = new HashMap<>();
                application.put("cropName", order.getCropName());
                application.put("cropDescription", order.getCropDescription());
                application.put("orderDate", order.getOrderDate());
                application.put("orderTime", order.getOrderTime());
                application.put("price", "500");
                application.put("Kgs", "10");

                root.push().setValue(application);

            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView buyer_image;
        TextView buyer_name, title, description, status;
        Button save, apply;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_order_item_image);
            buyer_image = (ImageView) itemView.findViewById(R.id.buyer_image_orders);
            buyer_name = (TextView) itemView.findViewById(R.id.buyer_name_orders);
            title = (TextView) itemView.findViewById(R.id.buyer_order_title_orders);
            description = (TextView) itemView.findViewById(R.id.buyer_order_description_orders);
            status = (TextView) itemView.findViewById(R.id.buyer_order_status_orders);
            save = itemView.findViewById(R.id.btn_save_order_recycler);
            apply = itemView.findViewById(R.id.btn_apply_order_recycler);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, FarmerOrderDetailActivity.class));
                }
            });
        }
    }

}
