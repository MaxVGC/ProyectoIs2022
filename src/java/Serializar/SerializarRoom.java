/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Serializar;

import AddOns.Salas;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class SerializarRoom {
    private FileOutputStream file;
    private ObjectOutputStream output;

    
    public void abrir() throws IOException{
        file = new FileOutputStream("salas.ser");
        output=new ObjectOutputStream(file);
    }
    
    public void cerrar() throws IOException{
        if(output!=null){
            output.close();
        }
    }
    
    public void setCurrentRoomList(ArrayList<Salas>  salas) throws IOException{
        abrir();
        if(output!=null){
            output.writeObject(salas);
        }
        cerrar();
    }
}
