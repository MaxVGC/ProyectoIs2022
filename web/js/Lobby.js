/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var xMLHttpRequest = new XMLHttpRequest();
var webSocket;
var nick = readCookie("nickname");
var send_btn = document.getElementById("send-btn");
var room_create = document.getElementById("room-create");
var close_create_btn = document.getElementById("close-crear");

room_create.addEventListener("click", function() {
    document.getElementById("crear-sala").classList.toggle("active");
});
send_btn.addEventListener("click", send_msg);
close_create_btn.addEventListener("click", function() {
    document.getElementById("crear-sala").classList.toggle("active");
});

Validar();

function readCookie(name) {
    return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + name.replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
}

function Validar() {
    if (readCookie("nickname") != null && readCookie("uuid") != null) {
        var nick = readCookie("nickname");
        xMLHttpRequest.open("Get", "../ValidarUsuario?nick=" + nick + "&sessionid=" + readCookie("uuid"), true);
        xMLHttpRequest.onreadystatechange = function() {
            if (xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
                if (xMLHttpRequest.responseText === "null") {
                    document.getElementById("header-nickname-txt").innerHTML = nick;
                    initWebSocket(nick);
                } else {
                    window.location = "inicio.jsp";
                }
                console.log(xMLHttpRequest.responseText);
            }
        };
        xMLHttpRequest.send(null);
    } else {
        window.location = "inicio.jsp";
    }
}

function initWebSocket(nickname) {
    if ("WebSocket" in window) {
        if (webSocket == null) {
            var url = "ws://localhost:8080/JuegoDeCartas/webSocket/chat/lobby/" + nickname + "@" + readCookie("uuid");
            webSocket = new WebSocket(url);
        } else {
            alert("Has entrado en la sala de chat ...");
        }

        webSocket.onopen = function() {};

        webSocket.onmessage = function(evt) {
            if (evt.data != "null") {
                var msg_board = document.getElementById("chat-body");
                var received_msg = evt.data;
                var old_msg = msg_board.innerHTML;
                msg_board.innerHTML = old_msg + received_msg;
                // Mueve el bloque de desplazamiento hacia abajo
                msg_board.scrollTop = msg_board.scrollHeight;
            } else {
                window.location = "inicio.jsp";
            }
        };

        webSocket.onclose = function() {
            webSocket = null;
        };
    } else {
        alert("¡Su navegador no es compatible con WebSocket!");
    }
}

function send_msg() {
    if (webSocket != null) {
        var input_msg = document.getElementById("input_msg").value.trim();
        if (input_msg == "") {
            return;
        }
        webSocket.send(input_msg);
        document.getElementById("input_msg").value = "";
    } else {
        alert("Estás desconectado, vuelve a ingresar a la sala de chat ...");
    }
};

function Crear_sala() {
    document.cookie = "room=";
    document.cookie = "type_game=";
    var name = document.getElementById("name_crear_sala").value;
    var type = document.getElementById("select_crear_sala").value;
    document.cookie = "room=" + name;
    document.cookie = "type_game=" + type;
    if (type == "Card-Jitsu") {
        window.location = "cardjitsu.jsp";
    }
}



window.addEventListener('keydown', (event) => {
    if (event.code === 'Enter') {
        send_msg();
    }
});