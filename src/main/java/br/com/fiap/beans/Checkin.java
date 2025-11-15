package br.com.fiap.beans;
import java.time.LocalDate;

public class Checkin {
    private int id_checkin;
    private LocalDate dt_checkin;
    private int nr_humor;
    private int nr_estresse;
    private Funcionario funcionario;

    public Checkin() {
    }

    public Checkin(int id_checkin, LocalDate dt_checkin, int nr_humor, int nr_estresse, Funcionario funcionario) {
        this.id_checkin = id_checkin;
        this.dt_checkin = dt_checkin;
        this.nr_humor = nr_humor;
        this.nr_estresse = nr_estresse;
        this.funcionario = funcionario;
    }

    public int getId_checkin() {
        return id_checkin;
    }

    public void setId_checkin(int id_checkin) {
        this.id_checkin = id_checkin;
    }

    public LocalDate getDt_checkin() {
        return dt_checkin;
    }

    public void setDt_checkin(LocalDate dt_checkin) {
        this.dt_checkin = dt_checkin;
    }

    public int getNr_humor() {
        return nr_humor;
    }

    public void setNr_humor(int nr_humor) {
        this.nr_humor = nr_humor;
    }

    public int getNr_estresse() {
        return nr_estresse;
    }

    public void setNr_estresse(int nr_estresse) {
        this.nr_estresse = nr_estresse;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
