package br.senai.sp.cotia.jogodavelhaapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import br.senai.sp.cotia.jogodavelhaapp.R;
import br.senai.sp.cotia.jogodavelhaapp.databinding.FragmentJogoBinding;


public class FragmentJogo extends Fragment {
    //variável para acessar os elementos na View
    private FragmentJogoBinding binding;

    //vetor para agrupar os botões
    private Button[] botoes;
    //variável que representa o tabuleiro
    private String[][] tabuleiro;
    //variável para os símbolos
    private String simbJog1, simbJog2, simbolo;
    //variável Random para sortear quem começa.... gera valor buleano
    private Random random;
    //variável para contar o número de jogadas
    private int numJogadas = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia o binding
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //instancia o vetor
        botoes = new Button[9];
        //agrupa os botões no vetor
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        //associa o listener aos botões
        for (Button bt : botoes){
            bt.setOnClickListener(listenerBotoes);
        }

        //inicializa o tabuleiro
        tabuleiro = new String[3][3];

        //instancia o Random
        random = new Random();

        //preencher o tabuleiro com ""
        for (String[] vetor: tabuleiro){
            Arrays.fill(vetor, "");
        }


        //define os simbolos dos jogadores
        simbJog1 = "X";
        simbJog2 = "O";

        //sorteia quem inicia o jogo
        sorteia();

        //atualiza a vez
        atualizaVez();

        //retorna a View do Fragment
        return binding.getRoot();
    }

    private void sorteia(){
        //caso random gere um valor V, jogador 1 começa
        //caso contrário, o jogador 2 começa
        if (random.nextBoolean()){
            simbolo = simbJog1;
        }else{
            simbolo = simbJog2;
        }
    }

    private void atualizaVez(){
        //verifia de quem é a vez e "acende" o placar do jogador em questão
        if (simbolo.equals(simbJog1)){
            binding.linearJog1.setBackgroundColor(getResources().getColor(R.color.white));
            binding.linearJog2.setBackgroundColor(getResources().getColor(R.color.green_A100));
        }else{
            binding.linearJog2.setBackgroundColor(getResources().getColor(R.color.white));
            binding.linearJog1.setBackgroundColor(getResources().getColor(R.color.green_A100));
        }
    }
    private void resetar(){
        // zerar o tabuleiro
        for (String[] vetor: tabuleiro){
            Arrays.fill(vetor, "");
        }
        //percorre o vetor de botões resetando-os
        for (Button botao : botoes){
            botao.setBackgroundColor(getResources().getColor(R.color.greens_900));
            botao.setText("");
            botao.setClickable(true);
        }
        //sorteia quem irá iniciar o próximo jogo
        sorteia();
        //atualiza a vez no placar
        atualizaVez();
        //zerar o número dde jogadas
        numJogadas = 0;
    }

    private boolean venceu(){
        //verifica se venceu nas linhas
        for (int i = 0; i < 3; i++){
            if (tabuleiro[i][0].equals(simbolo) && tabuleiro[i][1].equals(simbolo) && tabuleiro[i][2].equals(simbolo)){
                return true;
            }
        }

       /*
        for (int l = 0; l < 3;l++){
            boolean v = true;
            for (int c = 0; c < tabuleiro[l].length; c++){
                if(!tabuleiro[l][c].equals(simbolo)){
                    v = false;
                    break;
                }
            }
            if (v){
                return true;
            }
        }
    */
        //verifica se venceu na coluna
        for (int i = 0; i < 3; i++){
            if (tabuleiro[0][i].equals(simbolo)
                    && tabuleiro[1][i].equals(simbolo)
                    && tabuleiro[2][i].equals(simbolo)){
                return true;
            }
        }

        //verifica se venceu nas diagonais
        if (tabuleiro[0][0].equals(simbolo)
                && tabuleiro[1][1].equals(simbolo)
                && tabuleiro[2][2].equals(simbolo)){
            return true;
        }

        if (tabuleiro[0][2].equals(simbolo)
                && tabuleiro[1][1].equals(simbolo)
                && tabuleiro[2][0].equals(simbolo)){
            return true;
        }
        return false;
         /* FAZ A MESMA COISA QUE ACIMA
        for (int i = 0; i < 3; i++){
            for (int j = 0;j < 3; j++){
                tabuleiro[i][j] = "";
            }
        }
        */
    }


    private View.OnClickListener listenerBotoes = btPress -> {
        //incrementa as jogadas
        numJogadas++;
       //pega o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());
        //extrai os 2 últimos caracteres do nomeBotao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai a posição me linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));
        //marca no tabuleiro o símbolo que foi jogado
        tabuleiro[linha][coluna] = simbolo;
        // faz um casting de View pra Button
        Button botao = (Button) btPress;
        //trocar o texto do botão que foi clicado
        botao.setText(simbolo);
        //desabilitar o botão
        botao.setClickable(false);
        //troca o background do botão
        botao.setBackgroundColor(Color.rgb(153, 204, 255));

        //verifica se venceu
        if (numJogadas >= 5 && venceu()){
            //exibe um toast informando que o jogador venceu
            Toast.makeText(getContext(), R.string.venceu, Toast.LENGTH_LONG).show();
            //resetar o tabuleiro
            resetar();
        }else if (numJogadas == 9) {
            //exibe um Toast informando que o jogador venceu
            Toast.makeText(getContext(), R.string.deuvelha, Toast.LENGTH_LONG).show();
            //resetar o tabuleiro
            resetar();
        }else{
            //inverter a vez
            simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;

            //atualiza a vez
            atualizaVez();
        }
    };
}