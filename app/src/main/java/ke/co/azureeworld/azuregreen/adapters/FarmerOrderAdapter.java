package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerOrderDetailActivity;
import ke.co.azureeworld.azuregreen.modules.Order;

public class FarmerOrderAdapter extends RecyclerView.Adapter<FarmerOrderAdapter.ViewHolder> {

    List<Order> orders;
    Context mContext;

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

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView buyer_image;
        TextView buyer_name, title, description, status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_order_item_image);
            buyer_image = (ImageView) itemView.findViewById(R.id.buyer_image_orders);
            buyer_name = (TextView) itemView.findViewById(R.id.buyer_name_orders);
            title = (TextView) itemView.findViewById(R.id.buyer_order_title_orders);
            description = (TextView) itemView.findViewById(R.id.buyer_order_description_orders);
            status = (TextView) itemView.findViewById(R.id.buyer_order_status_orders);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, FarmerOrderDetailActivity.class));
                }
            });
        }
    }

}
