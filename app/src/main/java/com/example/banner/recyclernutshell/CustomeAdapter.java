package com.example.banner.recyclernutshell;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Banner on 10/14/2016.
 */

public class CustomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> listItem;
    private CustomClickListener onClickListener;

    public CustomeAdapter(ArrayList<String> listItem) {
        this.listItem = listItem;
    }

    public void setListItem(ArrayList<String> listItem){
        this.listItem=listItem;
        notifyDataSetChanged();
    }
    public void addMoreListItems(ArrayList<String> newListItems){
        this.listItem.addAll(newListItems);
        notifyItemRangeChanged(listItem.size()-newListItems.size(),newListItems.size());
    }
    public void addItemAtLastPosition(String string){
        listItem.add(string);
        notifyItemInserted(listItem.size()-1);
    }
    public void addItemAtFirstPostion(String string){
        listItem.add(0,string);
        notifyItemInserted(0);
    }
    public void changeItem(int position, String text) {
        listItem.set(position, text);
        notifyItemChanged(position);
    }
    public void deleteItem(int position){
        listItem.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String string=listItem.get(position);
        SimpleViewHolder simpleViewHolder=(SimpleViewHolder) holder;
        simpleViewHolder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    /*public void setOnClickListener(CustomClickListener onClickListener){
        this.onClickListener=onClickListener;
    }*/
    public void setOnClickListener(CustomClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,View.OnLongClickListener{
        TextView textView;
        ImageButton imageButton;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView);
            imageButton=(ImageButton)itemView.findViewById(R.id.imageButton);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            if(position==-1){
                return;
            }
            if(onClickListener==null){
                return;
            }
            if(view.getId()==imageButton.getId()){
                onClickListener.onDeleteItemClick(position);
            }else{
                onClickListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View view) {
        int position=getAdapterPosition();
            if(position==-1){
                return  false;
            }
            if(onClickListener==null){
                return false;
            }
            onClickListener.onItemLongClick(position);
            return true;
        }
    }
}
