/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Serializar;

import AddOns.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class DeserializarRoom {

    private FileInputStream file;
    private ObjectInputStream input;

    public void abrir() throws IOException {
        file = new FileInputStream("salas.ser");
        input = new ObjectInputStream(file);
    }

    public void cerrar() throws IOException {
        if (input != null) {
            input.close();
        }
    }

    public ArrayList<Salas> getCurrentUsuariosList() throws IOException, ClassNotFoundException {
        abrir();
        ArrayList<Salas> aux = null;
        if (input != null) {
            try {
                aux = (ArrayList<Salas>) input.readObject();
            } catch (EOFException e) {
            }
        }
        cerrar();
        return aux;
    }
}
