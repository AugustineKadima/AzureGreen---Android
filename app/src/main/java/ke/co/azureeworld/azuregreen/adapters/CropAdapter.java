package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.farmer.FarmerSellActivity;
import ke.co.azureeworld.azuregreen.modules.Crop;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ViewHolder> {

    List<Crop> crops;
    Context mContext;

    public CropAdapter(List<Crop> crops, Context mContext) {
        this.crops = crops;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CropAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_crop_farmer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropAdapter.ViewHolder holder, int position) {
        Crop crop = crops.get(position);
        holder.crop_location.setText(crop.getCropLocation());
        holder.crop_name.setText(crop.getCropName());
        holder.crop_description.setText(crop.getCropDescription());

        holder.btn_sell_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, FarmerSellActivity.class);
                intent.putExtra("cropName", crop.getCropName());
                intent.putExtra("cropDescription", crop.getCropDescription());
                intent.putExtra("cropLocation", crop.getCropLocation());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return crops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView crop_name, crop_description, crop_location;
        Button btn_sell_crop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            crop_name = itemView.findViewById(R.id.crop_name);
            crop_description = itemView.findViewById(R.id.crop_description);
            crop_location = itemView.findViewById(R.id.crop_location);
            btn_sell_crop = itemView.findViewById(R.id.btn_sell_crop);
        }
    }
}
