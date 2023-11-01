package com.sebastian.modelos.geometricos;
import com.sebastian.modelos.exception.LadosTrianguloException;

import java.util.List;
import java.util.stream.Collectors;


public class Triangulo extends FiguraGeometrica implements CalculosGeometricos {
    private Double base;
    private Double altura;

    public Triangulo(List<Double> lados, Double base, Double altura) {
        super(lados);
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
    public double calculoPerimetroOCircunferencia(){
        double suma = 0;

        if( lados.size() != 3 ){
            throw new LadosTrianguloException("Los triangulos solo tienen 3 lados");
        }
        // guardamos dentro de la variable "suma" la suma del método mapToDouble()
        suma = getLados().stream().mapToDouble( Double::doubleValue ).sum();

        return suma;
    }


}
