/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AddOns;

import jakarta.websocket.Session;
import java.util.ArrayList;
import java.util.Arrays;

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
    private final String elements2[]
            = {" <div class=\"a1\"> <img src=\"../img/AguaF1_200x200.png\" style=\"width: 100px;\" alt=\"\" srcset=\"\"> </div><div class=\"content\"> <img src=\"../img/Agua200x200.png\" alt=\"\" srcset=\"\"> </div><div class=\"a2\"> <img src=\"../img/AguaF2_200x200.png\" style=\"width: 100px;\" alt=\"\" srcset=\"\"> </div>",
                "<div class=\"f1\"> <img src=\"../img/fuegoF1_200x200.png\" style=\"width: 50px;\" alt=\"\" srcset=\"\"> </div><div class=\"content\"> <img src=\"../img/fuego200x200.png\" alt=\"\" srcset=\"\"> </div><div class=\"f2\"> <img src=\"../img/fuegoF2_200x200.png\" style=\"width: 50px;\" alt=\"\" srcset=\"\"> </div>",
                "<div class=\"h1\"> <img src=\"../img/HieloF1_200x200.png\" style=\"width: 90px;\" alt=\"\" srcset=\"\"> </div><div class=\"content\"> <img src=\"../img/Hielo200x200.png\" alt=\"\" srcset=\"\"> </div><div class=\"h2\"> <img src=\"../img/HieloF2_200x200.png\" style=\"width: 90px;\" alt=\"\" srcset=\"\"> </div>"};

    private String nameRoom;
    private String currentStatus;
    private String jugador1 = "";
    private String jugador2 = "";
    private String mazo_jugador1[] = {"", "", "", "", ""};
    private String mazo_jugador2[] = {"", "", "", "", ""};
    private String actionJugador1 = "";
    private String actionJugador2 = "";
    private Session sessionJugador1 = null;
    private Session sessionJugador2 = null;

    public Session getSessionJugador1() {
        return sessionJugador1;
    }

    public void setSessionJugador1(Session sessionJugador1) {
        this.sessionJugador1 = sessionJugador1;
    }

    public Session getSessionJugador2() {
        return sessionJugador2;
    }

    public void setSessionJugador2(Session sessionJugador2) {
        this.sessionJugador2 = sessionJugador2;
    }

    public String getActionJugador1() {
        return actionJugador1;
    }

    public void setActionJugador1(String actionJugador1) {
        this.actionJugador1 = actionJugador1;
    }

    public String getActionJugador2() {
        return actionJugador2;
    }

    public void setActionJugador2(String actionJugador2) {
        this.actionJugador2 = actionJugador2;
    }

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

    public String[] getMazo_jugador1() {
        return mazo_jugador1;
    }

    public void setMazo_jugador1(String[] mazo_jugador1) {
        this.mazo_jugador1 = mazo_jugador1;
    }

    public String[] getMazo_jugador2() {
        return mazo_jugador2;
    }

    public void setMazo_jugador2(String[] mazo_jugador2) {
        this.mazo_jugador2 = mazo_jugador2;
    }

    public String[] getCard() {
        int n = (int) (Math.random() * (numbers.length - 0));
        String number = numbers[n];
        n = (int) (Math.random() * (elements.length - 0));
        String element = elements[n];
        String elementAux = "";
        if (n == 0) {
            elementAux = "agua";
        } else if (n == 1) {
            elementAux = "fuego";
        } else if (n == 2) {
            elementAux = "hielo";
        }
        n = (int) (Math.random() * (colors.length - 0));
        String color = colors[n];
        String aux = "<div class=\"card-jitsu orientation " + color + "\"> <div class=\"card-number-top\"> <span>" + number + "</span> </div>" + element + " <div class=\"card-number-bottom\"> <span style=\"transform: rotate(180deg);\">" + number + "</span> </div></div>";

        String cards[] = {aux, (number + "@" + elementAux + "@" + color)};

        return cards;
    }

    public String getCard(ArrayList<CardJitsu> Salas_Cardjitsu, String action, String nickname) {
        int index = 0;
        int jugador = 0;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getJugador1().equals(nickname)) {
                index = i;
                jugador = 1;
            } else if (Salas_Cardjitsu.get(i).getJugador2().equals(nickname)) {
                index = i;
                jugador = 2;
            }
        }

        String actionAux = "";
        if (jugador == 1) {
            actionAux = Salas_Cardjitsu.get(index).getActionJugador1();
        } else if (jugador == 2) {
            actionAux = Salas_Cardjitsu.get(index).getActionJugador2();
        }
        String[] actionAux2 = actionAux.split("@");
        int n = 0;
        switch (actionAux2[1]) {
            case "agua":
                n = 0;
                break;
            case "fuego":
                n = 1;
                break;
            case "hielo":
                n = 2;
                break;
            default:
                break;
        }
        String aux = "<div class=\"card-jitsu " + actionAux2[2] + "\"> <div class=\"card-number-top\"> <span>" + actionAux2[0] + "</span> </div>" + elements2[n] + " <div class=\"card-number-bottom\"> <span style=\"transform: rotate(180deg);\">" + actionAux2[0] + "</span> </div></div>";
        return aux;
    }

    public String getCard(ArrayList<CardJitsu> Salas_Cardjitsu, Session session) {
        int index = 0;
        int jugador = 0;

        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getSessionJugador1() == session) {
                index = i;
                jugador = 1;
            } else if (Salas_Cardjitsu.get(i).getSessionJugador2() == session) {
                index = i;
                jugador = 2;
            }
        }
        String actionAux = "";
        if (jugador == 1) {
            actionAux = Salas_Cardjitsu.get(index).getActionJugador2();
        } else if (jugador == 2) {
            actionAux = Salas_Cardjitsu.get(index).getActionJugador1();
        }
        String[] actionAux2 = actionAux.split("@");
        int n = 0;
        switch (actionAux2[1]) {
            case "agua":
                n = 0;
                break;
            case "fuego":
                n = 1;
                break;
            case "hielo":
                n = 2;
                break;
            default:
                break;
        }
        String aux = "<div class=\"card-jitsu " + actionAux2[2] + "\"> <div class=\"card-number-top\"> <span>" + actionAux2[0] + "</span> </div>" + elements2[n] + " <div class=\"card-number-bottom\"> <span style=\"transform: rotate(180deg);\">" + actionAux2[0] + "</span> </div></div>";
        return aux;
    }

    public void setAction(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName, String nickname, String action) {
        int index = 0;
        int actionAux = Integer.parseInt(action);
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getNameRoom().equals(roomName)) {
                index = i;
            }
        }
        CardJitsu c = new CardJitsu();
        if (Salas_Cardjitsu.get(index).getJugador1().equals(nickname)) {
            Salas_Cardjitsu.get(index).setActionJugador1(Salas_Cardjitsu.get(index).getMazo_jugador1()[actionAux]);
            Salas_Cardjitsu.get(index).mazo_jugador1[actionAux] = c.replaceCard();
        } else if (Salas_Cardjitsu.get(index).getJugador2().equals(nickname)) {
            Salas_Cardjitsu.get(index).setActionJugador2(Salas_Cardjitsu.get(index).getMazo_jugador2()[actionAux]);
            Salas_Cardjitsu.get(index).mazo_jugador2[actionAux] = c.replaceCard();
        }
    }

    public String replaceCard() {
        int n = (int) (Math.random() * (numbers.length - 0));
        String number = numbers[n];
        n = (int) (Math.random() * (elements.length - 0));
        String elementAux = "";
        switch (n) {
            case 0:
                elementAux = "agua";
                break;
            case 1:
                elementAux = "fuego";
                break;
            case 2:
                elementAux = "hielo";
                break;
            default:
                break;
        }
        n = (int) (Math.random() * (colors.length - 0));
        String color = colors[n];
        return (number + "@" + elementAux + "@" + color);
    }

    public String getMazo(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName, String nickname) {
        CardJitsu c = new CardJitsu();
        String aux = "";
        String mazo[] = {"", "", "", "", ""};
        int index = 0;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getNameRoom().equals(roomName)) {
                index = i;
            }
        }
        for (int i = 0; i < 5; i++) {
            String card[] = c.getCard();
            if (i < 2) {
                aux = aux + card[0].replace("orientation", "left-1");
            } else if (i == 2) {
                aux = aux + card[0].replace("orientation", "center");
            } else if (i > 2) {
                aux = aux + card[0].replace("orientation", "right-1");
            }
            mazo[i] = card[1];
        }
        if (Salas_Cardjitsu.get(index).getJugador1().equals(nickname)) {
            Salas_Cardjitsu.get(index).setMazo_jugador1(mazo);
        } else if (Salas_Cardjitsu.get(index).getJugador2().equals(nickname)) {
            Salas_Cardjitsu.get(index).setMazo_jugador2(mazo);
        }
        return (aux);
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
                aux = i;
            } else if (Salas_Cardjitsu.get(i).getJugador2().equals(nickname)) {
                Salas_Cardjitsu.get(i).setJugador2("");
                aux = i;
            }
        }

        if (Salas_Cardjitsu.get(aux).getJugador1().equals("") && Salas_Cardjitsu.get(aux).getJugador2().equals("")) {
            Salas_Cardjitsu.remove(aux);
        }
        return Salas_Cardjitsu;
    }

    public boolean turnCard(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName) {
        boolean flag;
        int aux = 0;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getNameRoom().equals(roomName)) {
                aux = i;
            }
        }
        flag = !"".equals(Salas_Cardjitsu.get(aux).getActionJugador1()) && !"".equals(Salas_Cardjitsu.get(aux).getActionJugador2());
        return flag;

    }

    public String updateMazo(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName, Session session) {
        String mazoA[] = null;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getSessionJugador1() == session) {
                mazoA = Salas_Cardjitsu.get(i).getMazo_jugador1();
            } else if (Salas_Cardjitsu.get(i).getSessionJugador2() == session) {
                mazoA = Salas_Cardjitsu.get(i).getMazo_jugador2();
            }
        }
        String mazo = "";
        for (int i = 0; i < mazoA.length; i++) {
            int n = 0;
            String[] actionAux2 = mazoA[i].split("@");
            switch (actionAux2[1]) {
                case "agua":
                    n = 0;
                    break;
                case "fuego":
                    n = 1;
                    break;
                case "hielo":
                    n = 2;
                    break;
            }

            String aux = "<div class=\"card-jitsu orientation " + actionAux2[2] + "\"> <div class=\"card-number-top\"> <span>" + actionAux2[0] + "</span> </div>" + elements[n] + " <div class=\"card-number-bottom\"> <span style=\"transform: rotate(180deg);\">" + actionAux2[0] + "</span> </div></div>";
            if (i < 2) {
                mazo = mazo + aux.replace("orientation", "left-1");
            } else if (i == 2) {
                mazo = mazo + aux.replace("orientation", "center");
            } else if (i > 2) {
                mazo = mazo + aux.replace("orientation", "right-1");
            }

        }
        return mazo;

    }

    public String resultAction(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName, Session session) {
        int aux = 0;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getNameRoom().equals(roomName)) {
                aux = i;
            }
        }
        String j1[] = Salas_Cardjitsu.get(aux).getActionJugador1().split("@");
        String j2[] = Salas_Cardjitsu.get(aux).getActionJugador2().split("@");
