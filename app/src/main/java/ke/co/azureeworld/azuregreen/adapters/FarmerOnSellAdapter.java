package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.Sell;

public class FarmerOnSellAdapter extends RecyclerView.Adapter<FarmerOnSellAdapter.ViewHolder> {

    List<Sell> on_sell_items;
    Context mContext;

    public FarmerOnSellAdapter(List<Sell> on_sell_items, Context mContext) {
        this.on_sell_items = on_sell_items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_on_sell_farmer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sell onSell = on_sell_items.get(position);
        holder.name.setText(onSell.getCropName());
        holder.description.setText(onSell.getCropDescription());
        holder.date.setText(onSell.getSellDate());
        holder.status.setText("Status: Open");
    }

    @Override
    public int getItemCount() {
        return on_sell_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, date, status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.sell_title);
            description = itemView.findViewById(R.id.sell_description);
            date = itemView.findViewById(R.id.sell_date);
            status = itemView.findViewById(R.id.sell_status);

        }
    }
}
