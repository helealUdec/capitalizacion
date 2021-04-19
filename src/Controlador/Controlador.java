package Controlador;

import Modelo.Modelo;
import Vista.DatosIngresados;
import Vista.DatosUsuario;
import Vista.InicioApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {

    private Modelo modelo;
    private InicioApp view;
    private DatosUsuario datosUsuario;
    private DatosIngresados datosIngresados;
    private int width, height;

    public Controlador(Modelo modelo, InicioApp view) {
        this.modelo = modelo;
        this.view = view;
        initComponet();
    }

    public Modelo getModelo() {
        return modelo;
    }

    public InicioApp getView() {
        return view;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setView(InicioApp view) {
        this.view = view;
    }

    private void initComponet() {
        //tamano de la interfaz
        width = view.getWidth();
        height = view.getHeight();
        datosUsuario = new DatosUsuario();
        datosIngresados = new DatosIngresados();
        // usuario
        datosUsuario.setVisible(false);
        datosUsuario.setBounds(0, 0, 700, 490);
        view.add(datosUsuario);
        datosUsuario.jbuttonAtras.addActionListener(this);

        // ingresar
        datosIngresados.setVisible(false);
        datosIngresados.setBounds(0, 0, 490, 490);
        view.add(datosIngresados);
        datosIngresados.jbuttonAtras.addActionListener(this);
        datosIngresados.jbuttonIngresar.addActionListener(this);
        // ventana pricipal
        view.jbuttonIngresar.addActionListener(this);
        view.jbuttonImprimir.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // cambio entre paneles de la vista pricnipal
        if (e.getSource() == view.jbuttonIngresar) {
            // para cambiar a ingresar datos
            view.jPanel1.setVisible(false);
            datosUsuario.setVisible(false);
            datosIngresados.setVisible(true);
            view.setSize(view.getWidth() - 2, 440);

        } else if (e.getSource() == view.jbuttonImprimir) {
            // para cambiar a imprimir
            view.jPanel1.setVisible(false);
            datosIngresados.setVisible(false);
            datosUsuario.setVisible(true);
            view.setSize(640, 395);
            modelo.llenarTabla(datosUsuario);
        }

        // boton de atras en otras interfaces
        if (e.getSource() == datosIngresados.jbuttonAtras || e.getSource() == datosUsuario.jbuttonAtras) {
            datosUsuario.setVisible(false);
            datosIngresados.setVisible(false);
            view.jPanel1.setVisible(true);
            view.setSize(width - 2, height);
        }

        //Ingresar datos
        if (e.getSource() == datosIngresados.jbuttonIngresar) {
            boolean pasar = false;
            String cedula = datosIngresados.jtextCedula.getText();
            String nombre = datosIngresados.jtextNombre.getText();
            String apellido = datosIngresados.jtextApellido.getText();
            String telefono = datosIngresados.jtextTelefono.getText();
            String horas = datosIngresados.jtextHoras.getText();
            String capInicial = datosIngresados.jtextValorHora.getText();
            
            // verificar para ingresar datos
            if ("".equals(cedula) || "".equals(nombre) || "".equals(apellido) || "".equals(telefono) || "".equals(horas) || "".equals(capInicial)) {
                JOptionPane.showMessageDialog(null, "Por favor rellene los campos");
            } 
            
            try {
               Integer.parseInt(horas);
               pasar = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Diguite un numero de horas correcto\nejemplo ( 1 )");
                pasar = false;
            }
            
            try {
                Double.parseDouble(capInicial);
                pasar = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Diguite un valor de horas correcto\nejemplo ( 20000 )");
                pasar = false;
            }
            
            if(pasar) {
                 modelo.almacenarDatos(datosIngresados);
            } 
               
        }
        
        

    }

}
