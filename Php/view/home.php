<?php if(auth()["role"] == "admin"):?>
    <?php
        $controller = "dashboard";
        require "view/partials/secaoCards.php";
    ?>
<?php else:?>
    <h1 class="text-2xl font-bold mb-4">Eventos diponível para se inscrever</h1>
    <?php
        $controller = "dashboard";
        require "view/partials/secaoCards.php";
    ?>
<?php endif?>