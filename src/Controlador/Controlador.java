package Controlador;

import Modelo.Modelo;
import Vista.DatosIngresados;
import Vista.DatosUsuario;
import Vista.InicioApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // ventana pricipal
        view.jbuttonIngresar.addActionListener(this);
        view.jbuttonImprimir.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // cambio entre paneles de la vista pricnipal
        if (e.getSource() == view.jbuttonIngresar) {
            view.jPanel1.setVisible(false);
            datosUsuario.setVisible(false);
            datosIngresados.setVisible(true);
            view.setSize(view.getWidth() - 2, 440);

        } else if (e.getSource() == view.jbuttonImprimir) {
            view.jPanel1.setVisible(false);
            datosIngresados.setVisible(false);
            datosUsuario.setVisible(true);
            view.setSize(640, 395);
        }

        // boton de atras en otras interfaces
    }

}