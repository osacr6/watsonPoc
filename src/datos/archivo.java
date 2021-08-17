/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author d.murillo.porras
 */
public class archivo {
    String separador  = ",";
    protected String directorioRaiz = Paths.get("").toAbsolutePath().normalize().toString();
    String directorioDatos = directorioRaiz +  "/datos/";

    public archivo() {
        File nuevoFolder = new File(directorioDatos);
        boolean folderDatos = nuevoFolder.mkdir();
    }
    
    public ArrayList<String[]> leerArchivoCSV(String nombre){
        String nombreArchivo = directorioDatos + nombre + ".csv";
        BufferedReader bufferLectura  = null;
        String linea= "";
        ArrayList<String[]> datosArchivo = new ArrayList<String[]>();
        
        try {
            bufferLectura  = new BufferedReader(new FileReader(nombreArchivo));
            while ((linea = bufferLectura.readLine()) != null) {                
                String[] datos = linea.split(separador);
                datosArchivo.add(datos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferLectura  != null) {
                try {
                    bufferLectura .close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return datosArchivo;
    }
    
    public String[] BuscarDato(String valor, int columna, ArrayList<String[]> csv) {
        String[] linea = null;
        for (int i = 0; i < csv.size(); i++) {
            String[] datos = csv.get(i);
            if (datos[columna].toString().replaceAll("\\s", "").equals(valor.replaceAll("\\s", ""))) {
                linea = datos;
            }
        }
        return linea;
    }
    
    public void crearArchivoCSV(String nombre, ArrayList<String[]> csv) {
        String nombreArchivo = directorioDatos + nombre + ".csv";
        try {
            File statText = new File(nombreArchivo);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            
            for (int i = 0; i < csv.size(); i++) {
                String[] datos = csv.get(i);
                String lineatexto = "";
                
                for (int j = 0; j < datos.length; j++) {
                    if(j == 0){
                        lineatexto = datos[j];
                    } else {
                        lineatexto = lineatexto + separador + datos[j];
                    }
                }
                
                w.write(lineatexto + "\n");
            }
            
            w.close();
        } catch (IOException e) {
            System.err.println("Error de archivo: " + e);
        }
    }
}
