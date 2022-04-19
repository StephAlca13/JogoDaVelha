package br.senai.sp.cotia.jogodavelhaapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtil {

    //salva as preferences via código sem a tela de preference
   /* public static void salvarSimboloJog1(String simbolo, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("simb_jog_1", simbolo);
        editor.commit();
    }*/

    //grava as preferencias
    public static String getSimboloJog1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simb_jog_1", "X");
    }

    public static String getSimboloJog2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simb_jog_2", "O");
    }
}
