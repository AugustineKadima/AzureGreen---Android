package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.Sell;

public class FarmerMarketAdapter extends RecyclerView.Adapter<FarmerMarketAdapter.ViewHolder> {

    List<Sell> on_sell_items;
    Context mContext;

    public FarmerMarketAdapter(List<Sell> on_sell_items, Context mContext) {
        this.on_sell_items = on_sell_items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FarmerMarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_on_sell_farmer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerMarketAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return on_sell_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
