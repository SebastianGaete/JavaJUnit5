package com.sebastian.modelos.geometricos.models;
import com.sebastian.modelos.exception.IgualdadLadosCuadradoException;
import com.sebastian.modelos.geometricos.abstracts.FiguraGeometricaConLados;
import com.sebastian.modelos.geometricos.repository.GeometricoBasicoArea;
import com.sebastian.modelos.geometricos.repository.GeometricoBasicoPerimetro;


public class Cuadrado extends FiguraGeometricaConLados implements GeometricoBasicoArea, GeometricoBasicoPerimetro {

    private  final String messageException = "Los cuadrados deben tener sus cuatro lados iguales.";

    private boolean ladosIgualesCuadrado(){
        if(lados.size() != 4){
            return false;
        }

        double primerLado = lados.get(0);
        for (int i = 1; i < lados.size(); i++){
            if( lados.get(i) != primerLado ){
                return false;
            }
        }

        return true;
    }

    @Override
    public double calculoArea() {
        double area = 0;
        if(ladosIgualesCuadrado() == false){
            throw new IgualdadLadosCuadradoException(messageException);
        }
        area = getLados().get(0) * getLados().get(0);
        return Math.round(area);
    }
    @Override
    public double calculoPerimetro() {
        double perimetro = 0;
        if(ladosIgualesCuadrado() == false){
            throw new IgualdadLadosCuadradoException(messageException);
        }

        perimetro = getLados().get(0) * 4;
        return Math.round(perimetro);
    }

    public double calculoDiagonal(){
        double diagonal = 0;
        if(ladosIgualesCuadrado() == false){
            throw new IgualdadLadosCuadradoException(messageException);
        }

        diagonal = getLados().get(1) * Math.sqrt(2);
        return Math.round(diagonal);
    }
}
