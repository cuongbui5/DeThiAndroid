package com.example.myapplication;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.TaxiViewHolder> {
    private List<Taxi> taxis;

    public TaxiAdapter(List<Taxi> taxis) {
        this.taxis = taxis;
    }

    public void setTaxis(List<Taxi> taxis) {
        this.taxis = taxis;
    }
    public void sapxep(){
        taxis.sort(Comparator.comparing(Taxi::getSoXe));
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public TaxiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaxiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.taxi_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaxiViewHolder holder, int position) {
        Taxi taxi=taxis.get(position);
        holder.tvSoXe.setText(taxi.getSoXe());

        String formattedValue = String.format("%.2f", taxi.tinhTongTien());
        double tongTien = Double.parseDouble(formattedValue);
        holder.tvTongTien.setText(tongTien+"");
        holder.tvQuangDuong.setText("Quãng đường : "+taxi.getQuangDuong()+"km");


    }
    public Taxi getTaxi(int i){
        return taxis.get(i);
    }


    @Override
    public int getItemCount() {
        return taxis.size();
    }

    public static class TaxiViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tvSoXe,tvQuangDuong,tvTongTien;

        public TaxiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoXe=itemView.findViewById(R.id.tvSoXe);
            tvQuangDuong=itemView.findViewById(R.id.tvQuangDuong);
            tvTongTien=itemView.findViewById(R.id.tvTongTien);
            itemView.setOnCreateContextMenuListener(this);


        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), R.id.update, 0, "Sửa");
            menu.add(getAdapterPosition(), R.id.delete, 1, "Xóa");
        }

    }
}
