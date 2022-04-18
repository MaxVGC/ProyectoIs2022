var xMLHttpRequest = new XMLHttpRequest();
var send_btn = document.getElementById("send-btn");
send_btn.addEventListener("click", send_msg);
var countDown = true;
var webSocket;


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
        }
    };
    xMLHttpRequest.send(null);
}

function initWebSocket() {
    var cards;
    var flag = 0;
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
                    case 'updateMazo':
                        setTimeout(function() {
                            var aux = document.getElementById("container-enemy-card");
                            aux.style.right = "-50%";
                            aux.style.opacity = "0";
                            var aux = document.getElementById("container-my-card");
                            aux.style.left = "-50%";
                            aux.style.opacity = "0";
                            var aux = document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.card-container");
                            aux.style.top = "calc(100% - 255px)";
                            aux.style.opacity = "1";
                            aux.innerHTML = evt.data.substring(11, evt.data.length);
                            cards = evt.data.substring(11, evt.data.length);
                            aux.addEventListener("click", sendAction);
                            VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
                                max: 25,
                                speed: 400,
                            });
                            const observer = new MutationObserver((mutationList) => {
                                mutationList.forEach((mutation) => {
                                    if (aux.innerHTML != cards) {
                                        aux.innerHTML = cards;
                                        VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
                                            max: 25,
                                            speed: 400,
                                        });
                                        cards = aux.innerHTML;
                                    }
                                })
                            });
                            const equipos = document.querySelector('.row.card-container');
                            const observerOptions = {
                                attributes: false,
                                childList: true,
                                subtree: true,
                                characterData: true,
                                attributeOldValue: false,
                                characterDataOldValue: false
                            };
                            observer.observe(equipos, observerOptions);
                            startCountDown(30, document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.main-container > div > div.timer"), "waitAction");
                        }, 5000);
                        break;
                    case 'result':
                        if (ident[1] == "me") {
                            if (ident[3] == "fuego") {
                                var aux = document.querySelector("#me-points > div.points.fuego");
                                aux.innerHTML = aux.innerHTML + "<div class='row-point " + ident[2] + "'><img src='../img/fuego100x100.png'></div>";
                            } else if (ident[3] == "agua") {
                                var aux = document.querySelector("#me-points > div.points.agua");
                                aux.innerHTML = aux.innerHTML + "<div class='row-point " + ident[2] + "'><img src='../img/Agua100x100.png'></div>";
                            } else if (ident[3] == "hielo") {
                                var aux = document.querySelector("#me-points > div.points.hielo");
                                aux.innerHTML = aux.innerHTML + "<div class='row-point " + ident[2] + "'><img src='../img/Hielo100x100.png'></div>";
                            }
                        } else if (ident[1] == "enemy") {
                            if (ident[3] == "fuego") {
                                var aux = document.querySelector("#enemy-points > div.points.fuego");
                                aux.innerHTML = aux.innerHTML + "<div class='row-point " + ident[2] + "'><img src='../img/fuego100x100.png'></div>";
                            } else if (ident[3] == "agua") {
                                var aux = document.querySelector("#enemy-points > div.points.agua");
                                aux.innerHTML = aux.innerHTML + "<div class='row-point " + ident[2] + "'><img src='../img/Agua100x100.png'></div>";
                            } else if (ident[3] == "hielo") {
                                var aux = document.querySelector("#enemy-points > div.points.hielo");
                                aux.innerHTML = aux.innerHTML + "<div class='row-point " + ident[2] + "'><img src='../img/Hielo100x100.png'></div>";
                            }
                        }
                        break;
                    case 'turncard':
                        var aux = document.getElementById("container-enemy-card");
                        countDown = false;
                        setTimeout(function() {
                            aux.style.opacity = "0";
                            setTimeout(function() {
                                aux.innerHTML = ident[1];
                                aux.style.opacity = "1";
                            }, 500);
                        }, 2000);
                        break;
                    case 'enemycard':
                        var aux = document.getElementById("container-enemy-card");
                        aux.innerHTML = ident[1];
                        aux.style.right = "0";
                        aux.style.opacity = "1";
                        break;
                    case 'mycard':
                        var aux = document.getElementById("container-my-card");
                        aux.innerHTML = ident[1];
                        aux.style.left = "0";
                        aux.style.opacity = "1";
                        break;
                    case 'getMazo':
                        var aux = document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.card-container");
                        aux.style.top = "calc(100% - 255px)";
                        aux.style.opacity = "1";
                        aux.innerHTML = evt.data.substring(8, evt.data.length);
                        cards = evt.data.substring(8, evt.data.length);
                        aux.addEventListener("click", sendAction);
                        VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
                            max: 25,
                            speed: 400,
                        });
                        const observer = new MutationObserver((mutationList) => {
                            mutationList.forEach((mutation) => {
                                if (aux.innerHTML != cards) {
                                    aux.innerHTML = cards;
                                    VanillaTilt.init(document.querySelectorAll(".card-jitsu"), {
                                        max: 25,
                                        speed: 400,
                                    });
                                    cards = aux.innerHTML;
                                }
                            })
                        });
                        const equipos = document.querySelector('.row.card-container');
                        const observerOptions = {
                            attributes: false,
                            childList: true,
                            subtree: true,
                            characterData: true,
                            attributeOldValue: false,
                            characterDataOldValue: false
                        };
                        observer.observe(equipos, observerOptions);
                        startCountDown(30, document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.main-container > div > div.timer"), "waitAction");
                        break;
                    case 'nickname':
                        document.getElementById("nickname2").innerHTML = ident[1];
                        if (flag == 0) {
                            webSocket.send("nickname@<span style='z-index: 1;'>" + readCookie("nickname") + "</span>");
                            webSocket.send("startCountDown@ready");
                            flag = 1;
                            console.log("flag");
                        }
                        break;
                    case 'startCountDown':
                        startCountDown(10, document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.main-container > div > div.timer"), "countDownInit");
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

function startCountDown(duration, element, type) {
    let secondsRemaining = duration;
    let sec = 0;
    let countInterval = setInterval(function() {
        sec = parseInt(secondsRemaining % 60);
        element.innerHTML = "<span>" + sec + "</span>";
        secondsRemaining = secondsRemaining - 1;
        if (countDown) {
            if (secondsRemaining < 0) {
                console.log(countDown);
                if (type == "countDownInit") {
                    clearInterval(countInterval);
                    webSocket.send("getMazo@null");
                } else if (type == "waitAction") {
                    var aux = document.querySelector("body > div > div > div.col-md-9.div-main-lobby > div > div.row.card-container");
                    aux.style.top = "100%";
                    aux.style.opacity = "0";
                    webSocket.send("action@" + Math.floor(Math.random() * 5));
                    clearInterval(countInterval);
                }
            }
        } else {
            clearInterval(countInterval);
            countDown = true;
            element.innerHTML = "<span>--</span>";
        }
    }, 1000);
}

function sendAction(e) {
    this.style.top = "100%";
    this.style.opacity = "0";
    var aux = e.target;
    while (aux.parentNode.outerHTML != this.outerHTML) {
        aux = aux.parentNode;
    }
    var index = 0;
    for (let i = 0; i < this.childNodes.length; i++) {
        if (this.childNodes[i].outerHTML == aux.outerHTML) {
            index = i;
        }
    }
    webSocket.send("action@" + index);
}

function syncDelay(milliseconds) {
    var start = new Date().getTime();
    var end = 0;
    while ((end - start) < milliseconds) {
        end = new Date().getTime();
    }
}

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