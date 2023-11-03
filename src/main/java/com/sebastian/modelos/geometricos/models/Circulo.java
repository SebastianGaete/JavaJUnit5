package com.sebastian.modelos.geometricos.models;
import com.sebastian.modelos.geometricos.repository.GeometricoBasicoArea;
import com.sebastian.modelos.geometricos.repository.GeometricoCircunferencia;

public class Circulo implements GeometricoCircunferencia, GeometricoBasicoArea {
    private final double Pi = Math.PI;
    private double radio;

    public Circulo(){
    }

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getPi() {
        return Math.round(this.Pi);
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }


    @Override
    public double calculoArea(){
        return Math.round( getPi() * getRadio() )*2;
    }

    @Override
    public double calculoCircunferencia() {
        return Math.round(2 * getPi() * getRadio());
    }

    public double calculoDiametro(){
        return getRadio()*2;
    }

}
