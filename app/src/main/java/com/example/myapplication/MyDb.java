package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDb extends SQLiteOpenHelper {
    private static final String tableName="TAXI";
    private static final String MA="MA";
    private static final String SOXE="SOXE";
    private static final String QUANGDUONG="QUANGDUONG";
    private static final String GIA="GIA";
    private static final String KHUYENMAI="KHUYENMAI";

    public MyDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate="Create table if not exists "+tableName+" ("
                +MA+ " INTEGER Primary key AUTOINCREMENT, "
                +SOXE+" Text, "
                +QUANGDUONG+" Double, "
                +GIA+" Int, "
                +KHUYENMAI+" Int)";
        db.execSQL(sqlCreate);

    }

    public Long addNew(Taxi taxi){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SOXE,taxi.getSoXe());
        contentValues.put(QUANGDUONG,taxi.getQuangDuong());
        contentValues.put(GIA,taxi.getGia());
        contentValues.put(KHUYENMAI,taxi.getKhuyenMai());
        Long id= db.insert(tableName,null,contentValues);
        db.close();
        return id;
    }
    public int updateTaxi(Taxi taxi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SOXE, taxi.getSoXe());
        values.put(QUANGDUONG, taxi.getQuangDuong());
        values.put(GIA, taxi.getGia());
        values.put(KHUYENMAI, taxi.getKhuyenMai());


        // Updating row
        int result = db.update(tableName, values, MA + " = ?", new String[]{String.valueOf(taxi.getMa())});
        db.close();
        return result;
    }

    // Phương thức xóa thi sinh theo id
    public void delete(int ma) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, MA + " = ?", new String[]{ma+""});
        db.close();
    }

    public void initData(){
        Taxi taxi=new Taxi("A-01",274.1,100,20);
        Taxi taxi1=new Taxi("D-01",174.1,150,10);
        Taxi taxi2=new Taxi("E-01",294.1,1990,15);
        Taxi taxi3=new Taxi("D-01",74.1,140,9);
        Taxi taxi4=new Taxi("D-01",94.1,170,30);
        Taxi taxi5=new Taxi("B-01",2174.1,3100,40);
        addNew(taxi);
        addNew(taxi1);
        addNew(taxi2);
        addNew(taxi3);
        addNew(taxi4);
        addNew(taxi5);





    }

    public List<Taxi> getAll(){
        List<Taxi> taxis=new ArrayList<>();
        String sql="Select * from "+tableName;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Taxi taxi=new Taxi();
                taxi.setMa(cursor.getInt(0));
                taxi.setSoXe(cursor.getString(1));
                taxi.setQuangDuong(cursor.getDouble(2));
                taxi.setGia(cursor.getInt(3));
                taxi.setKhuyenMai(cursor.getInt(4));
                taxis.add(taxi);
            }


        }
        return taxis;
    }

    /*public void updateContact(Integer id,Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();

    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+tableName);
        onCreate(db);

    }

}
