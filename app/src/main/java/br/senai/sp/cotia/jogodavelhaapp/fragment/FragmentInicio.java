package br.senai.sp.cotia.jogodavelhaapp.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.jogodavelhaapp.R;
import br.senai.sp.cotia.jogodavelhaapp.databinding.FragmentInicioBinding;


public class FragmentInicio extends Fragment {
    private FragmentInicioBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        //ação do botão que leva ao fragment do jogo
        binding.iniciar.setOnClickListener(v ->{
            NavHostFragment.findNavController(FragmentInicio.this).navigate(R.id.action_fragmentInicio_to_fragmentJogo);
        });
        return binding.getRoot();
    }

    @Override
    public void onStart(){
        super.onStart();
        // para "sumir" com a toolbar
        //pegar uma referência do tipo AppCompactActivity
        AppCompatActivity minhaActivity = (AppCompatActivity) getActivity();
        //oculta a toolbar
        minhaActivity.getSupportActionBar().hide();
    }
}