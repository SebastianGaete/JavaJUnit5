package com.sebastian.modelos.geometricos;

import com.sebastian.modelos.exception.IgualdadLadosCuadradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CuadradoTest {

    Cuadrado cuadrado;

    @BeforeEach
    void initMethodTest(){
        cuadrado = new Cuadrado(new ArrayList<>());
        cuadrado.addLados(3.2);
        cuadrado.addLados(3.2);
        cuadrado.addLados(3.2);
        cuadrado.addLados(3.2);
    }

    @Nested
    @DisplayName("Comprobación métodos de clase cuadrado")
    class CuadradoMethodsTest{

        @Test
        @DisplayName("Area")
        void testMetodArea(){
            double areaActual = cuadrado.calculoArea();
            assertEquals(10.0, areaActual );
        }
    }

    @Nested
    @DisplayName("Comprobación exceptions")
    class CuadradoExceptionsTest{

        @Test
        @DisplayName("Igualdad Lados Cuadrado Exception")
        void testExceptionCuadrado(){
            Cuadrado cuadrado2 = new Cuadrado( new ArrayList<>());
            cuadrado2.addLados(1.2);
            cuadrado2.addLados(1.2);
            cuadrado2.addLados(2.2);
            assertThrows(IgualdadLadosCuadradoException.class, ()-> cuadrado2.calculoArea());
        }

    }

}