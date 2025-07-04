<?php if ($mensagem = flash()->get("error")): ?>
  <div id="notificacao" class="fixed top-5 w-[270px] right-5 bg-red-500 text-white px-4 py-3 rounded shadow-lg transition-opacity duration-500 z-50">
    <?= $mensagem ?>
  </div>

  <script>
    setTimeout(() => {
      const noti = document.getElementById("notificacao");
      if (noti) {
        noti.classList.add("opacity-0");
        setTimeout(() => noti.remove(), 500);
      }
    }, 4000);
  </script>

<?php endif; ?>

<?php if ($mensagem = flash()->get("sucess")): ?>
  <div id="notificacao" class="fixed w-[270px] top-5 right-5 bg-green-600 text-white px-4 py-5 rounded shadow-lg transition-opacity duration-500 z-50">
    <?= $mensagem ?>
  </div>

  <script>
    setTimeout(() => {
      const noti = document.getElementById("notificacao");
      if (noti) {
        noti.classList.add("opacity-0");
        setTimeout(() => noti.remove(), 500);
      }
    }, 4000);
  </script>

<?php endif; ?>