/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var xMLHttpRequest = new XMLHttpRequest();

init();

function init() {
    if (readCookie("uuid") == null) {
        document.cookie = "uuid=" + generateUUID();
    }
}

function readCookie(name) {
    return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + name.replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
}

function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}

function Validar() {
    var nick = document.getElementById("user").value;
    xMLHttpRequest.open("Get", "../ValidarUsuario?nick=" + nick + "&sessionid=" + readCookie("uuid"), true);
    xMLHttpRequest.onreadystatechange = function() {
        if (xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
            if (xMLHttpRequest.responseText === "null") {
                document.cookie = "nickname=" + nick;
                window.location = "lobby.jsp";
            } else {
                document.getElementById("aviso").hidden = false;

                document.getElementById("span_aviso").innerHTML = xMLHttpRequest.responseText;
            }
            console.log(xMLHttpRequest.responseText);
        }
    };
    xMLHttpRequest.send(null);
}