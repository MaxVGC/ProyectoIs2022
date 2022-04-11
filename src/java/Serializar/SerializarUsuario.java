/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Serializar;

import AddOns.*;
import jakarta.websocket.Session;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class SerializarUsuario {
    private FileOutputStream file;
    private ObjectOutputStream output;

    
    public void abrir() throws IOException{
        file = new FileOutputStream("usuarios.ser");
        output=new ObjectOutputStream(file);
    }
    
    public void cerrar() throws IOException{
        if(output!=null){
            output.close();
        }
    }
    
    public void setCurrentUsuariosList(ArrayList<Usuarios>  rooms) throws IOException{
        abrir();
        if(output!=null){
            output.writeObject(rooms);
        }
        cerrar();
    }
}
