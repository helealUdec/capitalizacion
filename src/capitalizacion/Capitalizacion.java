
package capitalizacion;

import Controlador.Controlador;
import Modelo.Modelo;
import Vista.InicioApp;


public class Capitalizacion {

    public static void main(String[] args) {
        Modelo model = new Modelo();
        InicioApp view = new InicioApp();
        Controlador control = new Controlador(model, view);
        
    }
    
}
