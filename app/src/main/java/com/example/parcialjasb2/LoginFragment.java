package com.example.parcialjasb2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.parcialjasb2.R;

public class LoginFragment extends Fragment {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        // Instancia de SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", getActivity().MODE_PRIVATE);

        // Verificar si ya hay un usuario guardado en SharedPreferences
        String savedUser = sharedPreferences.getString("username", null);
        if (savedUser != null) {
            // Si hay un usuario guardado, ir directamente a la pantalla de inicio
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
        }

        // Botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Validar que ambos campos estén llenos
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor ingresa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar el usuario en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.apply();

                    // Navegar a la pantalla de inicio
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);

                }
            }
        });

        return view;
    }
}
