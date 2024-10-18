package com.example.parcialjasb2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.Navigation;

import com.example.parcialjasb2.HomeAdapter;
import com.example.parcialjasb2.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnLogout;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        btnLogout = view.findViewById(R.id.btnLogout);

        // Inicializar SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", getActivity().MODE_PRIVATE);

        // Configurar RecyclerView con un adaptador
        setupRecyclerView();

        // Configurar el botón de cerrar sesión
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar usuario de SharedPreferences y volver al LoginFragment
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.apply();

                // Navegar de regreso al LoginFragment
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_loginFragment);
            }
        });

        return view;
    }

    private void setupRecyclerView() {
        // Crear una lista de datos de ejemplo para el RecyclerView
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("Elemento 1");
        itemList.add("Elemento 2");
        itemList.add("Elemento 3");
        itemList.add("Elemento 4");

        // Configurar el RecyclerView y su adaptador
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HomeAdapter adapter = new HomeAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
