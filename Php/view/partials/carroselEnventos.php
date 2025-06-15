<div class="bg-gray-300/80 py-30">
    <div class="w-full max-w-6xl mx-auto px-4 relative">
        <h2 class="text-2xl font-bold mb-6 text-gray-800">
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
            <div class="snap-start min-w-[350px] max-w-[350px] bg-white shadow-md rounded-lg p-4">
                <img src="../../<?=$evento['imagem']?>" class="rounded-md mb-4 h-[250px] w-full object-cover" alt="">

                <h3 class="font-semibold text-xl text-gray-800 mb-1">
                    <?= $evento['titulo'] ?>
                </h3>

                <p class="text-base text-gray-700 mb-2">
                    Descrição: <?= $evento['descricao'] ?>
                </p>

                <p class="text-sm text-gray-600 mb-1">
                    Início: <?= formatarData($evento['data_inicio']) ?>
                </p>

                <p class="text-sm text-gray-600 mb-1">
                    Término: <?= formatarData($evento['data_fim']) ?>
                </p>

                <p class="text-sm text-gray-600 mb-2">
                    Localização: <?= $evento['localizacao'] ?>
                </p>

                <form action="ver-evento" method="post">
                    <input name="id_evento" value="<?= $evento["id"] ?>" type="hidden">
                    <button type="submit" class="btn btn-neutral mt-5">
                        Saber Mais
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
