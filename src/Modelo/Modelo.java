package Modelo;

import Vista.DatosIngresados;
import Vista.DatosUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
        String[] datos = {cedula, nombre, apellido, telefono, String.valueOf(capInicial), String.valueOf(horas), String.valueOf(pago)};
        listaDatos.add(datos);
        JOptionPane.showMessageDialog(null, "Datos guardados");
        JOptionPane.showMessageDialog(null, "Debe pagarle: $ " + pago + "  Al empleado: " + nombre);
        datosIngresados.jtextCedula.setText("");
        datosIngresados.jtextNombre.setText("");
        datosIngresados.jtextApellido.setText("");
        datosIngresados.jtextTelefono.setText("");
        datosIngresados.jtextHoras.setText("");
        datosIngresados.jtextValorHora.setText("");
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

    public void llenarTabla(DatosUsuario datosUsuario) {
        DefaultTableModel modelo = (DefaultTableModel) datosUsuario.jtableDatos.getModel();
        int n = listaDatos.size();
        for (int i = 0; i < n; i++) {
            modelo.addRow(listaDatos.get(i));
        }
    }

    public void busqueda(DatosUsuario datosUsuario) {
        String busqueda = datosUsuario.jtextBuscar.getText();
        boolean encontrado = false;
        int n = listaDatos.size();
        DefaultTableModel modelo = (DefaultTableModel) datosUsuario.jtableDatos.getModel();
        for (int i = 0; i < n; i++) {
            modelo.removeRow(i);
        }
        if ("".equals(busqueda)) {
            JOptionPane.showMessageDialog(null, "Diguite una busqueda");
            llenarTabla(datosUsuario);
        } else {

            int m = listaDatos.get(0).length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (listaDatos.get(i)[j].equals(busqueda)) {
                        encontrado = true;
                        modelo.addRow(listaDatos.get(i));
                        continue;
                    } 
                }
            }
        }
        
        if(!encontrado) JOptionPane.showMessageDialog(null, "Dato no encontrado");
    }

}
