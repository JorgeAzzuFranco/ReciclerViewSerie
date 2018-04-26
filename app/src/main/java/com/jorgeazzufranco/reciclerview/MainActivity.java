package com.jorgeazzufranco.reciclerview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    SeriesAdapter adapter;
    ArrayList<Serie> series, bm;
    LinearLayoutManager lManager;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.SeriesNew);
        btn2 = findViewById(R.id.SeriesFav);
        series =new ArrayList<>();
        bm = new ArrayList<>();

        rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        rv.setLayoutManager(lManager);

        prepareSeries();
        rv.setAdapter(adapter);
    }

    public void boton1_list(View v){
        //adapter.setFalse();
        adapter = new SeriesAdapter(series, v.getContext());
        rv.setAdapter(adapter);
    }

    public void boton2_bookmark(View v){
        //adapter.setTrue();
        adapter = new SeriesAdapter(bm,v.getContext());
        rv.setAdapter(adapter);
    }

    private void prepareSeries() {
        String TAG = "Mensaje";
        series= new ArrayList<>();
        series.add(new Serie("World Trigger", "75", R.drawable.wtr, "Anime created by Daisuke Ashihara"));
        series.add(new Serie("AKB0048", "26", R.drawable.akb, "Anime created by  Shōji Kawamori"));
        series.add(new Serie("K-ON", "41", R.drawable.kon, "Anime created by Kakifly"));
        series.add(new Serie("Sora no Otoshimono", "13", R.drawable.sno, "Anime created by Suu Minazuki"));
    }

    public void addBookmark(Serie seriesfavoritas){
        bm.add(seriesfavoritas);
    }

    public void quitar(String name){
        int counter=0;

        for(Serie series : bm){
            if(series.getName() == name){
                break;
            }
            counter++;
        }
        bm.remove(counter);
        if(adapter.isOnBookmark()){
            adapter = new SeriesAdapter(bm, this);
            rv.setAdapter(adapter);
        }
    }
}