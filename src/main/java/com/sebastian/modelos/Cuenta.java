package com.sebastian.modelos;

import com.sebastian.modelos.exception.DineroInsuficienteException;

import java.math.BigDecimal;

public class Cuenta {

    private Persona persona;
    private BigDecimal saldo;
    private Banco banco;

    public Cuenta(Persona persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    // Sobreescribimos el método equals con la finalidad de comprar instancias de cuenta por nombre de Persona.
    @Override
    public boolean equals(Object obj) {
        // Casteo del Objeto genérico a un objeto de tipo Cuenta
        Cuenta c = (Cuenta)obj;
        // Verificamos si el objeto viene null, si es así retorna false.
        if (c == null){
            return false;
        }
        // verificamos que si alguno de los dos atributos son null, retorna false.
        if (c.getPersona() == null || c.getSaldo() == null){
            return false;
        }
        // devolvemos valor booleano si los atributos del objeto son iguales.
        return c.getPersona().getNombre().equals(this.persona.getNombre()) && c.getSaldo().equals(this.saldo);
    }


    public void transferenciaDebito(BigDecimal montoTransferido) throws DineroInsuficienteException{
        if( montoTransferido.doubleValue() > this.saldo.doubleValue() ){
            throw new DineroInsuficienteException("No posees dinero suficiente");
        }
        this.saldo = BigDecimal.valueOf( getSaldo().doubleValue() - montoTransferido.doubleValue() );
    }

    public void transferenciaCredito(BigDecimal montoTransferido){
        this.saldo = BigDecimal.valueOf( getSaldo().doubleValue() + montoTransferido.doubleValue() );
    }


}
