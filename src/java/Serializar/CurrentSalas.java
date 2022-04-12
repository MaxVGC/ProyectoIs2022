/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Serializar;

import AddOns.Salas;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class CurrentSalas {

    public void setCurrentSalasList(ArrayList<Salas> salas) throws IOException {
        FileOutputStream a = new FileOutputStream("salas.ser");
        ObjectOutputStream output = new ObjectOutputStream(a);

        if (output != null) {
            output.writeObject(salas);
        }

        if (output != null) {
            output.close();
        }
    }

    public ArrayList<Salas> getCurrentSalasList() throws IOException, ClassNotFoundException {
        FileInputStream b = new FileInputStream("salas.ser");
        ObjectInputStream input = new ObjectInputStream(b);
        ArrayList<Salas> aux = null;

        if (input != null) {
            try {
                aux = (ArrayList<Salas>) input.readObject();
            } catch (EOFException e) {
            }
        }

        if (input != null) {
            input.close();
        }

        return aux;
    }
}
