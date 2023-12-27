package com.example.testeprojeto;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvMultas;
    private ArrayAdapter adapter;
    private List<Multas> listMultas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMultas = findViewById(R.id.listMultas);
        carregarMultas();
        Button btn = findViewById(R.id.btnApp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.testeprojeto.FormularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvMultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idMulta = listMultas.get(position).getId();
                System.out.println("id::: " + listMultas.get(position).getId() + ".........");
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("id", idMulta);
                startActivity(intent);
            }
        });

        lvMultas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Entrou clique longo");
                excluir(position);
                return true;
            }
        });
    }

    private void excluir(int posicao) {
        Multas multas = listMultas.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão da multa de " + multas.getNome() + " ?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MultasDAO.excluir(MainActivity.this, multas.getId());
                carregarMultas();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarMultas();
    }

    private void carregarMultas() {
        listMultas = MultasDAO.getMultas(this);
        if (listMultas.size() == 0) {
            Multas fake = new Multas("Lista Vazia...", "", "", ",");
            listMultas.add(fake);
            lvMultas.setEnabled(false);
        } else {
            lvMultas.setEnabled(true);
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listMultas);
        lvMultas.setAdapter(adapter);
    }
}
