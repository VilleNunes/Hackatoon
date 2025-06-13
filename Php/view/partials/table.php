<div class="overflow-y-auto h-[400px] bg-white mt-10">
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
     <?php foreach($eventos as $evento): ?>
      <tr>
        <td><?=$evento['user_nome']?></td>
        <td><?=$evento['nome']?></td>
        <td><?=$evento['email']?></td>
        <td>Red</td>
      </tr>
     <?php endforeach; ?>
    </tbody>
  </table>
</div>