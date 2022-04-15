/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AddOns;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Usuarios implements Serializable {

    private String nickname;
    private String sessionId;
    private String sala;
    private static final long serialVersionUID = 1857893973041690918L;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return nickname + "@" + sessionId;
    }

    public int getIndexUsuario(ArrayList<Usuarios> usuarios, String nickname) {
        int aux=0;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNickname().equals(nickname)) {
                aux = i;
            }
        }
        return aux;
    }

}
