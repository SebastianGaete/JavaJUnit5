package com.sebastian.modelos.geometricos;

import java.util.ArrayList;
import java.util.List;

abstract public class FiguraGeometrica {
    protected List<Double> lados;

    public FiguraGeometrica(List<Double> lados) {
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
