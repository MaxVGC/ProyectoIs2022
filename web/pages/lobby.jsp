<%-- 
    Document   : lobby
    Created on : 9/04/2022, 05:35:36 PM
    Author     : carlo
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Lobby</title>
            <link rel="icon" href="../img/favicon.ico" type="image/x-icon">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <link rel="stylesheet" href="../bootstrap-5.1.3-dist/css/bootstrap.min.css" />
            <link rel="stylesheet" href="../css/lobby.css">
            <script src="https://kit.fontawesome.com/fbb5fada5a.js" crossorigin="anonymous"></script>
            <script src="../bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
            <script src="../js/jquery-3.6.0.min.js"></script>
            <script src="../js/Lobby.js" async></script>
        </head>

        <body>
            <div class="container-fluid">
                <div class="row" style="height: 100%">
                    <div class="col-md-3 div-chat-lobby" style="padding: 0;backdrop-filter: blur(20px);">
                        <div class="row header-info">
                            <div class="header-img">
                                <div class="img-border">
                                    <div id="status"></div>
                                    <img src="../img/icon.jpg" alt="Img profile">
                                </div>
                            </div>
                            <div id="header-nickname">
                                <span style="color: white;" id="header-nickname-txt"></span>
                            </div>
                        </div>
                        <div id="chat-body" class="row">

                        </div>
                        <div class="row" id="input-chat">
                            <input id="input_msg" type="text" autocomplete="off" name="msg" class="input-custom" required/>
                            <div id="send-btn" class="send-btn">
                                <i class="fa-solid fa-paper-plane"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9 div-main-lobby" style="padding: 0%;">
                        <div class="row" style="height: 100%;">
                            <div class="col-md-12 main-rooms" style="backdrop-filter: blur(10px);">
                                <div class="row">
                                    <h2 style="color: white;">Salas disponibles</h2>
                                </div>
                                <div id="div-contains-room-list" class="row rooms">
                                    <div id="room-create">
                                        <i class="fa-solid fa-plus" style="font-size:50px;"></i>
                                        <div id="span">
                                            <span style="color: white;">Crear sala</span>
                                        </div>
                                    </div>

                                </div>
                                <div id="crear-sala">
                                    <div class="row title-crear">
                                        <div class="title">
                                            <h4>Crea tu sala</h4>
                                        </div>
                                        <div id="close-crear">
                                            <i class="fa-solid fa-xmark"></i>
                                        </div>
                                    </div>
                                    <div class="row name-sala">
                                        <span class="input-group-text" id="basic-addon2">Nombre de sala</span>
                                        <input id="name_crear_sala" type="text" class="form-control" placeholder="Ingrese aqui" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    </div>
                                    <div class="row elegir-juego">
                                        <label class="input-group-text" for="select_crear_sala">Tipo de juego</label>
                                        <select class="form-select" id="select_crear_sala">
                                          <option selected>Escoge...</option>
                                          <option value="Card-Jitsu">Card-Jitsu</option>
                                        </select>
                                    </div>
                                    <div class="row" style="justify-content: center">
                                        <button id="btn_crear_sala" type="submit" class="btn btn-primary btn-grad" onclick="Crear_sala()">Crear</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>