package com.example.banner.recyclernutshell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CustomClickListener {

    final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    CustomeAdapter adapter;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        addButton = (Button) findViewById(R.id.btn_add);
        addButton.setOnClickListener(this);
        LinearLayoutManager llmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llmanager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
        ArrayList<String> strings = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J ", "K", "L", "M", "N", "O"));
        adapter = new CustomeAdapter(strings);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        if (itemId == R.id.btn_add) {
            Log.i(TAG, "Hello From btn_add");
            //adapter.addMoreListItems( Arrays.asList("P","Q","R"));
            adapter.addItemAtLastPosition("P");
        }
    }

    @Override
    public void onItemClick(int position) {
        /*Toast.makeText(MainActivity.this, String.format(Locale.ENGLISH, "Normal Click at position %d", position),
                Toast.LENGTH_SHORT).show();*/
        Log.i(TAG, "OnItemClick");
    }

    @Override
    public void onItemLongClick(int position) {

    }

    @Override
    public void onDeleteItemClick(int position) {
        adapter.deleteItem(position);
    }
}

