package com.test.antony.megakittest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.utils.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antony Mosin
 */

public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.AutoViewHolder> {

    private List<AutoData> mDataSet=new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mItemClickListener;

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
        final AutoViewHolder finHolder=holder;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClicked(finHolder.getAdapterPosition());
                }
            }
        });
        holder.name.setText(mDataSet.get(position).getName());
        holder.owner.setText(mDataSet.get(position).getOwner().getName());
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setDataSet(List<AutoData> autoDatas){
        mDataSet=autoDatas;
        notifyDataSetChanged();
    }

    public List<AutoData> getDataSet() {
        return mDataSet;
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
