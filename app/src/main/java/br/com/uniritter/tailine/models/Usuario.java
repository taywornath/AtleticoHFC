package br.com.uniritter.tailine.models;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.uniritter.tailine.services.FirebaseServices;

public class Usuario {
    private String nome, email;
    private int tipoUsuario;

    private FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();
    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();

    public Usuario(){ super(); }

    public Usuario(String nome, String email, FirebaseUser user) {
        this.nome = nome;
        this.email = email;
        this.user = user;
    }

    public void defineDados(){
        email = user.getEmail();
        nome = user.getDisplayName();
    }

    public String getEmail(){
        return email;
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
        this.tipoUsuario = tipoUsuario;
    }

}
