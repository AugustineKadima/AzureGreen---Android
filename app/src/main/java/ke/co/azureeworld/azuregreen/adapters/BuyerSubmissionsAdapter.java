package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.buyer.BuyActivity;
import ke.co.azureeworld.azuregreen.buyer.BuyerSubmissionDetailActivity;
import ke.co.azureeworld.azuregreen.modules.Submission;
import ke.co.azureeworld.azuregreen.view_models.EmailViewModel;

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
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference mRef = firebaseDatabase.getReference("buyer_saved_items");
                HashMap<String, String> saved = new HashMap<>();
                saved.put("cropName", submission.getCropName());
                saved.put("cropDescription", submission.getCropDescription());
                saved.put("orderDate", submission.getOrderDate());
                saved.put("price", submission.getPrice());
                saved.put("Kgs", submission.getKgs());
                saved.put("orderTime", submission.getOrderTime());
                saved.put("email", EmailViewModel.email);

                mRef.push().setValue(saved).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(mContext, "Saved successfully!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mContext, "Failed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BuyActivity.class);
                intent.putExtra("email", submission.getFarmerEmail());
                intent.putExtra("cropName", submission.getCropName());
                intent.putExtra("farmerPhone", submission.getFarmerPhone());
                mContext.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BuyerSubmissionDetailActivity.class);
                    intent.putExtra("cropName",submission.getCropName());
                    intent.putExtra("cropDescription", submission.getCropDescription());
                    intent.putExtra("kgs", submission.getKgs());
                    intent.putExtra("price", submission.getPrice());
                    intent.putExtra("orderDate", submission.getOrderDate());
                mContext.startActivity(intent);
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
