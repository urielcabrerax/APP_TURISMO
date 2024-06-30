package com.example.appturismo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listViewTurismo;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTurismo = findViewById(R.id.listViewTurismo);
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listViewTurismo.setAdapter(adapter);

        GuiaTuristica();

    }
    private  void  GuiaTuristica(){
        Methods methods = RetrofitClient.getRetrofitClientInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                int codeRequest = response.code();

                Toast.makeText(getApplication(),"Conexion OK: "+codeRequest, Toast.LENGTH_SHORT).show();
                ArrayList<Model.data> data = response.body().getData();

                for(Model.data data1 : data){
                    String item = data1.getCategoria() + "\n" + data1.getNombre_lugar() + "\n" + data1.getTelefono() ;
                    dataList.add(item);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                dataList.add("Error: "+t.getMessage());
                adapter.notifyDataSetChanged();
            }
        });

    }
}