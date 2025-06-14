<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <title>UniAlfa</title>
</head>

<body class="h-screen   md:grid flex  grid-cols-2">
    <?php require "view/partials/notificacao.php" ?>
    <div class="bg-blue-800 md:block hidden">
        <span class="text-white">Faculdade UniAlfa</span>
    </div>
     <div class="flex items-center mx-auto">
          <?php
        require  "view/{$view}.php"
    ?>
    </div>
  
</body>

</html>