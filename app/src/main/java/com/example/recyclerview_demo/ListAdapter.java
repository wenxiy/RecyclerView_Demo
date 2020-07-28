package com.example.recyclerview_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.InnerHolder> {
    private  List<Developer> mdevelopers;
    public ListAdapter(List<Developer> developers) {
        mdevelopers = developers;
    }

    @NonNull
    @Override
    public ListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_list,parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.InnerHolder holder, int position) {
        holder.setdata(mdevelopers.get(position));
    }

    @Override
    public int getItemCount() {
        if (mdevelopers!=null){
            return mdevelopers.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView maver;
        private TextView mitem_t1;
        private TextView mitem_t2;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            maver=itemView.findViewById(R.id.maver);
            mitem_t1=itemView.findViewById(R.id.mitem_1);
            mitem_t2=itemView.findViewById(R.id.mitem_2);
        }

        public void setdata(Developer developer) {
            maver.setImageURI(developer.getAvatar());
            mitem_t1.setText(developer.getName());
            mitem_t2.setText(developer.getUsername());
        }
    }
}