//        Salas_Cardjitsu.get(aux).setActionJugador1("");
//        Salas_Cardjitsu.get(aux).setActionJugador2("");
        String ganador = "";
        //0:Numero
        //1:Elemento
        //2:Color
        if (j1[1].equals("fuego") && j2[1].equals("hielo")) {
            ganador = "j1";
        } else if (j2[1].equals("fuego") && j1[1].equals("hielo")) {
            ganador = "j2";
        } else if (j1[1].equals("agua") && j2[1].equals("fuego")) {
            ganador = "j1";
        } else if (j2[1].equals("agua") && j1[1].equals("fuego")) {
            ganador = "j2";
        } else if (j1[1].equals("hielo") && j2[1].equals("agua")) {
            ganador = "j1";
        } else if (j2[1].equals("hielo") && j1[1].equals("agua")) {
            ganador = "j2";
        } else if (j1[1].equals(j2[1])) {
            int j1n = Integer.parseInt(j1[0]);
            int j2n = Integer.parseInt(j2[0]);
            if (j1n > j2n) {
                ganador = "j1";
            } else if (j1n < j2n) {
                ganador = "j2";
            } else if (j1n == j2n) {
                ganador = "empate";
            }
        }

        if (ganador.equals("j1") && Salas_Cardjitsu.get(aux).getSessionJugador1() == session) {
            ganador = "me@" + j1[2] + "@" + j1[1];
        } else if (ganador.equals("j1") && Salas_Cardjitsu.get(aux).getSessionJugador2() == session) {
            ganador = "enemy@" + j1[2] + "@" + j1[1];
        } else if (ganador.equals("j2") && Salas_Cardjitsu.get(aux).getSessionJugador2() == session) {
            ganador = "me@" + j2[2] + "@" + j2[1];
        } else if (ganador.equals("j2") && Salas_Cardjitsu.get(aux).getSessionJugador1() == session) {
            ganador = "enemy@" + j2[2] + "@" + j2[1];
        } else {
            ganador = "empate";
        }
        return ganador;
    }

    public void resetActions(ArrayList<CardJitsu> Salas_Cardjitsu, String roomName) {
        int aux = 0;
        for (int i = 0; i < Salas_Cardjitsu.size(); i++) {
            if (Salas_Cardjitsu.get(i).getNameRoom().equals(roomName)) {
                aux = i;
            }
        }
        Salas_Cardjitsu.get(aux).setActionJugador1("");
        Salas_Cardjitsu.get(aux).setActionJugador2("");
    }

    @Override
    public String toString() {
        return "CardJitsu{" + "nameRoom=" + nameRoom + ", currentStatus=" + currentStatus + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + ", mazo_jugador1=" + mazo_jugador1 + ", mazo_jugador2=" + mazo_jugador2 + ", actionJugador1=" + actionJugador1 + ", actionJugador2=" + actionJugador2 + '}';
    }

}
