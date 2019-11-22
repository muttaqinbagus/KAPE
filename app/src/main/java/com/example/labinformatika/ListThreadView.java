package com.example.labinformatika;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListThreadView extends ArrayAdapter <ModelThread> {

    private ArrayList<ModelThread> list;
    private LayoutInflater inflater;
    private int res;

    public ListThreadView(@NonNull Context context, int resource, ArrayList<ModelThread> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ListThreadView.MyHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new ListThreadView.MyHolder();

//            holder.ID = (TextView) convertView.findViewById(R.id.id);
            holder.JUDUL = (TextView) convertView.findViewById(R.id.judul_thread);
            holder.KETERANGAN = (TextView) convertView.findViewById(R.id.keterangan);
            holder.NAMA = (TextView) convertView.findViewById(R.id.nama_mahasiswa);
            holder.ThreadView = (CardView) convertView.findViewById(R.id.cardview_list_thread);

            convertView.setTag(holder);

        } else {

            holder = (ListThreadView.MyHolder) convertView.getTag();
        }

        holder.JUDUL.setText(list.get(position).getJudul());
        holder.KETERANGAN.setText(list.get(position).getKeterangan());
        holder.NAMA.setText(list.get(position).getNamaMahasiswa());
        holder.ThreadView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                final Context context = view.getContext();

                Intent intent = new Intent(context, ThreadDetail.class);
                intent.putExtra("id_thread", String.valueOf(list.get(position).getIdThread()));
                ((AppCompatActivity) context).startActivity(intent);

            }
        }));


        return convertView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelThread object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class MyHolder {

        //TextView ID;
        TextView JUDUL;
        TextView KETERANGAN;
        TextView NAMA;

        CardView ThreadView;

    }
}