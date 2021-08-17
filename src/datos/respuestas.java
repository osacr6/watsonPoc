/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author d.murillo.porras
 */
public class respuestas {
    private ArrayList<String[]> listaPreguntas = null;
    private String encabezados[] = new String[]{
        "Identificador",
        "Respuesta"
    };
    
    public respuestas(ArrayList<String[]> respuestas) {
        this.listaPreguntas = respuestas;
    }
    
    public DefaultTableModel getModelo() {
        DefaultTableModel nuevoModelo = new DefaultTableModel();
        nuevoModelo.setColumnIdentifiers(encabezados);

        for (int i = 0; i < listaPreguntas.size(); i++) {
            String[] datos = listaPreguntas.get(i);
            nuevoModelo.addRow(new Object[]{
                datos[0].toString(),
                datos[1].toString(),
            });
        }
                    
        return nuevoModelo;
    }
}
