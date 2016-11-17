package com.example.banner.recyclernutshell;

/**
 * Created by Banner on 10/14/2016.
 */

public interface CustomClickListener {
    void onItemClick(int position);
    void onItemLongClick(int position);
    void onDeleteItemClick(int position);
}
