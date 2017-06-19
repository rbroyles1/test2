package com.example.rxbro.test2.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxbro.test2.Entities.User;
import com.example.rxbro.test2.R;

import java.util.ArrayList;

/**
 * Created by rxbro on 6/19/2017.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {
    private ArrayList<User> users;
    private Context context;
    ItemClickListener itemClickListener;

    public UserRecyclerAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);


    }
    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User currentUser = users.get(position);
        holder.userTV.setText(currentUser.getName());
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userTV;
        public ViewHolder(View itemView) {
            super(itemView);
            userTV = (TextView)itemView.findViewById(R.id.userTv);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

    }
    public void setClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

}
