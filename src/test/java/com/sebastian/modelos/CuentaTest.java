package com.sebastian.modelos;

import com.sebastian.exceptions.DineroInsuficienteException;
import com.sebastian.modelsJUnit5.Cuenta;
import com.sebastian.modelsJUnit5.Persona;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

    /*
    Siempre que implementemos métodos de Test en nuestra carpeta Test debemos usar la anotación @Test para que funcione
    de lo contrario, si no lo utilizamos, no se podrán realizar los Test.
    Dentro de los métodos siempre debemos realizar "casos de pruebas" creando nuestras instancias, en donde podremos
    probar tipos de datos esperados y actuales, como tambien podemos probar los métodos de la misma clase que
    qusieramos utilizar.

    Los métodos de Test tienen a su disposición diferentes métodos de la clase Assertions
    los cuales sirven para comparar entre resultados esperados y actuales, como tambien expresiones booleanas,
    que algún atributo no sea nulo, comprobar excepciones que hayamos creado nosotros mismos, etc...
    */


class CuentaTest {

    //  Creamos atributos de clase para usar @BeforeEach
    Cuenta c;
    Persona p;

    @BeforeEach
    void initMethodTest(TestInfo testInfo, TestReporter testReporter){
        p = new Persona("Sebastian", "Gaete", 23);
        c = new Cuenta(p, new BigDecimal("100.000"));
        testReporter.publishEntry("Ejecutando: " + testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("Finalizando: " + testInfo.getDisplayName() + " Aprobado!");
        System.out.println();
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Inicializando la clase CuentaTest");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando la clase CuentaTest");
    }


    @Nested
    @Tag("atributos")
    @DisplayName("Comprobación atributos de clase")
    class CuentaAtributosTest{

        @Test
        @DisplayName("Atributo Persona not null")
        void testPersonaNotNull(){
            assertNotNull(c.getPersona());
        }

        @Test
        @DisplayName("Nombre, apellido y edad de Persona not null")
        void testNombreApellidoEdadPersonaNotNull() {
            assertNotNull(c.getPersona().getNombre());
            assertNotNull(c.getPersona().getApellido());
            assertNotNull(c.getPersona().getEdad());
        }

        @Test
        @Disabled //  La anotación @Disabled quiere decir que este test no lo va a tomar en cuenta.
        @DisplayName("Nombre en minúscula") // Anotación para dar un nombre al test.
        void testNombrePersonaMinuscula() {
            String expected = c.getPersona().getNombre().toLowerCase();
            String actual = c.getPersona().getNombre();
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Saldo que sea mayor que cero")
        void testSaldoMayorQueCero() {
            assertTrue(c.getSaldo().doubleValue() >= 0, "Es menor que 0");
        }

        @Test
        @DisplayName("Comparando instancias por atributos equals()")
        void testReferenciaDeCuenta() {
            Persona persona = new Persona("Jhon", "Doe", 50);
            Persona persona2 = new Persona("Jhon", "Doe", 50);
            Cuenta cuenta = new Cuenta(persona, new BigDecimal("120.121"));
            Cuenta cuenta2 = new Cuenta(persona2, new BigDecimal("120.121"));
            // Como sobreescribimos el método equals ahora estará comparando por atributos, no por referencia.
            assertEquals(cuenta2, cuenta);
        }
    }

    @Nested
    @Tag("metodosClass")
    @DisplayName("Comprobación de métodos de clase")
    class CuentaMetodosTest{

        @Test
        @DisplayName("Transferencia Debito")
        void testMetodoTranferenciaDebito() throws DineroInsuficienteException {
            c.transferenciaDebito(new BigDecimal(10.000)); // usamos el método y pasamos un BigDecimal
            assertNotNull(c.getSaldo()); // verificamos que el atributo no sea nulo
            assertEquals(90.0, c.getSaldo().doubleValue()); // comparamos lo que se espera con lo actual.
        }

        @Test
        @DisplayName("Transferencia Credito")
        void testMetodoTransferenciaCredito(){
            c.transferenciaCredito(new BigDecimal(50.000));
            assertNotNull(c.getSaldo());
            assertEquals(150.0, c.getSaldo().doubleValue());
        }
    }

    @Nested
    @Tag("exceptions")
    @DisplayName("Comprobación de exceptions")
    class CuentaExceptionsTest{

        @Test
        @DisplayName("Exception DineroInsuficienteException")
        void testDineroInsuficienteException(){
            Cuenta c = new Cuenta(p, new BigDecimal(50.000));
            assertNotNull(c.getSaldo());
            // Para probarlo usamos el método assertThrows al cual pasamos la clase creada y una expresion lambda.
            Exception excepcion = assertThrows(DineroInsuficienteException.class, ()-> {
                c.transferenciaDebito(new BigDecimal(60.000));
            });
            // y tambien vamos a comparar el mensaje obtenido de la excepción.
            String actual = excepcion.getMessage();
            String expected = "No posees dinero suficiente";
            assertEquals(expected, actual);
        }

    }
}