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
import ke.co.azureeworld.azuregreen.modules.FarmerSaved;

public class FarmerSavedAdapter extends RecyclerView.Adapter<FarmerSavedAdapter.ViewHolder> {

    Context mContext;
    List<FarmerSaved> saved_crops;

    public FarmerSavedAdapter(Context mContext, List<FarmerSaved> saved_crops) {
        this.mContext = mContext;
        this.saved_crops = saved_crops;
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
    }

    @Override
    public int getItemCount() {
        return saved_crops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cropName, cropDescription, status, orderDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cropName = itemView.findViewById(R.id.saved_title);
            cropDescription = itemView.findViewById(R.id.saved_description);
            status = itemView.findViewById(R.id.saved_status);
            orderDate = itemView.findViewById(R.id.saved_date);
        }
    }
}
