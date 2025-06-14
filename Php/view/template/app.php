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
<a
  href="https://wa.me/5511999999999"
  target="_blank"
  class="fixed bottom-5 right-5 bg-green-500 hover:bg-green-600 text-white rounded-full w-14 h-14 flex items-center justify-center shadow-lg z-50 transition-colors"
  aria-label="WhatsApp"
>
  <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="w-8 h-8" viewBox="0 0 24 24">
    <path d="M20.52 3.48A11.827 11.827 0 0012 0C5.373 0 0 5.373 0 12a11.967 11.967 0 001.774 6.086L0 24l5.934-1.764A11.94 11.94 0 0012 24c6.627 0 12-5.373 12-12a11.87 11.87 0 00-3.48-8.52zM12 21.6a9.555 9.555 0 01-4.84-1.4l-.346-.21-3.524 1.048 1.051-3.43-.224-.357A9.572 9.572 0 012.4 12c0-5.31 4.29-9.6 9.6-9.6 2.56 0 4.955.995 6.755 2.795a9.547 9.547 0 012.845 6.805c0 5.31-4.29 9.6-9.6 9.6zm5.373-7.873c-.293-.147-1.735-.858-2.003-.955-.268-.098-.463-.147-.658.147-.195.293-.753.955-.923 1.15-.17.195-.34.22-.633.074-.293-.147-1.237-.455-2.357-1.453-.872-.777-1.46-1.74-1.63-2.033-.17-.293-.018-.45.128-.596.13-.129.293-.34.44-.51.147-.17.195-.293.293-.488.098-.195.05-.366-.025-.513-.075-.146-.658-1.584-.9-2.175-.236-.57-.48-.49-.658-.5l-.56-.01c-.195 0-.513.074-.783.366-.268.293-1.02.996-1.02 2.428 0 1.43 1.045 2.812 1.19 3.006.146.195 2.06 3.145 4.988 4.41.697.3 1.24.479 1.664.613.7.22 1.335.189 1.84.114.56-.085 1.735-.71 1.98-1.396.244-.687.244-1.275.17-1.395-.074-.122-.268-.195-.56-.34z"/>
  </svg>
</a>

</body>

</html>