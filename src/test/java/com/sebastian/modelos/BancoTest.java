package com.sebastian.modelos;

import com.sebastian.modelos.exception.DineroInsuficienteException;
import org.junit.jupiter.api.*;

import javax.xml.namespace.QName;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    Persona persona;
    Persona persona2;

    @BeforeEach
    void initMethodTest(){
        persona = new Persona("Sebastian", "gaete", 23);
        persona2 = new Persona("Moises", "gaete",53);
    }

    @Nested
    @DisplayName("Comprobación método de clase")
    class BancoMethodstest{

        @Test
        @DisplayName("Transferencia")
        void testMetodoTransferencia() throws DineroInsuficienteException {
            Banco b = new Banco("Banco de Chile");
            Cuenta origen = new Cuenta(persona, new BigDecimal(30.000));
            Cuenta destino = new Cuenta(persona2, new BigDecimal(10.000));
            b.transferencia( origen, destino, new BigDecimal(20.000) );
            // Tambien podemos dar mensajes personalizados en el assert que quisieramos, lo ideal es hacerlo con
            // una expresion lambda, para con consumir recursos e instancias del String.
            assertNotNull(b.getNombre(), ()-> "El atributo \"Nombre\" de la clase Banco no puede ser nulo");
            assertNotNull(origen.getSaldo(), () -> "El atributo \"saldo\" no puede ser nulo");
            assertNotNull(destino.getSaldo(), () -> "El atributo \"saldo\" no puede ser nulo");

            assertEquals(10.0, origen.getSaldo().doubleValue());
            assertEquals(30.0, destino.getSaldo().doubleValue());
        }
    }

    @Nested
    @DisplayName("Comprobación de relaciones de clases")
    class BancoRelacionesCuenta{
        @Test
        @DisplayName("Comprueba relacion de Banco-Cuenta y Cuenta-Banco")
        void testRelacionBancoCuenta(){
            Banco b = new Banco("Banco de Chile");
            Cuenta cuenta1 = new Cuenta(persona, new BigDecimal(30.000));
            Cuenta cuenta2 = new Cuenta(persona2, new BigDecimal(10.000));

            b.addCuenta(cuenta1);
            b.addCuenta(cuenta2);
            // vemos si el getCuenta de cuenta 1 es instancia de Banco.
            assertTrue( cuenta1.getBanco() instanceof Banco );
            // Comprobamos que el nombre del bando de la cuenta1 sea el esperado.
            assertEquals( "Banco de Chile", cuenta1.getBanco().getNombre() );
            // Comprobamos que la cantidad de cuentas que tenga el banco sea el esperado.
            assertEquals( 2, b.getCuentas().size() );
            // Comprobamos que la instancia de banco tenga una cuenta a de "sebastian gaete" con stream y filter.
            assertEquals("sebastian gaete", b.getCuentas().stream()
                    .filter( n -> n.getPersona().equals("sebastian gaete"))
                    .findFirst()
                    .get().getPersona());
            // Otra forma de comprobar si se encuentra una cuenta, con Stream y método anyMatch.
            assertTrue( b.getCuentas().stream()
                    .anyMatch( a -> a.getPersona().equals("moises gaete") ));

        }

        /*
         El método assertAll permite agrupar un conjunto de assertions, por medio de expresiones lambdas,
         es muy util usar este tipo de agrupaciones en nuestros test, ya que al utilizar este método,
         podemos saber cual o cuales assertions han fallado. cosa que al utilizar los assert no agrupados
         solo muestra el primero que falla.
        */

        @Test
        @DisplayName("Compueba relación de clases con método assertAll (conjunto de asserts)")
        void testRelacionBancoCuentaAssertAll(){
            Banco b = new Banco("Banco de Chile");
            Cuenta cuenta1 = new Cuenta(persona, new BigDecimal(30.000));
            Cuenta cuenta2 = new Cuenta(persona2, new BigDecimal(10.000));
            b.addCuenta(cuenta1);
            b.addCuenta(cuenta2);

            // usamos la agrupación de los asserts con expresiones lambda, sin argumentos, si fallan varios los va a mostrar.
            assertAll(
                    ()-> {assertTrue( cuenta1.getBanco() instanceof Banco );},
                    ()-> {assertEquals( "Banco de Chile", cuenta1.getBanco().getNombre() );},
                    ()-> {assertEquals( 2, b.getCuentas().size() );},

                    ()-> {assertEquals("sebastian gaete", b.getCuentas().stream()
                            .filter( n -> n.getPersona().equals("sebastian gaete"))
                            .findFirst()
                            .get().getPersona());},

                    ()-> {assertTrue( b.getCuentas().stream()
                            .anyMatch( a -> a.getPersona().equals("moises gaete") )); }
            );

        }
    }

}