package com.example.avaliacao1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private EditText editJoaoNinguem;
    private RadioButton radioButtonF, radioButtonM;
    private Button botaofechar, botaosalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar
        botaofechar = findViewById(R.id.buttonleave);
        botaosalvar = findViewById(R.id.buttonsave);
        editJoaoNinguem = findViewById(R.id.editJoaoNinguem);
        radioButtonF = findViewById(R.id.radioButtonF);
        radioButtonM = findViewById(R.id.radioButtonM);

        //"Listeners"
        botaofechar.setOnClickListener(this);
        botaosalvar.setOnClickListener(this);

        //Shared Preferences
        sharedPrefs = getSharedPreferences("myprefs",MODE_PRIVATE);
        sharedPrefsEditor = sharedPrefs.edit();

        loadSavedData();


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (v.getId() == R.id.buttonsave){
            saveData();
            Toast.makeText(this, "Dados Salvos", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.buttonleave) {
            finish();
        }
    }
    private void saveData(){
        //Salva o nome ao clicar salvar
        String name = editJoaoNinguem.getText().toString();
        sharedPrefsEditor.putString("name",name);

        //Salva o gênero ao clicar salvar
        String gender = radioButtonF.isChecked() ? "Female": "Male";
        sharedPrefsEditor.putString("gender",gender);

        //Commit
        sharedPrefsEditor.apply();
    }
    private void loadSavedData(){
        //Pega o nome salvo
        String savedName = sharedPrefs.getString("name","João Ninguém");
        editJoaoNinguem.setText(savedName);

        //Pega o gênero salvo
        String savedGender = sharedPrefs.getString("gender","Male");
        if (savedGender.equals("Female")){
            radioButtonF.setChecked(true);
        }else {
            radioButtonM.setChecked(true);
        }

    }
}