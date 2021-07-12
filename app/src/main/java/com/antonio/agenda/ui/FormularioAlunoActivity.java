package com.antonio.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.antonio.agenda.R;
import com.antonio.agenda.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome, campoTelefone, campoEmail;
    final AlunoDAO dao = new AlunoDAO();
    private Button botaoSalvar;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializaComponentes();
        ConfiguraBotaoSalvar();

        Intent dados = getIntent();
        if(dados.hasExtra("aluno")){
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
            setTitle("Editar Aluno");
        } else{
            aluno= new Aluno();
        }
    }

    private void ConfiguraBotaoSalvar() {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preencheAluno();
                if(aluno.temIdValido()){
                    dao.edita(aluno);
                }else{
                    dao.salvar(aluno);
                }

                finish();
            }
        });
    }

    private void message(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();

    }

    private void inicializaComponentes() {
        campoNome = findViewById(R.id.activiity_formulario_editName);
        campoTelefone = findViewById(R.id.activity_formulario_editPhone);
        campoEmail = findViewById(R.id.activity_formulario_editEmail);
        botaoSalvar = findViewById(R.id.activity_formulario_ButtonSalvar);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.getNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

    }
}