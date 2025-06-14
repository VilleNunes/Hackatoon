<header class="bg-white shadow-md flex justify-between items-center px-6 py-4 sticky top-0 z-10">

    <button id="menu-btn" class="text-gray-700 focus:outline-none">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"
            stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M4 6h16M4 12h16M4 18h16" />
        </svg>
    </button>

    <div class="text-lg hidden md:block font-semibold" >Olá, <?=auth()['nome']?></div>
    <a href="/logout" id="logout-btn" class="font-bold">
        Logout
    </a href="/logout">
</header>

<script>
    const sidebar = document.getElementById('sidebar');
    const menuBtn = document.getElementById('menu-btn');
    const overlay = document.getElementById('overlay');
    const content = document.getElementById('content');

    function openMenu() {
        sidebar.classList.remove('-translate-x-full');
        sidebar.classList.add('translate-x-0');
        overlay.classList.remove('hidden');
        content.classList.remove('ml-0');
        content.classList.add('ml-64');
    }

    function closeMenu() {
        sidebar.classList.add('-translate-x-full');
        sidebar.classList.remove('translate-x-0');
        overlay.classList.add('hidden');
        content.classList.remove('ml-64');
        content.classList.add('ml-0');
    }

    function isMobile() {
        return window.innerWidth < 1000;
    }

    function initMenu() {
        if (isMobile()) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    menuBtn.addEventListener('click', () => {
        const isClosed = sidebar.classList.contains('-translate-x-full');
        if (isClosed) {
            openMenu();
        } else {
            closeMenu();
        }
    });

    overlay.addEventListener('click', closeMenu);

    window.addEventListener('resize', initMenu);

    initMenu();
</script>