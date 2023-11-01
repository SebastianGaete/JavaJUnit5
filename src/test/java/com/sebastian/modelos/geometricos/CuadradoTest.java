package com.sebastian.modelos.geometricos;

import com.sebastian.modelos.exception.IgualdadLadosCuadradoException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

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

    /*
    Tambien podemos utilizar anotaciones similares al @Test, pero que proveen más utilidades o características al método
    Test

    @RepetedTest: Permite ejecutar el mismo método test conforme a las veces que sean necesarias.
    @ParametrizedTest: Permite pasar argumentos en forma de array, para que estos argumentos se inyecten en el método.

    */

    @Nested
    @DisplayName("Comprobación métodos de clase cuadrado")
    class CuadradoMethodsTest{

        // Este Test se va a ejecutar 3 veces.
        @RepeatedTest(value = 3, name = "Repetición {currentRepetition} de {totalRepetitions}")
        @DisplayName("Area")
        void testMetodArea(){
            double areaActual = cuadrado.calculoArea();
            assertEquals(10.0, areaActual );
        }

        // Este Test va a recibir valores por parámetro, a travez del Array de @ValueSource
        @ParameterizedTest
        @ValueSource(doubles = {10.3, 4.4, 10.2, 100.2} )
        void testLadosPorParametro(double valorLados){
            assertAll(
                    () -> assertTrue( cuadrado.getLados().get(0) != valorLados ),
                    () -> assertTrue( cuadrado.getLados().get(1) != valorLados ),
                    () -> assertTrue( cuadrado.getLados().get(2) != valorLados ),
                    () -> assertTrue( cuadrado.getLados().get(3) != valorLados )
            );
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