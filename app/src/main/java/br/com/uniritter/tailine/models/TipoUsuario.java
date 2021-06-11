package br.com.uniritter.tailine.models;

public class TipoUsuario {

    public int membro = 1;
    public int admin = 2;

    public int getMembro() {
        return membro;
    }

    public void setMembro(int membro) {
        this.membro = membro;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
