<div class="bg-blue-950 p-5 mb-10">
    <form class="flex items-center gap-2 max-w-screen-md mx-auto" action="<?= $controller ?>">
        <fieldset class="fieldset flex-1">
            <input type="text" class="input w-full -z-0" name="query" placeholder="Pesquisar" />
        </fieldset>
        <button class="btn btn-primary">Pesquisar</button>
    </form>
</div>  

<p class="text-3xl text-center font-bold">Eventos e Palestras</p>

<div class="grid grid-cols-1 space-y-4 md:grid-cols-2 lg:grid-cols-2 xl:grid-cols-3 p-10 max-w-screen-xl mx-auto gap-5 mb-20">
    <?php if (!empty($eventos) && is_array($eventos)): ?>
        <?php foreach($eventos as $evento): ?>
            <div class="bg-white hover:-translate-y-5 transition duration-500 shadow-md rounded-lg">
                <img src="../../imagens/banner1.jpg" class="rounded-md mx-auto mb-4 h-[200px] object-cover ">
                <div class="p-5">
                    <h3 class="font-semibold text-lg"><?= $evento['titulo'] ?></h3>
                    <p class="text-sm text-gray-600"><?= $evento['descricao'] ?></p>
                    <p class="text-sm text-gray-600">Data de Início <?= formatarData($evento['data_inicio']) ?></p>
                    <p class="text-sm text-gray-600">Até <?= formatarData($evento['data_fim']) ?></p>
                    <p class="text-sm text-gray-600">Localização <?= $evento['localizacao'] ?></p>

                    <?php if ($controller == "dashboard" || $controller == "eventos"): ?>
                        <form action="inscrever" method="post">
                            <input name="id_evento" value="<?= $evento["id"] ?>" type="hidden">
                            <button type="submit" class="btn btn-neutral mt-5">Se Cadastrar</button>
                        </form>
                    <?php endif; ?>

                    <?php if (!empty($evento["validado"]) && $evento["validado"] == 1): ?>
                        <form method="post" action="gerar-certificado" target="_blank">
                            <input name="evento" value="<?=$evento['titulo']?>" type="hidden">
                            <button type="submit" class="btn btn-neutral mt-5">Ver Certificado</button>
                        </form>
                    <?php endif; ?>

                    <?php if (isset($evento["validado"]) && $evento["validado"] == 0 && $controller == "minhas-inscricoes"): ?>
                       <form action="deletar-inscricao" method="post">
                         <input name="id_inscricao" value="<?= $evento["id"] ?>" type="hidden">
                         <button class="btn mt-5 bg-red-700 text-white">Deletar Inscrição</button>
                       </form>
                    <?php endif; ?>
                </div>
            </div>
        <?php endforeach; ?>
    <?php else: ?>
        <p class="col-span-full text-center text-gray-500 text-lg">Nenhum evento encontrado.</p>
    <?php endif; ?>
</div>
