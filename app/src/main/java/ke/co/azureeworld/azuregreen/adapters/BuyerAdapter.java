package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_buyer_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerAdapter.ViewHolder holder, int position) {
        User user = buyers.get(position);
//        holder.profileImage.setImageURI(user.getImageUrl().);
        holder.name.setText(user.getUserName());
        holder.phone.setText(user.getPhoneNumber());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return buyers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView name, email, phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.buyer_image_orders);
            name = itemView.findViewById(R.id.farmer_name);
            email = itemView.findViewById(R.id.farmer_email);
            phone = itemView.findViewById(R.id.farmer_phone);
        }
    }
}
