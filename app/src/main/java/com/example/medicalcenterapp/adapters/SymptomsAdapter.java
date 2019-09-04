package com.example.medicalcenterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.models.SymptomsModal;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<SymptomsModal> symp;
    public static ArrayList<SymptomsModal> sympFilter;

    public SymptomsAdapter(Context context, ArrayList<SymptomsModal> symp) {
        this.context = context;
        this.symp = symp;
        sympFilter = symp;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_symptom_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return sympFilter.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.symptomText.setText(sympFilter.get(position).getSymptomTitle());
    }
    public void removeItem(int position) {
        sympFilter.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }
    public void restoreItem(SymptomsModal item, int position) {
        sympFilter.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
                if (charString.isEmpty()) {
                sympFilter = symp;
            } else {
                ArrayList<SymptomsModal> filteredList = new ArrayList<>();
                for (SymptomsModal row : symp) {

                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    if (row.getSymptomTitle().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row);
                    }
                }

                sympFilter = filteredList;
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = sympFilter;
                return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            sympFilter = (ArrayList<SymptomsModal>) filterResults.values;
            notifyDataSetChanged();
        }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       public LinearLayout viewForeground ;
       public RelativeLayout viewBackground;
       private TextView symptomText;


       public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomText = itemView.findViewById(R.id.symptom);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            viewBackground = itemView.findViewById(R.id.view_background);
        }
    }
}
