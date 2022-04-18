<%-- 
    Document   : lobby
    Created on : 9/04/2022, 05:35:36 PM
    Author     : carlo
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <title></title>
            <link rel="icon" href="../img/favicon.ico" type="image/x-icon">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <link rel="stylesheet" href="../bootstrap-5.1.3-dist/css/bootstrap.min.css" />
            <link rel="stylesheet" href="../css/cardjitsu.css">
            <link rel="stylesheet" href="../css/cards_cardjitsu.css">
            <script src="https://kit.fontawesome.com/fbb5fada5a.js" crossorigin="anonymous"></script>
            <script src="../bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
            <script type="text/javascript" src="../js/vanilla-tilt.js"></script>
            <script src="../js/jquery-3.6.0.min.js"></script>
            <script src="../js/Cardjitsu.js" async></script>
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
                        <div class="game-container">
                            <div class="row main-container">
                                <div class="header-game">
                                    <div class="timer">
                                        <span>--</span>
                                    </div>
                                    <div class="nicknames">
                                        <div id="nickname1">
                                            <span style='z-index: 1;'>Esperando oponente</span>
                                        </div>
                                        <div id="nickname2">
                                            <span style='z-index: 1;'>Esperando oponente</span>
                                        </div>
                                    </div>
                                </div>
                                <div id="me-points">
                                    <div class="points fuego">

                                    </div>
                                    <div class="points agua">

                                    </div>
                                    <div class="points hielo">

                                    </div>
                                </div>
                                <div id="enemy-points">
                                    <div class="points fuego">

                                    </div>
                                    <div class="points agua">

                                    </div>
                                    <div class="points hielo">

                                    </div>
                                </div>
                                <div id="container-my-card">

                                </div>
                                <div id="container-enemy-card">

                                </div>
                            </div>
                            <div class="row card-container">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>

        </html>