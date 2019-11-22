package com.example.labinformatika;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListMateriPraktikum extends ArrayAdapter <ModelMateri> {

    private ArrayList<ModelMateri> list;
    private LayoutInflater inflater;
    private int res;
    ProgressDialog loading;
    public ListMateriPraktikum(@NonNull Context context, int resource, ArrayList<ModelMateri> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ListMateriPraktikum.MyHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new ListMateriPraktikum.MyHolder();

//            holder.ID = (TextView) convertView.findViewById(R.id.id);
            holder.NAMA = (TextView) convertView.findViewById(R.id.thread_materi);
            holder.ThreadMateri = (CardView) convertView.findViewById(R.id.cardview_thread_materi);

            convertView.setTag(holder);

        } else {

            holder = (ListMateriPraktikum.MyHolder) convertView.getTag();
        }

        holder.NAMA.setText(list.get(position).getNamaMateri());
        holder.ThreadMateri.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                final Context context = view.getContext();

                Intent intent = new Intent(context, ThreadView.class);
                intent.putExtra("id_materi", String.valueOf(list.get(position).getIdM()));
                ((AppCompatActivity) context).startActivity(intent);

//                Toast.makeText(context, "NGAPAIN !!!!!!1", Toast.LENGTH_SHORT).show();

            }
        }));

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelMateri object) {
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
        TextView NAMA;
        CardView ThreadMateri;

    }
}
