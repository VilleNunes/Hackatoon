<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <title>UniAlfa</title>
    <style>
        .scrollbar-hide {
            -ms-overflow-style: none;
            scrollbar-width: none;
        }

        .scrollbar-hide::-webkit-scrollbar {
            display: none;
        }
    </style>
</head>

<body class="bg-gray-200/80">
    <?php require "view/partials/notificacao.php" ?>
    <?php require "view/partials/header.php" ?>
    <?php require "view/partials/carrosel.php" ?>
     <?php
        require  "view/{$view}.php"
     ?>
    <?php require "view/partials/footer.php" ?>
    <?php require "view/partials/faixa.php" ?>
</body>

</html>