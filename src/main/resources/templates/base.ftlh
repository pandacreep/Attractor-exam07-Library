<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header_flex">
            <div class="header__menu">
                <a href="/" class="menu__item">Main</a>
                <a href="/taken" class="menu__item">Taken books</a>
                <a href="/add" class="menu__item">Add book</a>
                <a href="/profile" class="menu__item">Profile</a>
                <a href="/login" class="menu__item">Login</a>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <#nested/>
</div>
</body>
</html>
</#macro>