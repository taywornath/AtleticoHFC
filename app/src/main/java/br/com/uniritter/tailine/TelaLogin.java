package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class TelaLogin extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private Button botaoLogin;

    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            gotoTelaPrincipal();
//        } else {
//            // No user is signed in
//        }

        mAuth = FirebaseAuth.getInstance();
        botaoLogin = findViewById(R.id.btnLogin);


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build()
                );

                // Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
            }
        });
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                Toast.makeText(this, "Login realizado com sucesso" + FirebaseServices.getFirebaseUser().getDisplayName(),
//                        Toast.LENGTH_LONG).show();
                gotoTelaPrincipal();

            } else {
                //Login falhou
                Toast.makeText(this, "Login falhou", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void gotoTelaPrincipal(){
        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

    private void reload() { }
}


