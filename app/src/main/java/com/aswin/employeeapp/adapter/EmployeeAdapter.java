package com.aswin.employeeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aswin.employeeapp.R;
import com.aswin.employeeapp.activities.EmployeeDetailActivity;
import com.aswin.employeeapp.activities.MainActivity;
import com.aswin.employeeapp.helper.DBHandler;
import com.aswin.employeeapp.model.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> implements Filterable {

    // creating a variable for array list and context.
    private ArrayList<EmployeeModel> employeeModalArrayList;
    private ArrayList<EmployeeModel> FullList;

    private Context context;
    private DBHandler dbHandler;


    // creating a constructor for our variables.
    public EmployeeAdapter(ArrayList<EmployeeModel> employeeModalArrayList, Context context) {
        this.employeeModalArrayList = employeeModalArrayList;
        this.context = context;
        FullList = new ArrayList<>(employeeModalArrayList);

    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        EmployeeModel modal = employeeModalArrayList.get(position);

        // setting data to our views of recycler view.
        holder.EmployeeNameTV.setText(modal.getName());
        holder.EmployeeCompanyName.setText(modal.getCompanyName());
        Picasso.get().load(modal.getProfileImage()).into(holder.profileIV);

        holder.itemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, modal.getName()+" Click event", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, EmployeeDetailActivity.class);
                i.putExtra("employeeObj",modal);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return employeeModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView EmployeeNameTV, EmployeeCompanyName;
        private ImageView profileIV;
        private CardView itemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            profileIV = itemView.findViewById(R.id.idIVProfileImage);
            EmployeeNameTV = itemView.findViewById(R.id.idTVname);
            EmployeeCompanyName = itemView.findViewById(R.id.idTvCompanyName);
            itemCardView = itemView.findViewById(R.id.MainCV);
        }
    }

    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }

    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<EmployeeModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (EmployeeModel item : FullList) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            employeeModalArrayList.clear();
            employeeModalArrayList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}

