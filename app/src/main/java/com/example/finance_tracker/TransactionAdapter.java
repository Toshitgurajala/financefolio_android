package com.example.finance_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<usertransaction> transactionsList;

    public TransactionAdapter(List<usertransaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public void updateData(List<usertransaction> newList) {
        if (transactionsList == null) {
            transactionsList = new ArrayList<>(); // Initialize the list if it's null
        } else {
            transactionsList.clear(); // Clear the list if it's not null
        }
        if (newList != null) {
            transactionsList.addAll(newList); // Add items from the newList
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        usertransaction transaction = transactionsList.get(position);
        holder.textViewTransactionId.setText("Transaction ID: " + transaction.getTransactionId());
        holder.textViewDateTime.setText("Date and Time: " + transaction.getDateTime());
        holder.textViewAmount.setText("Amount: " + transaction.getAmount());
        holder.textViewReason.setText("Reason: " + transaction.getReason());
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTransactionId;
        TextView textViewDateTime;
        TextView textViewAmount;
        TextView textViewReason;

        TransactionViewHolder(View itemView) {
            super(itemView);
            textViewTransactionId = itemView.findViewById(R.id.textViewTransactionId);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            textViewReason = itemView.findViewById(R.id.textViewReason);
        }
    }
}
