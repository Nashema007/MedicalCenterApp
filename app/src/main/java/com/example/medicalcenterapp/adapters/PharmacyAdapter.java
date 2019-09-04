package com.example.medicalcenterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.models.PharmacyModal;

import java.util.ArrayList;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.MyViewHolder> {

    Context context;
    ArrayList<PharmacyModal> pharmacyModals;

    public PharmacyAdapter(Context context, ArrayList<PharmacyModal> pharmacyModals) {
        this.context = context;
        this.pharmacyModals = pharmacyModals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacy_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.pharmacyName.setText(pharmacyModals.get(position).getName());
        holder.pharmacyAddress.setText(pharmacyModals.get(position).getAddress());
        holder.pharmacyContact.setText(pharmacyModals.get(position).getContact());

    }

    @Override
    public int getItemCount() {
        return pharmacyModals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView pharmacyName;
        private TextView pharmacyAddress;
        private TextView pharmacyContact;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacyAddress = itemView.findViewById(R.id.pharmacyAddress);
            pharmacyName = itemView.findViewById(R.id.pharmacyName);
            pharmacyContact = itemView.findViewById(R.id.pharmacyContact);


        }
    }
}
