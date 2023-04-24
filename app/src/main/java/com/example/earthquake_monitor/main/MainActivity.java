package com.example.earthquake_monitor.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /**
         *se especifica que recycler va a usar
         */
        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this)); // con esto se le coloca que es una lista vertical
        viewModel = new ViewModelProvider( this).get(MainViewModel.class);


        /**
         *para mostrar los terremotos en el recycler se usará un adapter
         *se muestra el uso del adaptador
         */
        EqAdapter adapter = new EqAdapter(this);
        /**
         * Al presionar algun objeto dentro de la lista se mostrara la ciudad que contiene
         */
        adapter.setOnItemClickListener(earthquake ->
                Toast.makeText(MainActivity.this, earthquake.getPlace(),
                        Toast.LENGTH_SHORT).show());
                //aqui se tiene que agregar la parte para envio a la siguente activity
        /**
         * se le asigna al reclicer el adaptador que se ha creado
         */
        binding.eqRecycler.setAdapter(adapter);
        /**
         * Se agrega un observador la lista de terremotos, al realizar cualquier cambio en la lista
         * el observador volvera a pintar la lista para mostrar los cambios
         */
        viewModel.getEqList().observe(this, eqList -> {
            adapter.submitList(eqList);
            if(eqList.isEmpty()){
                binding.emptyView.setVisibility(View.VISIBLE);
            }
            else
            {
                binding.emptyView.setVisibility(View.GONE);
            }
        });

        viewModel.getEarthquakes();

        //EqApiClient.getInstance().getService().getEarthquakes();

    }
}