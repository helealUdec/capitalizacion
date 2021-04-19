package Modelo;

import Vista.DatosIngresados;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Modelo {

    private ArrayList<Object[]> listaDatos = new ArrayList<>();

    public void almacenarDatos(DatosIngresados datosIngresados) {
        String cedula = datosIngresados.jtextCedula.getText();
        String nombre = datosIngresados.jtextNombre.getText();
        String apellido = datosIngresados.jtextApellido.getText();
        String telefono = datosIngresados.jtextTelefono.getText();
        int horas = Integer.parseInt(datosIngresados.jtextHoras.getText());
        double capInicial = Double.parseDouble(datosIngresados.jtextValorHora.getText());
        double pago = calcularPago(horas, capInicial);
        String[] datos = {cedula, nombre, apellido, telefono, String.valueOf(horas), String.valueOf(capInicial), String.valueOf(pago)};
        listaDatos.add(datos);
        JOptionPane.showMessageDialog(null, "Datos guardados");
    }

    private double calcularPago(int horas, double capInicial) {
        double pago = 0, porcentaje;

        if (horas <= 40) {
            pago = horas * capInicial;
        } else if (horas <= 48) {
            pago = horas * capInicial;
            porcentaje = pago * 0.2;
            pago = pago + porcentaje;
        } else if (horas > 48) {
            pago = horas * capInicial;
            porcentaje = pago * 0.4;
            pago = pago + porcentaje;
        }

        return pago;
    }

}
