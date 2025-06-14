<div class="bg-white shadow-md p-5 fixed top-0 left-0 w-full z-10">
    <header class="max-w-screen-lg mx-auto flex items-center justify-between">
        
       
        <a href="index" class="flex items-center space-x-2">
            <img src="../imagens/logoAlfa.png" alt="Logo da empresa Alfa" class="h-10 w-auto">
        </a>

        <button id="menu-toggle" class="lg:hidden text-gray-800 focus:outline-none" aria-label="Abrir menu">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M4 6h16M4 12h16M4 18h16" />
            </svg>
        </button>

       
        <nav id="nav-menu" class="hidden lg:block absolute lg:static bg-white lg:bg-transparent top-full left-0 w-full lg:w-auto p-5 lg:p-0 shadow-md lg:shadow-none z-20">
            <ul class="flex flex-col lg:flex-row gap-4 lg:gap-6 text-gray-800">
                <li>
                    <a href="index" class="font-semibold hover:underline">Home</a>
                </li>
                <li>
                    <a href="eventos" class="font-semibold hover:underline">Eventos</a>
                </li>
                <li class="lg:hidden">
                    <?php if(auth()): ?>
                        <a href="dashboard" class="text-blue-600 font-medium hover:underline">Painel</a>
                    <?php else: ?>
                        <a href="login" class="text-blue-600 font-medium hover:underline">Login</a>
                    <?php endif; ?>
                </li>
            </ul>
        </nav>

       
        <div class="hidden lg:block">
            <?php if(auth()): ?>
                <a href="dashboard" class="text-blue-600 font-medium hover:underline">Painel</a>
            <?php else: ?>
                <a href="login" class="text-blue-600 font-medium hover:underline">Login</a>
            <?php endif; ?>
        </div>
    </header>
</div>


<script>
    const toggle = document.getElementById('menu-toggle');
    const menu = document.getElementById('nav-menu');

    toggle.addEventListener('click', () => {
        menu.classList.toggle('hidden');
    });
</script>
