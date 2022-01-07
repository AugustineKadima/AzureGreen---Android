package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.buyer.BuyerMarketDetailActivity;
import ke.co.azureeworld.azuregreen.farmer.FarmerMarketDetailActivity;
import ke.co.azureeworld.azuregreen.modules.Sell;

public class BuyerMarketAdapter extends RecyclerView.Adapter<BuyerMarketAdapter.ViewHolder> {

    List<Sell> on_sell_items;
    Context mContext;

    public BuyerMarketAdapter(List<Sell> on_sell_items, Context mContext) {
        this.on_sell_items = on_sell_items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BuyerMarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_market_farmer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerMarketAdapter.ViewHolder holder, int position) {
        Sell sell = on_sell_items.get(position);
        holder.date.setText(sell.getSellDate());
        holder.description.setText(sell.getCropDescription());
        holder.title.setText(sell.getCropName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BuyerMarketDetailActivity.class);
                intent.putExtra("cropName", sell.getCropName());
                intent.putExtra("cropDescription", sell.getCropDescription());
                intent.putExtra("sellDate", sell.getSellDate());
                intent.putExtra("kgs", sell.getKgs());
                intent.putExtra("price", sell.getPrice());
                intent.putExtra("status", sell.getStatus());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return on_sell_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, title, description, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.sell_date);
            title = itemView.findViewById(R.id.sell_title);
            description = itemView.findViewById(R.id.sell_description);
            status = itemView.findViewById(R.id.sell_status);
        }
    }
}
