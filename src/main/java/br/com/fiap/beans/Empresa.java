package br.com.fiap.beans;

public class Empresa {
    private int id_empresa;
    private String nm_empresa;
    private String nr_cnpj;
    private String setor;

    public Empresa() {
    }

    public Empresa(int id_empresa, String nm_empresa, String nr_cnpj, String setor) {
        this.id_empresa = id_empresa;
        this.nm_empresa = nm_empresa;
        this.nr_cnpj = nr_cnpj;
        this.setor = setor;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNm_empresa() {
        return nm_empresa;
    }

    public void setNm_empresa(String nm_empresa) {
        this.nm_empresa = nm_empresa;
    }

    public String getNr_cnpj() {
        return nr_cnpj;
    }

    public void setNr_cnpj(String nr_cnpj) {
        this.nr_cnpj = nr_cnpj;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}

