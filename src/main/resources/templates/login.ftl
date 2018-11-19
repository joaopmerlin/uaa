<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <style>
        body {
            background-color: #F5F5F5;
        }

        .form-login {
            width: 300px;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .20);
            position: absolute;
            left: calc(50% - 150px);
            top: 30%;
        }

        .form-control {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="form-login">
    <form action="/login" method="post">

        <input name="username" class="form-control" type="text" placeholder="Usuário" required/>
        <input name="password" class="form-control" type="password" placeholder="Senha" required/>

        <label>
            <input name="remember-me" type="checkbox" checked/>
            Lembrar
        </label>

        <button type="submit" class="btn btn-success btn-block">Entrar</button>

        <a class="btn btn-primary btn-block" href="/login/facebook">Facebook</a>
        <a class="btn btn-danger btn-block" href="/login/github">Github</a>
    </form>
</div>

</body>
</html>