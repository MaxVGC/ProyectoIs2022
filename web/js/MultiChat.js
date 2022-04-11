/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

$(document).ready(function() {
    $("#quxiaobtn").click(function() {
        $("#text").val('');
    });

});

var webSocket;

function send_msg() {
    if (webSocket != null) {
        var input_msg = document.getElementById("input_msg").value.trim();
        if (input_msg == "") {
            return;
        }
        webSocket.send(input_msg);
        // Borrar la información en el cuadro de entrada
        document.getElementById("input_msg").value = "";
    } else {
        alert("Estás desconectado, vuelve a ingresar a la sala de chat ...");
    }
};

function closeWs() {
    webSocket.close();
};

function initWebSocket() {
    var roomName = document.getElementById("input_roomName").value;
    var nickname = document.getElementById("input_nickname").value;
    // El nombre de la sala no puede estar vacío
    if (roomName == null || roomName == "") {
        alerta("Ingrese el nombre de la sala");
        return;
    }
    if ("WebSocket" in window) {
        // alert ("¡Su navegador es compatible con WebSocket!");
        if (webSocket == null) {
            var url = "ws://localhost:8080/JuegoDeCartas/webSocket/chat/" + roomName + "/"+nickname;
            // Abrir un socket web
            webSocket = new WebSocket(url); 
        } else {
            alert("Has entrado en la sala de chat ...");
        }

        webSocket.onopen = function(evt) {
            alert();
        };

        webSocket.onmessage = function(evt) {
            var msg_board = document.getElementsByClassName("msg_board")[0];
            var received_msg = evt.data;
            var old_msg = msg_board.innerHTML;
            msg_board.innerHTML = old_msg + received_msg;
            // Mueve el bloque de desplazamiento hacia abajo
            msg_board.scrollTop = msg_board.scrollHeight;
        };

        webSocket.onclose = function() {
            // Cierre websocket y borre el panel de información
            alert("Conexión cerrada ...");
            webSocket = null;
            document.getElementsByClassName("msg_board")[0].innerHTML = "";
        };
    } else {
        // El navegador no es compatible con WebSocket
        alert("¡Su navegador no es compatible con WebSocket!");
    }
}