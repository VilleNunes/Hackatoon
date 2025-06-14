<?php $url = str_replace("/", "", $_SERVER['REQUEST_URI'])?>

<aside id="sidebar" class="fixed border-r  inset-y-0 left-0 w-64 bg-white shadow-black/50 shadow-xl flex flex-col
transform translate-x-0 transition-transform duration-300 ease-in-out z-30">
    <div class="p-6 text-xl font-bold mx-auto border-b">
        <img src="../imagens/logoAlfa.png" alt="Logo Alfa" class="h-10 w-auto">
    </div>
    <nav class="flex-grow mt-4 flex flex-col space-y-2 px-4">
       
        <?php if(auth()['role'] == "admin"): ?>
            <a href="admin" class="py-2 px-3 rounded hover:bg-blue-100 <?= $url === 'admin' ? 'bg-blue-300 font-semibold' : '' ?>">Validar Certificados</a>
        <?php else: ?>
            <a href="dashboard" class="py-2 px-3 rounded hover:bg-blue-100 <?= $url === "dashboard" ? 'bg-blue-300 font-semibold' : '' ?>" >Eventos</a>
            <a href="minhas-inscricoes" class="py-2 px-3 rounded hover:bg-blue-100 <?= $url === 'minhas-inscricoes' ? 'bg-blue-300 font-semibold' : '' ?>">Minhas Inscrições</a>
        <?php endif; ?>
        <a href="index" class="py-2 px-3 rounded hover:bg-blue-100">Site</a>
    </nav>
</aside>