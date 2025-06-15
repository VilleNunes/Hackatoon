<div class="md:flex max-w-screen-xl mx-auto p-5 py-30">
    
    <div class="md:w-1/3">
      <img src="../../<?=$evento['imagem']?>" alt="Imagem do Evento" class="h-full rounded-md w-full object-cover">
    </div>

    <div class="md:w-2/3 p-10 space-y-4">
  
      <h2 class="text-3xl font-bold text-blue-800"><?=$evento['titulo']?></h2>

      <p class="text-gray-700"><strong>Descrição: <br></strong><?=$evento['descricao']?></p>

      <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between text-sm text-gray-600">
        <p><strong>Local: </strong><?=$evento['localizacao']?></p>
        <p><strong>Início: </strong><?=formatarData($evento["data_inicio"])?></p>
        <p><strong>Fim: </strong><?=formatarData($evento["data_fim"])?></p>
      </div>

      <p class="text-gray-800"><strong>Curso relacionado: </strong><?=$evento["nome"]?></p>

      <div class="flex items-center gap-4 mt-6">
        <img src="<?=$evento['foto']?>" alt="Foto do Palestrante" class="h-25 w-25 rounded-full object-cover border border-gray-300 shadow-sm">
        <div>
          <p class="font-semibold text-gray-800"><?=$evento['nome_palestrante']?></p>
          <p class="text-sm text-gray-500"><?=$evento['minicurriculo']?></p>
        </div>
      </div>

      <form action="inscrever" method="post" class="pt-4">
        <input type="hidden" name="id_evento" value="<?=$evento['id']?>">
        <button type="submit" class="bg-blue-800 text-white px-6 py-2 rounded-md hover:bg-blue-900 transition">
          Inscrever-se
        </button>
      </form>
    </div>
</div>