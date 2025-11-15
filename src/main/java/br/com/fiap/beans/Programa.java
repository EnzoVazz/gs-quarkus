package br.com.fiap.beans;
import java.time.LocalDate;

public class Programa {
    private int id_programa;
    private String nm_programa;
    private LocalDate dt_inicio_programa;
    private LocalDate dt_fim_programa;
    private String descricao_programa;
    private Empresa empresa;

    public Programa() {
    }

    public Programa(int id_programa, String nm_programa, LocalDate dt_inicio_programa, LocalDate dt_fim_programa, String descricao_programa, Empresa empresa) {
        this.id_programa = id_programa;
        this.nm_programa = nm_programa;
        this.dt_inicio_programa = dt_inicio_programa;
        this.dt_fim_programa = dt_fim_programa;
        this.descricao_programa = descricao_programa;
        this.empresa = empresa;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public String getNm_programa() {
        return nm_programa;
    }

    public void setNm_programa(String nm_programa) {
        this.nm_programa = nm_programa;
    }

    public LocalDate getDt_inicio_programa() {
        return dt_inicio_programa;
    }

    public void setDt_inicio_programa(LocalDate dt_inicio_programa) {
        this.dt_inicio_programa = dt_inicio_programa;
    }

    public LocalDate getDt_fim_programa() {
        return dt_fim_programa;
    }

    public void setDt_fim_programa(LocalDate dt_fim_programa) {
        this.dt_fim_programa = dt_fim_programa;
    }

    public String getDescricao_programa() {
        return descricao_programa;
    }

    public void setDescricao_programa(String descricao_programa) {
        this.descricao_programa = descricao_programa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
