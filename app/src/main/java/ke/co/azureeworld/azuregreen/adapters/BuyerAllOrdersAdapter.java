package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.Order;

public class BuyerAllOrdersAdapter extends RecyclerView.Adapter<BuyerAllOrdersAdapter.ViewHolder> {

    Context mContext;
    List<Order> orders;

    public BuyerAllOrdersAdapter(Context mContext, List<Order> orders) {
        this.mContext = mContext;
        this.orders = orders;
    }

    @NonNull
    @Override
    public BuyerAllOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_buyer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerAllOrdersAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.order_name.setText(order.getCropName());
        holder.order_status.setText(order.getStatus());
        holder.order_description.setText(order.getCropDescription());
        holder.order_date.setText(order.getOrderDate());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_name, order_description, order_date, order_status;
        Button btn_delete, btn_edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_name = itemView.findViewById(R.id.order_title_buyer);
            order_description = itemView.findViewById(R.id.order_description_buyer);
            order_date = itemView.findViewById(R.id.order_date_buyer);
            order_status = itemView.findViewById(R.id.order_status_buyer);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_save_edit);
        }
    }
}
