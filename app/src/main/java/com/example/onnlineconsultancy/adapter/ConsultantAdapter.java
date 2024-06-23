package com.example.onnlineconsultancy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onnlineconsultancy.Models.Consultant;
import com.example.onnlineconsultancy.R;
import com.example.onnlineconsultancy.activities.ConsultantProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class ConsultantAdapter extends RecyclerView.Adapter<ConsultantAdapter.ConsultantViewHolder> implements Filterable {

    private List<Consultant> consultantList;
    private List<Consultant> consultantListFull;
    private Context context;

    public ConsultantAdapter(List<Consultant> consultantList, Context context) {
        this.consultantList = consultantList;
        this.consultantListFull = new ArrayList<>(consultantList);
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultant, parent, false);
        return new ConsultantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultantViewHolder holder, int position) {
        Consultant consultant = consultantList.get(position);
        holder.profileImageView.setImageResource(consultant.getProfileImageResId());
        holder.nameTextView.setText(consultant.getName());
        holder.locationTextView.setText(consultant.getLocation());
        holder.serviceTextView.setText(consultant.getService());
        holder.contactNumberTextView.setText(consultant.getContactNumber());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ConsultantProfileActivity.class);
            intent.putExtra("consultant", consultant);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return consultantList.size();
    }

    @Override
    public Filter getFilter() {
        return consultantFilter;
    }

    private Filter consultantFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Consultant> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(consultantListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Consultant consultant : consultantListFull) {
                    if (consultant.getName().toLowerCase().contains(filterPattern) ||
                            consultant.getLocation().toLowerCase().contains(filterPattern) ||
                            consultant.getService().toLowerCase().contains(filterPattern)) {
                        filteredList.add(consultant);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            consultantList.clear();
            consultantList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class ConsultantViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImageView;
        TextView nameTextView;
        TextView locationTextView;
        TextView serviceTextView;
        TextView contactNumberTextView;

        ConsultantViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.consultantProfileImageView);
            nameTextView = itemView.findViewById(R.id.consultantNameTextView);
            locationTextView = itemView.findViewById(R.id.consultantLocationTextView);
            serviceTextView = itemView.findViewById(R.id.consultantServiceTextView);
            contactNumberTextView = itemView.findViewById(R.id.consultantno);
        }
    }
}
