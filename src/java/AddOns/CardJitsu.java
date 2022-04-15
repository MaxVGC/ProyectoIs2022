/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AddOns;

import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class CardJitsu {

    private final String colors[] = {"yellow", "celeste", "red", "purple", "blue", "green"};
    private final String numbers[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private final String elements[]
            = {" <div class=\"a1\"> <img src=\"../img/AguaF1_100x100.png\" style=\"width: 100px;\" alt=\"\" srcset=\"\"> </div><div class=\"content\"> <img src=\"../img/Agua100x100.png\" alt=\"\" srcset=\"\"> </div><div class=\"a2\"> <img src=\"../img/AguaF2_100x100.png\" style=\"width: 100px;\" alt=\"\" srcset=\"\"> </div>",
                "<div class=\"f1\"> <img src=\"../img/fuegoF1_100x100.png\" style=\"width: 50px;\" alt=\"\" srcset=\"\"> </div><div class=\"content\"> <img src=\"../img/fuego100x100.png\" alt=\"\" srcset=\"\"> </div><div class=\"f2\"> <img src=\"../img/fuegoF2_100x100.png\" style=\"width: 50px;\" alt=\"\" srcset=\"\"> </div>",
                "<div class=\"h1\"> <img src=\"../img/HieloF1_100x100.png\" style=\"width: 90px;\" alt=\"\" srcset=\"\"> </div><div class=\"content\"> <img src=\"../img/Hielo100x100.png\" alt=\"\" srcset=\"\"> </div><div class=\"h2\"> <img src=\"../img/HieloF2_100x100.png\" style=\"width: 90px;\" alt=\"\" srcset=\"\"> </div>"};

    private String nameRoom;
    private String currentStatus;
    private String jugador1="";
    private String jugador2="";
    private String mazo_jugador1;
    private String mazo_jugador2;

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getMazo_jugador1() {
        return mazo_jugador1;
    }

    public void setMazo_jugador1(String mazo_jugador1) {
        this.mazo_jugador1 = mazo_jugador1;
    }

    public String getMazo_jugador2() {
        return mazo_jugador2;
    }

    public void setMazo_jugador2(String mazo_jugador2) {
        this.mazo_jugador2 = mazo_jugador2;
    }

    public String getCard() {
        int n = (int) (Math.random() * (numbers.length - 0));
        String number = numbers[n];
        n = (int) (Math.random() * (elements.length - 0));
        String element = elements[n];
        n = (int) (Math.random() * (colors.length - 0));
        String color = colors[n];
        String aux = "<div class=\"card-jitsu orientation " + color + "\"> <div class=\"card-number-top\"> <span>" + number + "</span> </div>" + element + " <div class=\"card-number-bottom\"> <span style=\"transform: rotate(180deg);\">" + number + "</span> </div></div>";
        return aux;
    }

    public CardJitsu getJuego(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName) {
        int aux = 0;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getNameRoom().equals(roomName)) {
                aux = i;
            }
        }
        return Salas_Cardjitsu.get(aux);
    }

    public ArrayList<CardJitsu> removeUsuario(ArrayList<CardJitsu> Salas_Cardjitsu, String nickname) {
        int aux = 0;
        
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getJugador1().equals(nickname)) {
                Salas_Cardjitsu.get(i).setJugador1("");
                aux=i;
            } else if (Salas_Cardjitsu.get(i).getJugador2().equals(nickname)) {
                Salas_Cardjitsu.get(i).setJugador2("");
                aux=i;
            }
        }
        
        if(Salas_Cardjitsu.get(aux).getJugador1().equals("") &&Salas_Cardjitsu.get(aux).getJugador2().equals("")){
            Salas_Cardjitsu.remove(aux);
        }
        return Salas_Cardjitsu;
    }

    @Override
    public String toString() {
        return "CardJitsu{" + "nameRoom=" + nameRoom + ", currentStatus=" + currentStatus + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + '}';
    }

}
