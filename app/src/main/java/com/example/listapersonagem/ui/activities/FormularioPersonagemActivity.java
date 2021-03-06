package com.example.listapersonagem.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.listapersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {
    //mostra na hud que o personagem está sendo editado
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    //mostra na hud que um novo personagem está sendo criado
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Persongem";
    //adiciona o nome
    private EditText campoNome;
    //adiciona a altura
    private EditText campoAltura;
    //adiciona o nascimento
    private EditText campoNascimento;
    //campo para salvar o numero de telefone
    private EditText campoTelefone;
    //campo para salvar o RG
    private EditText campoRG;
    //campo para salvar o CEP
    private EditText campoCEP;
    //Campo para salvar o CEP
    private EditText campoGenero;
    //salva as informações inseridas pelo usuario
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    //faz aparecer o botão de salvar no menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();
        if(itemid == R.id.activity_formulario_personagem_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //Adiciona o título
        inicilizacaoCampos();
        //deixa o botão salvar funcional
        configuraBotaoSalvar();
        //ao clicar no personagem, mostra as informações inseridas pelo usuario
        carregaPersonagem();

    }

    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            //altera a informação da hud (editando personagem)
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        } else {
            //altera a informação da hud (criando personagem)
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        //preenche os campos Nome, Altura, Nascimento, telefone, RG, CEP e genero
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
        campoTelefone.setText(personagem.getTelefone());
        campoRG.setText(personagem.getRG());
        campoCEP.setText(personagem.getCEP());
        campoGenero.setText(personagem.getGenero());
    }

    private void configuraBotaoSalvar() {
        //adicionando ações no botão ao clicar nele
        Button botaoSalvar = findViewById(R.id.button_salvar);
        //Botão salvar as informações e alterações
        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Salvando as informações*/
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }
    //metodo para finalizar o formulario
    private void finalizaFormulario() {
        preenchePersonagem();
        if (personagem.idvalido()) {
            dao.edita(personagem);
        } else {
            //aplicando metodo salvar
            dao.salva(personagem);
        }
        finish();
    }

    private void inicilizacaoCampos() {
        //identificação dos campos criados (edittexts)
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
        campoTelefone = findViewById(R.id.editTextPhone);
        campoRG = findViewById(R.id.editTextRG);
        campoCEP = findViewById(R.id.editTextCEP);
        campoGenero = findViewById(R.id.editTextgenero);


        //mascara para altura
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter ("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);

        //mascara para data
        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter ("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
        campoNascimento.addTextChangedListener(mtwNascimento);
        //mascara para telefone
        SimpleMaskFormatter smfTelefone = new SimpleMaskFormatter ("(NN)NNNNN-NNNN)");
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(campoTelefone, smfTelefone);
        campoTelefone.addTextChangedListener(mtwTelefone);
        //mascara para RG
        SimpleMaskFormatter smfRG = new SimpleMaskFormatter ("NNN.NNN.NNN-NN");
        MaskTextWatcher mtwRG = new MaskTextWatcher(campoRG, smfRG);
        campoRG.addTextChangedListener(mtwRG);
        //mascara para CEP
        SimpleMaskFormatter smfCEP = new SimpleMaskFormatter ("NNNNN-NNN");
        MaskTextWatcher mtwCEP = new MaskTextWatcher(campoCEP, smfCEP);
        campoCEP.addTextChangedListener(mtwCEP);
    }

    private void preenchePersonagem() {
        //guarda as informações inseridas pelo usuario e os armazena em personagens
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String RG = campoRG.getText().toString();
        String CEP = campoCEP.getText().toString();
        String genero = campoGenero.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setTelefone(telefone);
        personagem.setRG(RG);
        personagem.setCEP(CEP);
        personagem.setgenero(genero);
    }
}
