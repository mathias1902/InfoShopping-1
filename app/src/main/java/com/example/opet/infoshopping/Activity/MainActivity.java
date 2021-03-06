package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;
import com.example.opet.infoshopping.Repository.ClienteRepository;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    Spinner estadoSpinner;
    Spinner cidadeSpinner;
    public static Cliente clienteLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       /* Intent resultado = getIntent();
        long id = resultado.getLongExtra("ID_USUARIO",0);

        if (clienteLogado == null){
            clienteLogado = new ClienteRepository(this).carregaClientePorID(id);
        }*/


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        estadoSpinner = findViewById(R.id.spinnerEstado);
        cidadeSpinner = findViewById(R.id.spinnerCidade);

        cidadeSpinner.setEnabled(false);

        estadoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    String selecionada = estadoSpinner.getSelectedItem().toString();
                    String[] cidades = getResources().getStringArray(R.array.cidades_array);
                    switch (selecionada){
                        case "Paraná":
                            cidades  = getResources().getStringArray(R.array.cidades_array_parana);
                            break;
                        case "São Paulo":
                            cidades  = getResources().getStringArray(R.array.cidades_array_sao_paulo);
                            break;
                    }

                    ArrayAdapter yourarray = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item, cidades);
                    yourarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cidadeSpinner.setAdapter(yourarray);
                    cidadeSpinner.setEnabled(true);
                }
                else{
                    cidadeSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cidadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    String selecionada = cidadeSpinner.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this, selecionada, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }








    public void cadastrar(View view){

        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }


    public void logar(View view){

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void listar (View view ){
        Intent intent = new Intent(this, ListarClientesActivity.class);
        startActivity(intent);
    }

    public void pesquisar (View view){
        String selecionada = cidadeSpinner.getSelectedItem().toString();
        switch (selecionada){
            case "Curitiba":
                Intent intent = new Intent(this, ShoppingsActivity.class);
                startActivity(intent);
                break;
            case "São Paulo":
            Toast.makeText(MainActivity.this, "Cidade ainda não implementada", Toast.LENGTH_SHORT).show();
                break;
            case "Campinas":
                Toast.makeText(MainActivity.this, "Cidade ainda não implementada", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
