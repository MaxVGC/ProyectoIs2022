/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocket;

import AddOns.CardJitsu;
import AddOns.Core;
import AddOns.Salas;
import Serializar.SerializarUsuario;
import AddOns.Usuarios;
import Serializar.CurrentSalas;
import Serializar.DeserializarUsuario;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author carlo
 */
@ServerEndpoint("/webSocket/rooms/{roomName}/{type}/{nick}")
public class Rooms {

    // Use el mapa para recopilar la sesión, la clave es roomName, el valor es una colección de usuarios en la misma sala
    // Se informa un error cuando la clave de concurrentMap no existe, no es nula
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    private static ArrayList<Salas> salasList = null;
    private static final Core core = new Core();
    private static ArrayList<CardJitsu> Salas_Cardjitsu = new ArrayList<>();

    SerializarUsuario Su = new SerializarUsuario();
    Salas sala = new Salas();

    @OnOpen
    public void connect(@PathParam("roomName") String roomName, @PathParam("type") String type, @PathParam("nick") String nickname, Session session) throws Exception {
        if (new CurrentSalas().getCurrentSalasList() != null) {
            salasList = new CurrentSalas().getCurrentSalasList();
        } else {
            salasList = new ArrayList();
        }
        sala.setNombre(roomName);
        if (!rooms.containsKey(roomName)) {
            ArrayList<Usuarios> usuarios = new ArrayList<>();
            Usuarios aux2 = new Usuarios();
            aux2.setNickname(nickname);
            usuarios.add(aux2);
            sala.setUsuarios(usuarios);
            sala.setJuego(type);
            salasList.add(sala);
            if ("Card-Jitsu".equals(type)) {
                System.out.println("entre");
                CardJitsu juego = new CardJitsu();
                juego.setNameRoom(roomName);
                juego.setCurrentStatus("INITIALIZED");
                juego.setJugador1(nickname);
                Salas_Cardjitsu.add(juego);
            }
            Set<Session> room = new HashSet<>();
            room.add(session);
            rooms.put(roomName, room);
            new CurrentSalas().setCurrentSalasList(salasList);
        } else {
            CardJitsu juego = new CardJitsu().getJuego(Salas_Cardjitsu, roomName);
            juego.setJugador2(nickname);
            juego.setCurrentStatus("STARTING");
            sala.addUsuario(nickname);
            rooms.get(roomName).add(session);
        }
        System.out.println(nickname + " se ha conectado a " + roomName);
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, @PathParam("nick") String nickname, Session session) throws IOException, ClassNotFoundException {
        ArrayList<Usuarios> usuarios = new DeserializarUsuario().getCurrentUsuariosList();
        usuarios.remove(core.getUsuario(usuarios, nickname));
        Su.setCurrentUsuariosList((ArrayList<Usuarios>) usuarios.clone());
        sala.removeUsuario(roomName, nickname);
        Salas_Cardjitsu = new CardJitsu().removeUsuario(Salas_Cardjitsu, nickname);
        rooms.get(roomName).remove(session);
        if (rooms.get(roomName).size() == 0) {
            rooms.remove(roomName);
        }
        System.out.println(nickname + " se ha desconectado de " + roomName);
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName, @PathParam("nick") String nickname, String msg, Session session) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        String message;
        String[] data = msg.split("@");
        if (data[0].equals("message")) {
            msg=msg.substring(8, msg.length());
            for (Session session1 : rooms.get(roomName)) {
                if (session1.equals(session)) {
                    message = "<div class=\"div-message\" style=\"display: flex;flex-direction: column;align-items: flex-end;\"><div class=\"msg-nickname-me\"> <span>" + nickname + "</span> </div><div class=\"message-me\"><div style=\"display: flex;justify-content: flex-end;\"><span>" + msg  + "</span></div><div class=\"msg-time\"><span>" + time + "</span></div></div></div>";
                } else {
                    message = "<div class=\"div-message\"> <div class=\"msg-nickname\"> <span>" + nickname + "</span> </div><div class=\"message\"> <span>" + msg + "</span> <div class=\"msg-time\"> <span> " + time + " </span> </div></div></div>";
                }
                session1.getBasicRemote().sendText(message);
            }
        } else if (data[0].equals("nickname")) {
            for (Session session1 : rooms.get(roomName)) {
                if (!session1.equals(session)) {
                    session1.getBasicRemote().sendText(msg);
                }
            }
        }

    }

    public String generateDiv(String msg, String nickname) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        String message = "<div class=\"div-message\"> <div class=\"msg-nickname\"> <span>" + nickname + "</span> </div><div class=\"message\"> <span>" + msg + "</span> <div class=\"msg-time\"> <span> " + time + " </span> </div></div></div>";
        return message;
    }
}
