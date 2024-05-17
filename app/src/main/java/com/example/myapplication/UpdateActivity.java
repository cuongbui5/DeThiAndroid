package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    private EditText editSoXe,editQD,editGia,editKhuyenMai;
    private Button btnSua,btnBack;
    private MyDb myDB;
    private Taxi taxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editGia=findViewById(R.id.editGia);
        editSoXe=findViewById(R.id.editSoXe);
        editQD=findViewById(R.id.editQuanDuong);
        editKhuyenMai=findViewById(R.id.editKhuyenMai);
        Intent intent=getIntent();
        if(intent!=null){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                taxi=intent.getSerializableExtra("taxi",Taxi.class);
                if(taxi==null){
                    finish();
                }else {
                    editGia.setText(taxi.getGia()+"");
                    editQD.setText(taxi.getQuangDuong()+"");
                    editKhuyenMai.setText(taxi.getKhuyenMai()+"");
                    editSoXe.setText(taxi.getSoXe());
                }
            }
        }
        myDB = new MyDb(this, "TAXI", null, 1);
        btnSua=findViewById(R.id.btnSua);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String soxe=editSoXe.getText().toString();
                    double qd=Double.parseDouble(editQD.getText().toString());
                    int gia=Integer.parseInt(editGia.getText().toString());
                    int khuyenmai=Integer.parseInt(editKhuyenMai.getText().toString());
                    if(soxe==""||qd==0||gia==0||khuyenmai<0){
                        Toast.makeText(UpdateActivity.this,"Thong tin ko hop le",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Taxi taxi1=new Taxi(taxi.getMa(), soxe,qd,gia,khuyenmai);
                    myDB.updateTaxi(taxi1);
                    setResult(Activity.RESULT_OK);
                    finish();


                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });




    }
}
