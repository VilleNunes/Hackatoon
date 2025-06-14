<?php require "view/partials/head.php" ?>

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