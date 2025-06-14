<?php require "view/partials/head.php" ?>

<body class="md:grid h-screen flex grid-cols-2 p-2">
    <?php require "view/partials/notificacao.php" ?>
    <div class=" md:block hidden  rounded-lg bg-[url('../../imagens/banner4.jpg')] bg-center bg-cover">
        
    </div>
    <div class="flex items-center mx-auto">
        <?php
            require  "view/{$view}.php"
        ?>
    </div>
  
</body>

</html>