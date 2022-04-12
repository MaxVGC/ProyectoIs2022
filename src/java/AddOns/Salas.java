/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AddOns;

import Serializar.CurrentSalas;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Salas implements Serializable {

    private static final long serialVersionUID = 1857893973041690918L;
    private String nombre;
    private String juego;
    private ArrayList<Usuarios> usuarios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public ArrayList<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public int getNameRoom() {
        try {
            String aux = this.nombre;
            ArrayList<Salas> aux2 = new CurrentSalas().getCurrentSalasList();
            int aux3 = 0;
            if (aux2 != null) {
                for (int i = 0; i < aux2.size(); i++) {
                    if (aux2.get(i).getNombre().equals(aux)) {
                        aux3 = i;
                    }
                }
            }
            return aux3;
        } catch (IOException ex) {
            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public void addUsuario(String nickname) {
        try {
            Usuarios usuario = new Usuarios();
            usuario.setNickname(nickname);
            int index = getNameRoom();
            ArrayList<Salas> CSalas = new CurrentSalas().getCurrentSalasList();
            Salas sala=CSalas.get(index);
            ArrayList<Usuarios> aux=sala.getUsuarios();
            aux.add(usuario);
            sala.setUsuarios(aux);
            CSalas.remove(index);
            CSalas.add(sala);
            new CurrentSalas().setCurrentSalasList(CSalas);
        } catch (IOException ex) {
            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

//try {
//            Usuarios usuario = new Usuarios();
//            usuario.setNickname(nickname);
//            int index = getNameRoom();
//            ArrayList<Salas> CSalas = new CurrentSalas().getCurrentSalasList();
//            Salas aux = null;
//            ArrayList<Usuarios> usuarios;
//            if (CSalas.size()!=0) {
//                aux = CSalas.get(index);
//                usuarios = aux.getUsuarios();
//                System.out.println("entro");
//                CSalas.remove(index);
//            } else {
//                CSalas = new ArrayList<>();
//                System.out.println("no entro");
//                aux = new Salas();
//                usuarios = new ArrayList<>();
//            }
//            usuarios.add(usuario);
//            aux.setUsuarios(usuarios);
//            aux.setNombre(this.nombre);
//            CSalas.add(aux);
//            new CurrentSalas().setCurrentSalasList(CSalas);
//        } catch (IOException ex) {
//            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
//        }
