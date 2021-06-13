package br.com.uniritter.tailine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import br.com.uniritter.tailine.models.TipoUsuario;
import br.com.uniritter.tailine.models.Usuario;
import br.com.uniritter.tailine.services.FirebaseServices;

public class Perfil extends AppCompatActivity {

    private Button alterarEmail, alterarSenha;
    private TextView txtEmail, txtNome;

    private FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Usuario usuario = new Usuario(user.getDisplayName(), user.getEmail(), user);

        alterarEmail = findViewById(R.id.btn_alterarEmail);
        alterarSenha = findViewById(R.id.btn_alterarSenha);
        txtEmail = findViewById(R.id.txt_emailPerfil);
        txtNome = findViewById(R.id.txt_nomePerfil);

        txtEmail.setText(usuario.getEmail());
        txtNome.setText(usuario.getNome());


    }


}