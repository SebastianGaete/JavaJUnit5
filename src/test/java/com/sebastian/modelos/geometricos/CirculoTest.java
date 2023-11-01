package com.sebastian.modelos.geometricos;

import com.sebastian.modelos.exception.LadosTrianguloException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


    /*
    Existen Anotaciones que permiten que nuestros métodos de Test se ejectuten cierto a algunos parámetros
    como establecer que un Test se ejecute solo en uno o varios sistemas operativos "Windows, Mac, Linux, etc"
    gracias a la anotación @EnabledOnOs()

    Como tambien otra anotación la cual establece que el Test se ejecute bajo una version de Java específica
    con la anotacion @EnabledOnJre.

    Tambien podemos categorizar nuestros Test dentro de una clase anidada o innerClass, con la utilizacion de la
    anotación @Nested, solamente para categorizar métodos de Test.

    Otro tipo de estructura para organizar nuestros Test es a través de la anotación @Tag("") en donde colocamos un
    Target a la InnerClass o método por si solo, solamente para categorizar aún más nuestro Test, ya que podemos ejecutar
    solamente los innerClass o métodos de Test que tengan un Tag en específico. Se configura en Edit Configurations.

    Otra anotación que implementa JUnit5 es @TimeOut el cual se utiliza generalmente para establecer una condición para
    que el Test termine dentro del plazo estimado, de lo contrario va a lanzar un error, y a aparte de esta anotación
    existe el assertTimeOut() el cual realiza lo mismo.
     */


class CirculoTest {
    Circulo circulo;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Iniciando el CirculoTest");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando el CirculoTest");
    }

    @BeforeEach
    void initMethodTest(TestInfo testInfo, TestReporter testReporter){
        circulo = new Circulo(4.3);
        testReporter.publishEntry("Ejecutando: " + testInfo.getDisplayName() + " " +
                testInfo.getTestMethod().get().getName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("Finalizando: " + testInfo.getDisplayName() + " Aprobado!".concat("\n") );
    }


    @Nested
    @Tag("metodosSO")
    @DisplayName("Comprobación métodos de clase Sistemas Operativos")
    class CirculoMethodsSistemaOperativo{
        @Test
        @EnabledOnOs(OS.WINDOWS) // Test solo para WINDOWS
        @DisplayName("Diametro WINDOWS")
        void testMethodDiametro(){
            double diametroActual = circulo.calculoDiametro();
            assertAll(
                    () -> assertNotEquals(0, circulo.getRadio()),
                    () -> assertEquals( 8.6, diametroActual )
            );
        }

        @Test
        @EnabledOnOs({OS.LINUX, OS.MAC}) // Test solo para LINUX Y MAC
        @DisplayName("Area LINUX y MAC")
        void testMethodArea(){
            circulo.setRadio(8.1);
            double areaActual = circulo.calculoArea();
            assertEquals(48.0, areaActual);
        }

        @Test
        @DisabledOnOs(OS.WINDOWS) // Test desabilitado para WINDOWS
        @DisplayName("Diametro desabilitado WINDOWS")
        void testMethodDiametro2(){
            circulo.setRadio(20.4);
            double diametroActual = circulo.calculoDiametro();
            assertEquals(40.8, diametroActual);
        }
    }


    @Nested
    @Tag("metodosJRE")
    @DisplayName("Comprobacion métodos de clase Version de Java")
    class CirculoMethodTestVersionJava{
        @Test
        @EnabledOnJre(JRE.JAVA_20) // Test solo para Versión 20 de Java
        @DisplayName("Circuferencia Java 20")
        void testMethodCircunferencia() {
            circulo.setRadio(22.1);
            double cirunferenciaActual = circulo.calculoPerimetroOCircunferencia();
            assertEquals(133.0, cirunferenciaActual );
        }

        @Test
        @EnabledOnJre(JRE.JAVA_8)
        @DisplayName("Area Java 8")
        void testMethodArea(){
            circulo.setRadio(8.1);
            double areaActual = circulo.calculoArea();
            assertEquals(48.0, areaActual);
        }


    }


    @Nested
    @Tag("metodos")
    @DisplayName("Comprobación métodos de clase")
    class circuloMethodsTest{
        @Test
        @DisplayName("Diametro")
        void testMethodDiametro(){
            double diametroActual = circulo.calculoDiametro();
            assertAll(
                    () -> assertNotEquals(0, circulo.getRadio()),
                    () -> assertEquals( 8.6, diametroActual )
            );
        }

        @Test
        @DisplayName("Area")
        void testMethodArea(){
            circulo.setRadio(8.1);
            double areaActual = circulo.calculoArea();
            assertEquals(48.0, areaActual);
        }

        @Test
        @DisplayName("Circunferencia")
        void testMethodCircunferencia() {
            circulo.setRadio(22.1);
            double cirunferenciaActual = circulo.calculoPerimetroOCircunferencia();
            assertEquals(133.0, cirunferenciaActual );
        }

    }

    // Implementación de @TimeOut y assertTimeOut.
    @Nested
    @Tag("TimeOut")
    @DisplayName("Comprobación métodos de clase con Time out")
    class circuloTestTimeOut{

        @Test
        @DisplayName("Utilizando anotacion @TimeOut")
        @Timeout(2)  // Anotación a la cual le pasamos el tiempo (segundos, milisegundo, minutos, horas, etc)
        void testTimeOutAnotacion() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }

        @Test
        @DisplayName("Utilizando assertTimeOut")
        void testAreaAssertTimeOut() throws InterruptedException {
            // assert para realizar lo mismo, pero espera como primer argumento un objeto de tipo Duration y despues
            // la tarea a realizar mediante una expresion lambda.
            assertTimeout(Duration.ofMinutes(10), ()->{
                circulo.calculoArea();
            });
        }



    }
}