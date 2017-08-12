package com.test.antony.megakittest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.utils.pojo.AutoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12.08.17.
 */

public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.AutoViewHolder> {

    private List<AutoItem> mDataSet=new ArrayList<>();
    private Context mContext;

    @Override
    public AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_auto, parent, false);
        return new AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, int position) {
        holder.name.setText(mDataSet.get(position).getName());
        holder.owner.setText(mDataSet.get(position).getOwner());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class AutoViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView owner;

        public AutoViewHolder(View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.item_auto_image);
            name=(TextView) itemView.findViewById(R.id.item_auto_name);
            owner=(TextView) itemView.findViewById(R.id.item_auto_owner);
        }
    }

}
