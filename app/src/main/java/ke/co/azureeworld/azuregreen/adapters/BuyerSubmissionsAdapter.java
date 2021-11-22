package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.Submission;

public class BuyerSubmissionsAdapter extends RecyclerView.Adapter<BuyerSubmissionsAdapter.ViewHolder> {

    Context mContext;
    List<Submission> submissions;

    public BuyerSubmissionsAdapter(Context mContext, List<Submission> submissions) {
        this.mContext = mContext;
        this.submissions = submissions;
    }

    @NonNull
    @Override
    public BuyerSubmissionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_buyer_submission, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerSubmissionsAdapter.ViewHolder holder, int position) {
        Submission submission = submissions.get(position);
        holder.crop_name.setText(submission.getCropName());
        holder.crop_description.setText(submission.getCropDescription());
        holder.date.setText(submission.getOrderDate());

        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Save clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Buy clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return submissions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView crop_name, crop_description, date;
        Button btn_save, btn_buy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crop_name = itemView.findViewById(R.id.buyer_order_title_orders);
            crop_description = itemView.findViewById(R.id.buyer_order_description_orders);
            date = itemView.findViewById(R.id.buyer_date_orders);
            btn_save =itemView.findViewById(R.id.btn_save_order_recycler);
            btn_buy = itemView.findViewById(R.id.btn_buy_order_recycler);

        }
    }
}
