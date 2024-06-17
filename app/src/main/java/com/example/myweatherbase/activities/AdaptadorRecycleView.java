package com.example.myweatherbase.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.DetailedActivity;
import com.example.myweatherbase.activities.model.List;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaptadorRecycleView
        extends RecyclerView.Adapter<AdaptadorRecycleView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Root root;
    private View.OnClickListener onClickListener;
    private int layout_displayed;

    public AdaptadorRecycleView(Context context, Root root){
        layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root= root;
        this.layout_displayed = R.layout.simple_element;

    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(layout_displayed,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setLayout_displayed(int layout_displayed){
        this.layout_displayed=layout_displayed;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycleView.ViewHolder holder, int position) {
        List lista = root.list.get(position);



        holder.txtView.setText(root.list.get(position).weather.get(0).description);
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + root.list.get(position).weather.get(0).icon + Parameters.ICON_URL_POST, holder.imageView);
        holder.tvTemp.setText("Temp: "+root.list.get(position).main.temp);
        holder.tvMax.setText("Max: "+root.list.get(position).main.temp+"");
        holder.tvMin.setText("Min: "+root.list.get(position).main.temp+"");

        Date date = new Date((long)root.list.get(position).dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateDay = new SimpleDateFormat("d/M/yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
        holder.textViewDayOfWeek.setText(dateDayOfWeek.format(date));
        holder.textViewDay.setText(dateDay.format(date));
        holder.tvHora.setText(hour.format(date));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailedActivity.class);
            intent.putExtra("extra", root);
            intent.putExtra("numero", position);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return root.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvHora, txtView, textViewDay, textViewDayOfWeek, tvTemp, tvMax, tvMin;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.txtView);
            textViewDay = itemView.findViewById(R.id.textViewDay);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            imageView = itemView.findViewById(R.id.imageView);
            tvHora = itemView.findViewById(R.id.TVHora);
            tvTemp = itemView.findViewById(R.id.tvTemp);
            tvMax = itemView.findViewById(R.id.tvMax);
            tvMin = itemView.findViewById(R.id.tvMin);

        }
    }
}
