package com.sebastian.modelos.geometricos;

import com.sebastian.modelos.exception.LadosTrianguloException;
import org.junit.jupiter.api.*;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
 Una de las características de los Test en JUnit5 es que podemos acceder a los ciclos de vida de los test.
 En nuestras clases Test podemos crear un método con la anotación @BeforeEach el cual indicará que todos
 los otros métodos de test van a utilizar una instancia u objeto en común, esto para la reutilización de código
 y además existe la anotación @AfterEach, el cual es un método que se va a ejecutar después de cada test.

 Dentro de los BeforeEach y AfterEach podemos inyectar información acerca de los métodos de Test gracias a las
 interfaces TestInfo y TestReporter, la primera sirve para mostrar información hacerca del método de clase y la
 segunda es para dar un tipo de formato a la información, en vez de usar System.out.println().

 Otros dos métodos del ciclo de vida de los Test son los @BeforeAll y los @AfterAll los cuales SON MÉTODOS DE
 CLASE por lo tanto deben ser STATIC, de lo contrario los test no se van a ejecutar. Estas anotaciones permiten
 que el método se inicialize cuando se inicia el test y cuando ya finalizan todos los Test.
*/

class TrianguloTest {
    Triangulo triangulo; // Creamos una variable de clase para poder acceder a ella desde los métodos test.


    // Implementación de anotación @BeforeEach "se ejecuta antes de cada método test"
    @BeforeEach
    void initMethodTest(TestInfo testInfo, TestReporter testReporter){
        triangulo = new Triangulo(new ArrayList<>(), 10.3, 4.3);

        triangulo.addLadoTriangulo(3.2);
        triangulo.addLadoTriangulo(2.3);
        triangulo.addLadoTriangulo(9.3);
        testReporter.publishEntry("Ejecutando: " + testInfo.getDisplayName() );
    }

    // Implementación de anotación @AfterEach "se ejecuta despues de cada método test"
    @AfterEach
    void tearDown(TestInfo testInfo, TestReporter testReporter){
        testReporter.publishEntry("Finalizando: " + testInfo.getDisplayName() + " Aprobado!".concat("\n") );
    }

    // Implementación de anotación @BeforeAll "Antes de todos" es un método estático de clase.
    @BeforeAll
    static void beforeAll(){
        System.out.println("Iniciando la clase TrianguloTest");
    }

    // Implemenatción de anotación @AfterAll "Después de todos" es un método de clase.
    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando la clase TrianguloTest");
    }



    @Nested
    @DisplayName("Comprobando métodos de clase")
    class TrianguloMehodTest{
        @Test
        @DisplayName("Area")
        void testMetodoCalcularArea(){
            double area = triangulo.calculoArea();
            assertEquals(88.58, area);
            assertEquals(3, triangulo.getLados().size());
        }

        @Test
        @DisplayName("Perímetro")
        void testMetodoCalcularPerimetro() {
            double perimetro = triangulo.calculoPerimetroOCircunferencia();
            assertEquals(14.8, perimetro);
        }
    }

    @Nested
    @DisplayName("Comprobando Exceptions ")
    class TrianguloExceptionsTest{
        @Test
        @DisplayName("Exception LadoTrianguloException")
        void testMetodoCalcularPerimetroException() {
            Triangulo triangulo2 = new Triangulo(new ArrayList<>(), 10.3, 4.3);
            triangulo2.addLadoTriangulo(3.2);
            triangulo2.addLadoTriangulo(3.2);
            triangulo2.addLadoTriangulo(3.2);
            triangulo2.addLadoTriangulo(3.2);

            assertThrows(LadosTrianguloException.class, ()-> triangulo2.calculoPerimetroOCircunferencia());
        }
    }
}