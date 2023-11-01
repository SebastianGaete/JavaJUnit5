package com.sebastian.modelos;

import com.sebastian.modelos.exception.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    // Con este método addCuenta creamos la relación inversa de ambas clases.
    public Cuenta addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
        cuenta.setBanco(this);
        return cuenta;
    }

    public void transferencia(Cuenta origen, Cuenta destino, BigDecimal monto) throws DineroInsuficienteException {
        // usamos el método trasnferenciaDebito para trasnferir dinero desde la cuenta Origen.
        origen.transferenciaDebito(monto);
        // guardamos el saldoActual de la cuenta de destino y ademas guardamos el monto recibido.
        BigDecimal saldoActual = destino.getSaldo();
        BigDecimal saldoRecibido = monto;
        // Con la utilizacion del método setSaldo de la cuenta de destino sumanos ambos saldos.
        destino.setSaldo( BigDecimal.valueOf(saldoActual.doubleValue() + saldoRecibido.doubleValue()) );
    }


}
