package br.com.greenmile.ponto_api.domain;

public class HoraTrabalhada {

    private Integer horas;

    private Integer minutos;

    public HoraTrabalhada() {
        this.horas = 0;
        this.minutos = 0;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }
}
