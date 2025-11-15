package br.com.fiap.beans;

public class Funcionario {
    private int id_funcionario;
    private String nm_funcionario;
    private String email;
    private String senha;
    private String cargo;
    private Empresa empresa;

    public Funcionario() {
    }

    public Funcionario(int id_funcionario, String nm_funcionario, String email, String senha, String cargo, Empresa empresa) {
        this.id_funcionario = id_funcionario;
        this.nm_funcionario = nm_funcionario;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.empresa = empresa;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNm_funcionario() {
        return nm_funcionario;
    }

    public void setNm_funcionario(String nm_funcionario) {
        this.nm_funcionario = nm_funcionario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
