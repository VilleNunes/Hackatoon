 <div id="sobre" class="max-w-screen-xl mt-20 mx-auto p-10 grid grid-cols-1 md:grid-cols-2 items-center gap-10">
    <!-- Texto -->
    <div class="opacity-0 translate-y-10 transition-all duration-700 ease-in-out" data-animate>
        <h1 class="text-3xl font-bold mb-5">Sobre a UniAlfa</h1>
        <p>
            A UniAlfa é uma instituição de ensino superior comprometida com a
            excelência acadêmica e a formação de profissionais capacitados para
            enfrentar os desafios do mercado de trabalho. Fundada em 2005, nossa
            missão é proporcionar uma educação de qualidade que prepare nossos alunos
            para se tornarem líderes em suas áreas de atuação. Oferecemos uma ampla gama
            de cursos de graduação e pós-graduação, todos ministrados por um corpo docente
            altamente qualificado. Além disso, nossa infraestrutura moderna e os recursos
            educacionais avançados garantem um ambiente de aprendizado estimulante e enriquecedor.
            Estamos localizados em um campus vibrante, equipado com laboratórios de última geração,
            bibliotecas bem abastecidas e áreas de convivência para promover a integração e o
            desenvolvimento pessoal dos nossos alunos.
        </p>
    </div>

    <!-- Imagem -->
    <div class="opacity-0 translate-y-10 transition-all duration-700 delay-200 ease-in-out" data-animate>
        <img class="rounded-2xl hover:scale-110 duration-500" src="../imagens/alfa.jpg" alt="Sobre a UniAlfa">
    </div>
</div>

    <!-- Script de animação por rolagem -->
<script>
    const animateElements = document.querySelectorAll('[data-animate]');

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('opacity-100', 'translate-y-0');
                entry.target.classList.remove('opacity-0', 'translate-y-10');
            } else {
                // Reverte para animar novamente ao rolar
                entry.target.classList.remove('opacity-100', 'translate-y-0');
                entry.target.classList.add('opacity-0', 'translate-y-10');
            }
        });
    }, {
        threshold: 0.10,
    });

    animateElements.forEach(el => observer.observe(el));
</script>
