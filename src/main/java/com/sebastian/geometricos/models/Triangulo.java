package com.sebastian.geometricos.models;
import com.sebastian.exceptions.LadosTrianguloException;
import com.sebastian.geometricos.abstracts.FiguraGeometricaConLados;
import com.sebastian.geometricos.repository.GeometricoBasicoArea;
import com.sebastian.geometricos.repository.GeometricoBasicoPerimetro;


public class Triangulo extends FiguraGeometricaConLados implements GeometricoBasicoArea, GeometricoBasicoPerimetro {
    private Double base;
    private Double altura;

    public Triangulo(Double base, Double altura) {
        this.base = base;
        this.altura = altura;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void addLadoTriangulo(Double lado){
        lados.add(lado);
    }


    @Override
    public double calculoArea(){
        return ( getBase() * getAltura() ) * 2;
    }

    @Override
    public double calculoPerimetro(){
        double suma = 0;

        if( lados.size() != 3 ){
            throw new LadosTrianguloException("Los triangulos solo tienen 3 lados");
        }
        // guardamos dentro de la variable "suma" la suma del método mapToDouble()
        suma = getLados().stream().mapToDouble( Double::doubleValue ).sum();

        return suma;
    }


}
