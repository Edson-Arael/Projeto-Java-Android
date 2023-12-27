package com.example.testeprojeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private TextView id;
    private EditText etPlaca;
    private EditText etValor;
    private Spinner spGravidade;
    private Button btnSalvar;
    private String acao;
    private Multas multas;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario); // MUDAR ?
        multas = new Multas();

        etNome = findViewById(R.id.editNome);
        etPlaca = findViewById(R.id.editPlaca);
        etValor = findViewById(R.id.editValor);
        spGravidade = findViewById(R.id.editGravidade);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");

        if (acao.equals("editar")) {
            //       MultasDAO.editar(this, multas);
            carregarFormulario();
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    public void carregarFormulario() {
        int id = getIntent().getIntExtra("id", 0); // <-- Corrigir para "idMultas"

        // Verificar se o ID é válido antes de carregar os dados
        if (id > 0) {
            multas = MultasDAO.getMultasbyId(this, id);

            if (multas != null) {
                etNome.setText(multas.getNome());
                String[] gravidade = getResources().getStringArray(R.array.strGravidade);
                for (int i = 0; i < gravidade.length; i++) {
                    if (multas.getGravidade().equals(gravidade[i])) {
                        spGravidade.setSelection(i);
                        break;
                    }
                }
            } else {
                Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show();
        }
    }

    public void salvar() {
        if( multas == null ) {
            multas = new Multas();
        }

        String nome = etNome.getText().toString();
        String placa = etPlaca.getText().toString();
        String valor = etValor.getText().toString();
        if (nome.isEmpty() || spGravidade.getSelectedItemPosition() == 0 || placa.isEmpty() || valor.isEmpty()) {
            Toast.makeText(this, "Você deve preencher ambos os campos!!", Toast.LENGTH_LONG).show();
        } else {
            multas.setNome(nome);
            multas.setPlaca(placa);
            multas.setValor(valor);
            multas.setGravidade(spGravidade.getSelectedItem().toString());
            if (acao.equals("inserir")) {
                MultasDAO.inserir(this, multas);
                etNome.setText("");
                etPlaca.setText("");
                etValor.setText("");
                spGravidade.setSelection(0, true);
            } else {
                MultasDAO.editar(this, multas);
                finish();
            }
        }
    }
}

