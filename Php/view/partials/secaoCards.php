<div class="bg-blue-950 p-5 mb-10">
    <form class="flex items-center gap-2 max-w-screen-md mx-auto" action="<?=$controller?>">
        <fieldset class="fieldset flex-1">
            <input type="text" class="input w-full -z-0" name="query" placeholder="Pesquisar" />
        </fieldset>
        <button class="btn btn-primary">Pesquisar</button>
    </form>
</div>  

<p class="text-3xl text-center font-bold">Eventos e Palestras</p>

<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 p-10 max-w-screen-xl mx-auto gap-10 mb-20">
    <?php if (!empty($eventos) && is_array($eventos)): ?>
        <?php foreach($eventos as $evento): ?>
            <div class="snap-start bg-white hover:-translate-y-5 transition duration-500 shadow-md rounded-lg p-4">
                <img src="logoAlfa.png" class="rounded-md mb-4 h-[200px] object-contain" alt="Card 1">
                <h3 class="font-semibold text-lg"><?= $evento['nome'] ?></h3>
                <p class="text-sm text-gray-600"><?= $evento['descricao'] ?></p>
                <p>Data: 20/30/2024</p>
                <p>Localização: Rua 2</p>
                <?php if($controller == "dashboard" || $controller == "eventos"):?>
                    <form action="inscrever" method="post">
                        <input name="id_evento" value="<?=$evento["id"]?>" type="hidden">
                        <button type="submit" class="btn btn-neutral mt-5">Se Cadastrar</button>
                    </form>
                <?php endif;?>
            </div>
        <?php endforeach; ?>
    <?php else: ?>
        <p class="col-span-full text-center text-gray-500 text-lg">Nenhum evento encontrado.</p>
    <?php endif; ?>
</div>
