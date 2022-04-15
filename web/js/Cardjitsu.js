var xMLHttpRequest = new XMLHttpRequest();
var send_btn = document.getElementById("send-btn");
send_btn.addEventListener("click", send_msg);
var webSocket;
var cards;

ValidarUsuario();

function readCookie(name) {
    return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + name.replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
}

function ValidarUsuario() {
    var nick = readCookie("nickname");
    xMLHttpRequest.open("Get", "../ValidarUsuario?nick=" + nick + "&sessionid=" + readCookie("uuid") + "&url=" + window.location.pathname, true);
    xMLHttpRequest.onreadystatechange = function() {
        if (xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
            if (xMLHttpRequest.responseText == "null") {
                document.getElementById("header-nickname-txt").innerHTML = nick;
                initWebSocket();
            } else {
                window.location = "inicio.jsp";
            }
            console.log(xMLHttpRequest.responseText);
        }
    };
    xMLHttpRequest.send(null);
}

function initWebSocket() {
    if ("WebSocket" in window) {
        if (webSocket == null) {
            var url = "ws://localhost:8080/JuegoDeCartas/webSocket/rooms/" + readCookie("room") + "/" + readCookie("type_game") + "/" + readCookie("nickname");
            webSocket = new WebSocket(url);
        } else {
            alert("Has entrado en la sala de chat ...");
        }

        webSocket.onopen = function() {
            webSocket.send("message@Se ha conectado!");
            webSocket.send("nickname@<span style='z-index: 1;'>" + readCookie("nickname") + "</span>");
            document.getElementById("nickname1").innerHTML = "<span style='z-index: 1;'>" + readCookie("nickname") + "</span>";
        };

        webSocket.onmessage = function(evt) {
            if (evt.data != "null") {
                var ident = evt.data.split("@");
                switch (ident[0]) {
                    case 'nickname':
                        document.getElementById("nickname2").innerHTML = ident[1];
                        webSocket.send("nickname@<span style='z-index: 1;'>" + readCookie("nickname") + "</span>");
                        break;
                    case 'leave':
                        alert(ident[1] + " se ha desconectado");
                        window.location = "lobby.jsp";
                        break;
                    default:
                        var msg_board = document.getElementById("chat-body");
                        var received_msg = evt.data;
                        var old_msg = msg_board.innerHTML;
                        msg_board.innerHTML = old_msg + received_msg;
                        msg_board.scrollTop = msg_board.scrollHeight;
                        break;
                }
            } else {
                window.location = "inicio.jsp";
            }
        };

        webSocket.onclose = function() {
            webSocket = null;
            alert("El oponente se ha desconectado.");
            window.location = "lobby.jsp";
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
        webSocket.send("message@" + input_msg);
        document.getElementById("input_msg").value = "";
    } else {
        alert("Estás desconectado, vuelve a ingresar a la sala de chat ...");
    }
};

window.addEventListener('keydown', (event) => {
    if (event.code === 'Enter') {
        send_msg();
    }
});


// const observer = new MutationObserver((mutationList) => {
//     mutationList.forEach((mutation) => {
//         if (aux.innerHTML != cards) {
//             aux.innerHTML = cards;
//             VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
//                 max: 25,
//                 speed: 400,
//             });
//             cards = aux.innerHTML;
//         }
//     })
// });
// const equipos = document.querySelector('.row.card-container');
// const observerOptions = {
//     attributes: false,
//     childList: true,
//     subtree: true,
//     characterData: false,
//     attributeOldValue: false,
//     characterDataOldValue: false
// };
// observer.observe(equipos, observerOptions);



// $.get('../Prueba', function(data) {
//     var aux = document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.card-container");
//     aux.innerHTML = data;
//     cards = data;
//     VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
//         max: 25,
//         speed: 400,
//     });
//     const observer = new MutationObserver((mutationList) => {
//         mutationList.forEach((mutation) => {
//             if (aux.innerHTML != cards) {
//                 aux.innerHTML = cards;
//                 VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
//                     max: 25,
//                     speed: 400,
//                 });
//                 cards = aux.innerHTML;
//             }
//         })
//     });
//     const equipos = document.querySelector('.row.card-container');
//     const observerOptions = {
//         attributes: false,
//         childList: true,
//         subtree: true,
//         characterData: true,
//         attributeOldValue: false,
//         characterDataOldValue: false
//     };
//     observer.observe(equipos, observerOptions);
// });