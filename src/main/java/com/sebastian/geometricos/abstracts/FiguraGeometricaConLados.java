package com.sebastian.geometricos.abstracts;

import java.util.ArrayList;
import java.util.List;

abstract public class FiguraGeometricaConLados {
    protected List<Double> lados;

    public FiguraGeometricaConLados() {
        this.lados = new ArrayList<>();
    }

    public List<Double> getLados() {
        return lados;
    }
    public void setLados(List<Double> lados) {
        this.lados = lados;
    }

    public void addLados(double lado){
        lados.add(lado);
    }

}
