/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.test.antony.megakittest.R;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.utils.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antony Mosin
 */

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.OwnerViewHolder> {

    private Context mContext;
    private List<OwnerData> mDataSet=new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    @Override
    public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OwnerViewHolder holder, int position) {
        holder.name.setText(mDataSet.get(position).getName());
        holder.experience.setText("Стаж работы: "+mDataSet.get(position).getExperience());
        holder.delete.setImageDrawable(new IconicsDrawable(mContext).icon(GoogleMaterial.Icon.gmd_delete).colorRes(R.color.accent).sizeDp(30));
        final OwnerViewHolder finHolder=holder;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClicked(finHolder.getAdapterPosition());
                }
            }});
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemDeleted(finHolder.getAdapterPosition());
                }
                mDataSet.remove(finHolder.getAdapterPosition());
                notifyItemRemoved(finHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public List<OwnerData> getDataSet() {
        return mDataSet;
    }

    public void setDataSet(List<OwnerData> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    class OwnerViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView experience;
        ImageButton delete;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.item_list_name);
            experience=(TextView) itemView.findViewById(R.id.item_list_second);
            delete=(ImageButton) itemView.findViewById(R.id.item_list_delete);
        }
    }

}
