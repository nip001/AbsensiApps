package com.batch9.absensiapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.batch9.absensiapps.R;
import com.batch9.absensiapps.entity.Absensi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AbsensiAdapter extends RecyclerView.Adapter<AbsensiAdapter.AbsensiViewHolder>{

    private ArrayList<Absensi> dataAbsensi;
    private Context context;

    public AbsensiAdapter(ArrayList<Absensi> dataAbsensi, Context context) {
        this.dataAbsensi = dataAbsensi;
        this.context = context;
    }

    @NonNull
    @Override
    public AbsensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history,parent,false);
        return new AbsensiAdapter.AbsensiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiViewHolder holder, int position) {
//        try {
            String dateKeluar = dataAbsensi.get(position).getTanggalKeluar();
            String dateMasuk = dataAbsensi.get(position).getTanggalMasuk();
            String jamMasuk = dateMasuk.substring(12);
            String tanggal = dateMasuk.substring(0,11);
            if(dateKeluar != null){
                String jamKeluar = dateKeluar.substring(12);
                holder.txtJamKeluar.setText("Keluar : "+ jamKeluar);

                int jamLembur= Integer.parseInt(jamKeluar.substring(0,2))  - Integer.parseInt(jamMasuk.substring(0,2))-10;
                if(jamLembur > 0 ){
                    holder.txtLembur.setText("Lembur : "+ jamLembur);
                }
            }

            holder.txtTanggal.setText("Tanggal : "+ tanggal);
            holder.txtJamMasuk.setText("Masuk : "+ jamMasuk);




//            SimpleDateFormat formatter =new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
//            Date jamMasuk = formatter.parse(dateMasuk);
//            if(dateKeluar != null){
//                Date jamKeluar = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").parse(dateKeluar);;
//                holder.txtJamKeluar.setText("Keluar : "+ jamKeluar.getTime());
//
//                long jamLembur= jamKeluar.getTime() - jamMasuk.getTime()-10;
//                if(jamLembur > 0 ){
//                    holder.txtLembur.setText("Lembur : "+ jamLembur);
//                }
//            }
//            holder.txtTanggal.setText("Tanggal : "+ jamMasuk.getDate());
//            holder.txtJamMasuk.setText("Masuk : "+ jamMasuk.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }
    }

    @Override
    public int getItemCount() {
        return dataAbsensi.size();
    }

    public class AbsensiViewHolder extends RecyclerView.ViewHolder{

        TextView txtTanggal,txtJamMasuk,txtJamKeluar,txtLembur;

        public AbsensiViewHolder(@NonNull View itemView){
            super(itemView);
            txtJamKeluar = itemView.findViewById(R.id.txtJamKeluar);
            txtJamMasuk = itemView.findViewById(R.id.txtJamMasuk);
            txtLembur = itemView.findViewById(R.id.txtLembur);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
        }
    }
}
