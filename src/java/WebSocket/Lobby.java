/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocket;

import AddOns.Core;
import Serializar.SerializarUsuario;
import AddOns.Usuarios;
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
@ServerEndpoint("/webSocket/chat/{roomName}/{id}")
public class Lobby {

    // Use el mapa para recopilar la sesión, la clave es roomName, el valor es una colección de usuarios en la misma sala
    // Se informa un error cuando la clave de concurrentMap no existe, no es nula
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    private static final ArrayList<Usuarios> usuarios = new ArrayList();
    private static final Core core = new Core();
    SerializarUsuario Su = new SerializarUsuario();

    @OnOpen
    public void connect(@PathParam("roomName") String roomName, @PathParam("id") String token, Session session) throws Exception {
        String[] info = token.split("@");
        if (!rooms.containsKey(roomName)) {
            Usuarios aux = new Usuarios();
            Set<Session> room = new HashSet<>();
            room.add(session);
            aux.setNickname(info[0]);
            aux.setSessionId(info[1]);
            usuarios.add(aux);
            rooms.put(roomName, room);
        } else {
            Usuarios aux = new Usuarios();
            aux.setNickname(info[0]);
            aux.setSessionId(info[1]);
            usuarios.add(aux);
            rooms.get(roomName).add(session);
        }
        System.out.println(info[0] + " conectado a sala " + roomName);
        Su.setCurrentUsuariosList((ArrayList<Usuarios>) usuarios.clone());
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, @PathParam("id") String nickname, Session session) throws IOException {
        String[] info = nickname.split("@");
        usuarios.remove(core.getUsuario(usuarios, info[0]));
        Su.setCurrentUsuariosList((ArrayList<Usuarios>) usuarios.clone());
        rooms.get(roomName).remove(session);
        System.out.println(nickname + " desconectado");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName, @PathParam("id") String nickname, String msg, Session session) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String[] info = nickname.split("@");
        String time = simpleDateFormat.format(date);
        String message;

        for (Session session1 : rooms.get(roomName)) {
            if (session1.equals(session)) {
                message = "<div class=\"div-message\" style=\"display: flex;flex-direction: column;align-items: flex-end;\"><div class=\"msg-nickname-me\"> <span>"+info[0]+"</span> </div><div class=\"message-me\"><div style=\"display: flex;justify-content: flex-end;\"><span>" + msg + "</span></div><div class=\"msg-time\"><span>" + time + "</span></div></div></div>";
            } else {
                message = "<div class=\"div-message\"> <div class=\"msg-nickname\"> <span>" + info[0] + "</span> </div><div class=\"message\"> <span>" + msg + "</span> <div class=\"msg-time\"> <span> " + time + " </span> </div></div></div>";
            }
            session1.getBasicRemote().sendText(message);
        }
    }
}
