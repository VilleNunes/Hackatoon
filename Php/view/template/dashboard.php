<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Alfa</title>
   
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>


<body class="bg-gray-100 flex h-screen overflow-hidden">
    <?php require "./view/partials/sidebar.php"?>
    <div id="overlay" class="fixed inset-0 bg-black bg-opacity-50 hidden z-20 md:hidden"></div>

    <div id="content" class="flex flex-col flex-grow ml-64 w-full transition-all duration-300">
        <?php require "./view/partials/navTop.php"?>
        <main class="flex-grow p-6 overflow-auto">
            <?php
                require "view/partials/notificacao.php";
                require  "view/{$view}.php"
            ?>
        </main>
    </div>
</body>

</html>