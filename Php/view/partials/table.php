<div class="overflow-y-auto h-[310px] bg-white mt-6">
  <table class="table table-zebra">
    <!-- head -->
    <thead>
      <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Evento</th>
        <th>Ação</th>
      </tr>
    </thead>
    <tbody>
        <?php if (!empty($inscricoes) && is_array($inscricoes)): ?>
            <?php foreach($inscricoes as $inscricao): ?>
                <tr>
                    <td><?=$inscricao['user_nome']?></td>
                    <td><?=$inscricao['email']?></td>
                    <td><?=$inscricao['nome']?></td>
                    <td>
                        <form action="validar" method="post">
                            <input name="id" value="<?=$inscricao['id']?>" type="hidden">
                            <button type="submit" class="btn btn-neutral">Validar</button>
                        </form>
                    </td>
                </tr>
        <?php endforeach; ?>
        <?php else: ?>
           <tr>
             <p class="col-span-full text-center text-gray-500 text-lg">Nenhuma inscrição encontrada</p>
           </tr>
        <?php endif; ?>
    </tbody>
  </table>
</div>

