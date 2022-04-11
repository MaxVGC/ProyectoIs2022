/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AddOns;

import Serializar.DeserializarUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Core {

    public Boolean existe(ArrayList<Usuarios> usuarios, String nick) {
        int i = 0;
        String[] aux = nick.split("@");
        if (!usuarios.isEmpty()) {
            for (int j = 0; j < usuarios.size(); j++) {
                if (usuarios.get(j).getNickname().equals(aux[0])) {
                    i = 1;
                }
            }
            if (i == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public int getUsuario(ArrayList<Usuarios> usuarios, String nick) {
        int i = 0;
        for (int j = 0; j < usuarios.size(); j++) {
            if (usuarios.get(j).getNickname().equals(nick)) {
                i = j;
            }
        }
        return i;
    }

    public String generarToken(int length) {
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // 0-62 (exclusivo), retorno aleatorio 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }

    public Boolean isTokenValid(String nick, String token, ArrayList<Usuarios> usuarios) {
        int aux = 0;
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i).toString() + " : " + nick + "@" + token);
            if (usuarios.get(i).toString().equals("" + nick + "@" + token + "")) {
                aux = 1;
            }
        }
        if (aux == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void MostrarUsuarios() {
        try {
            DeserializarUsuario aux = new DeserializarUsuario();
            ArrayList<Usuarios> usuarios = aux.getCurrentUsuariosList();
            
            /* TODO output your page here. You may use following sample code. */
            if (usuarios == null) {
                System.out.print("no ha nada");
            } else {
                for (int i = 0; i < usuarios.size(); i++) {
                    System.out.println(usuarios.get(i).toString());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
