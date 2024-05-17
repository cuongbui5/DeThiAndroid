package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDb myDB;
    private RecyclerView rcvTaxi;
    private FloatingActionButton btnAdd;
    private TaxiAdapter taxiAdapter;
    private List<Taxi> taxis;
    private ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new MyDb(this, "TAXI", null, 1);
        //myDB.initData();
        rcvTaxi=findViewById(R.id.rcvTaxi);
        rcvTaxi.setLayoutManager(new LinearLayoutManager(this));
        rcvTaxi.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        btnAdd=findViewById(R.id.btnAdd);
        taxis=myDB.getAll();
        Toast.makeText(this,taxis.size()+"",Toast.LENGTH_SHORT).show();
        taxiAdapter=new TaxiAdapter(taxis);
        taxiAdapter.sapxep();
        rcvTaxi.setAdapter(taxiAdapter);
        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        taxis=myDB.getAll();
                        taxiAdapter.setTaxis(taxis);
                        taxiAdapter.notifyDataSetChanged();

                    }
                });



    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            Taxi taxi=taxiAdapter.getTaxi(item.getGroupId());
            myDB.delete(taxi.getMa());
            taxis=myDB.getAll();
            taxiAdapter.setTaxis(taxis);
            taxiAdapter.notifyDataSetChanged();

        } else if (id == R.id.update) {
            Taxi taxi=taxiAdapter.getTaxi(item.getGroupId());
            Intent intent=new Intent(this,UpdateActivity.class);
            intent.putExtra("taxi",taxi);
            activityLauncher.launch(intent);


        }

        return super.onContextItemSelected(item);
    }
}