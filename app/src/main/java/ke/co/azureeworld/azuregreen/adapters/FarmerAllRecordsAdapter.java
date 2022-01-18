package ke.co.azureeworld.azuregreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ke.co.azureeworld.azuregreen.R;
import ke.co.azureeworld.azuregreen.modules.FarmerRecord;

public class FarmerAllRecordsAdapter extends RecyclerView.Adapter<FarmerAllRecordsAdapter.ViewHolder> {

    List<FarmerRecord> records;
    Context mContext;
    List<String> userIds;

    public FarmerAllRecordsAdapter(List<FarmerRecord> records, Context mContext) {
        this.records = records;
        this.mContext = mContext;
        this.userIds = new ArrayList<>();
    }

    @NonNull
    @Override
    public FarmerAllRecordsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_farmer_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerAllRecordsAdapter.ViewHolder holder, int position) {
        FarmerRecord record = records.get(position);
        holder.name.setText(record.getActivityName());
        holder.date.setText(record.getDate());
        holder.description.setText(record.getActivityDescription());
        holder.cost.setText("Cost: " + record.getCost());
    }


    public void setIds(List<String> ids){
        userIds.clear();
        userIds.addAll(ids);
    }

    //    Delete Item
    public void deleteItem(int position){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("farmer_records");

        mRef.child(userIds.get(position)).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return records.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, date, cost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.activity_title);
            description = itemView.findViewById(R.id.record_description);
            date = itemView.findViewById(R.id.activity_date);
            cost = itemView.findViewById(R.id.activity_cost);
        }
    }
}
