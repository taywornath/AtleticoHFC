package br.com.uniritter.tailine.models;

import com.google.firebase.firestore.DocumentId;

public class Evento {
    @DocumentId
    private String id;

    private String user;
    private String mensagem;
    private String nome;
    private String local;
    private String horario;
    private String data;
    private boolean lido;
    private Usuario objUser;

    public Evento() {
        super();
    }

    public Evento(String user, String nome, String mensagem, String local, String horario, String data) {
        this.user = user;
        this.nome = nome;
        this.mensagem = mensagem;
        this.local = local;
        this.horario = horario;
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) { this.user= user;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public Usuario getObjUser() {
        return objUser;
    }

    public void setObjUser(Usuario objUser) {
        this.objUser = objUser;
    }

    public String toString() {
        return "Nome: "+this.nome+"\n"+
                "Horario: "+this.horario+"\n+" +
                "Data: "+this.data+"\n"+
                "Local: "+this.local+"\n"+

                this.mensagem+"\n"+"\n";
    }


}
