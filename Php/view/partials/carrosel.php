<div class="w-full mx-auto pt-20">
    <div class="relative overflow-hidden rounded-lg shadow-lg">
        <!-- Botões -->
        <button id="prev"
            class="absolute left-4 top-1/2 -translate-y-1/2  bg-white/70 hover:bg-white p-2 rounded-full shadow">
            ❮
        </button>
        <button id="next"
            class="absolute right-4 top-1/2 -translate-y-1/2  bg-white/70 hover:bg-white p-2 rounded-full shadow">
            ❯
        </button>

        <!-- Slides -->
        <div id="slider" class="flex transition-transform duration-500 ease-in-out">
            <img src="../imagens/banner1.jpg" class="w-full flex-shrink-0 object-cover" alt="Natureza" />
            <img src="../imagens/banner2.jpg" class="w-full flex-shrink-0 object-cover" alt="Cidade" />
            <img src="../imagens/banner3.jpg" class="w-full flex-shrink-0 object-cover" alt="Tecnologia" />
            <img src="../imagens/banner1.jpg" class="w-full flex-shrink-0 object-cover" alt="Oceano" />
        </div>
    </div>
</div>
<div class="bg-black p-1"></div>
<script>
    const slider = document.getElementById("slider");
    const prev = document.getElementById("prev");
    const next = document.getElementById("next");
    const slides = slider.children;
    let index = 0;

    function updateSlider() {
        slider.style.transform = `translateX(-${index * 100}%)`;
    }

    prev.addEventListener("click", () => {
        index = (index === 0) ? slides.length - 1 : index - 1;
        updateSlider();
    });

    next.addEventListener("click", () => {
        index = (index + 1) % slides.length;
        updateSlider();
    });

    // Auto play
    let autoplay = setInterval(() => {
        index = (index + 1) % slides.length;
        updateSlider();
    }, 5000);

    // Pausar autoplay no hover
    slider.parentElement.addEventListener("mouseenter", () => clearInterval(autoplay));
    slider.parentElement.addEventListener("mouseleave", () => {
        autoplay = setInterval(() => {
            index = (index + 1) % slides.length;
            updateSlider();
        }, 5000);
    });
</script>