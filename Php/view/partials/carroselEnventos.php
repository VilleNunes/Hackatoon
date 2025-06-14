<div class="bg-gray-300/80 py-">
    <div class="w-full max-w-6xl mx-auto px-4 py-10 relative">
        <h2 class="text-2xl font-bold mb-6 text-gray-800 flex items-center gap-2">
            <!-- Ícone de Confete -->
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-indigo-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.752 11.168l-3.197-2.132a.5.5 0 00-.755.43v3.682a.5.5 0 00.755.43l3.197-2.132a.5.5 0 000-.848z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20.288 12.288a9 9 0 11-13.416 0" />
            </svg>
            Eventos
        </h2>

        <!-- Botões -->
        <button id="scroll-left"
            class="absolute left-0 top-1/2 -translate-y-1/2 z-[0] bg-white shadow-md p-2 rounded-full hover:bg-gray-100">
            ❮
        </button>
        <button id="scroll-right"
            class="absolute right-0 top-1/2 -translate-y-1/2 z-[0] bg-white shadow-md p-2 rounded-full hover:bg-gray-100">
            ❯
        </button>

        <div id="carousel" class="flex gap-4 overflow-x-auto scroll-smooth snap-x snap-mandatory scrollbar-hide px-6">

            <?php foreach($eventos as $evento): ?>
            <div class="snap-start w-[350px] bg-white shadow-md rounded-lg p-4">
                <img src="../../imagens/banner1.jpg" class="rounded-md mb-4 h-[200px] object-cover" alt="">

                <h3 class="font-semibold text-xl text-gray-800 mb-1 flex items-center gap-2">
                    <!-- Ícone de marcador -->
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-blue-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c1.657 0 3-1.343 3-3S13.657 5 12 5s-3 1.343-3 3 1.343 3 3 3z" />
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19.071 4.929A10 10 0 105.929 19.071 10 10 0 0019.071 4.929z" />
                    </svg>
                    <?= $evento['titulo'] ?>
                </h3>

                <p class="text-base text-gray-700 mb-2 flex items-start gap-2">
                    <!-- Ícone de texto -->
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 10H5m14 4H5m14-8H5m14 12H5" />
                    </svg>
                    <?= $evento['descricao'] ?>
                </p>

                <p class="text-sm text-gray-600 mb-1 flex items-center gap-1">
                    <!-- Ícone de calendário -->
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-green-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M8 7V3m8 4V3M5 11h14M5 19h14M5 7h14a2 2 0 012 2v10a2 2 0 01-2 2H5a2 2 0 01-2-2V9a2 2 0 012-2z" />
                    </svg>
                    Início: <?= formatarData($evento['data_inicio']) ?>
                </p>

                <p class="text-sm text-gray-600 mb-1 flex items-center gap-1">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-red-600" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M8 7V3m8 4V3M5 11h14M5 19h14M5 7h14a2 2 0 012 2v10a2 2 0 01-2 2H5a2 2 0 01-2-2V9a2 2 0 012-2z" />
                    </svg>
                    Término: <?= formatarData($evento['data_fim']) ?>
                </p>

                <p class="text-sm text-gray-600 mb-2 flex items-center gap-1">
                    <!-- Ícone de localização -->
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-purple-600" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M12 11c1.657 0 3-1.343 3-3S13.657 5 12 5 9 6.343 9 8s1.343 3 3 3z" />
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M19.071 4.929A10 10 0 105.929 19.071 10 10 0 0019.071 4.929z" />
                    </svg>
                    <?= $evento['localizacao'] ?>
                </p>

                <form action="inscrever" method="post">
                    <input name="id_evento" value="<?= $evento["id"] ?>" type="hidden">
                    <button type="submit" class="btn btn-neutral mt-5 flex items-center gap-2">
                        <!-- Ícone de inscrição -->
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-white" fill="none" viewBox="0 0 24 24"
                            stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 4v16m8-8H4" />
                        </svg>
                        Se Cadastrar
                    </button>
                </form>
            </div>
            <?php endforeach; ?>

        </div>
    </div>
</div>

<script>
    const carousel = document.getElementById("carousel");
    const leftBtn = document.getElementById("scroll-left");
    const rightBtn = document.getElementById("scroll-right");

    const scrollAmount = 300;

    leftBtn.addEventListener("click", () => {
        carousel.scrollBy({ left: -scrollAmount, behavior: "smooth" });
    });

    rightBtn.addEventListener("click", () => {
        carousel.scrollBy({ left: scrollAmount, behavior: "smooth" });
    });
</script>
