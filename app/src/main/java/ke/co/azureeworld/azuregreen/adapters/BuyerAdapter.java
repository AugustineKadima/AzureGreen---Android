package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.modules.User;

public class BuyerAdapter extends RecyclerView.Adapter<BuyerAdapter.ViewHolder> {

    Context mContext;
    List<User> buyers;

    public BuyerAdapter(Context mContext, List<User> buyers) {
        this.mContext = mContext;
        this.buyers = buyers;
    }

    @NonNull
    @Override
    public BuyerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return buyers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
