<!DOCTYPE html>
<html>

<head>
    <title>Inicio</title>
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../bootstrap-5.1.3-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../css/core.css">
    <link rel="stylesheet" href="../css/boxicons-2.1.2/css/boxicons.min.css">
    <script src="../bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="../js/Inicio.js"></script>
</head>

<body>
    <div class="container-fluid">
        <div class="row row-inicio" style="height: 100%;">
            <div class="col-md-4 div-inicio" style="padding: 0;">
                <div class="row" style="height: 100px;justify-content: center;align-items: center;">
                    <span style="color:white;width: fit-content;font-family: 'Patron',sans-serif;">Listo pARA jugAR?</span>
                </div>
                <div class="row" style="height: 400px;display: flex;align-items: center;">
                    <div class="col-md-12">
                        <div class="row">
                            <span style="color:white;">Usuario</span>
                            <center><input id="user" type="text" autocomplete="off" name="user" maxlength="10" class="input-custom" required/></center>
                        </div>
                        <div class="row" style="justify-content: center">
                            <button style="margin-top: 15%;" id="btn_submit" type="submit" class="btn btn-primary btn-grad" onclick = "Validar()">Iniciar</button>
                            <center id="aviso" style='padding-top: 10px;color:red' hidden><i class='bx bxs-error-alt' style="margin-right: 15px;"></i><span id="span_aviso"></span></center>
                        </div>
                    </div>
                </div>
                <div class="row" style="height: 100px;align-items: center">
                    <center><img src="../img/Imagotipo_Claro.png" alt="Logo" style="height: 85px;"></center>
                </div>
            </div>
        </div>
    </div>
</body>

</html>