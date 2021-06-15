package br.com.uniritter.tailine.models;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import br.com.uniritter.tailine.Perfil;
import br.com.uniritter.tailine.R;
import br.com.uniritter.tailine.TelaPrincipal;
import br.com.uniritter.tailine.services.FirebaseServices;

public class Usuario {

    private FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();
    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();

    private Context aContext;

    private String nome;
    private String email = user.getEmail();
    private int tipoUsuario;

    public Usuario(){ super(); }

    public Usuario(String nome, String email, FirebaseUser user) {
        this.nome = nome;
        this.email = email;
        this.user = user;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String membro) {
        DocumentReference usuario = db.collection("usuarios").document(user.getUid());
        usuario.update("nome", membro);
    }

    public int getTipoUsuario() { return tipoUsuario; } // requer acesso à collection

    public void setTipoUsuario(int admin) {
        this.tipoUsuario = admin;
    }

    public void resetaSenha(FirebaseUser user){
        String emailAddress = user.getEmail();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Usuario", "Email enviado.");
                        }
                    }
                });
    }





}