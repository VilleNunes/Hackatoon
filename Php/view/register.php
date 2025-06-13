
<div class="flex items-center bg-white justify-between"> 
    <form class="w-[400px] mx-auto flex flex-col p-10 shadow-black/50 shadow-2xl" method="post" action="register">
        <img class="w-[230px] mx-auto mb-5" src="../../imagens/logoAlfa.png" alt="">
        <h1 class="font-bold text-3xl mb-5">Registrar</h1>
        <label class="label">Nome</label>
        <input required type="text" name="nome" class="input w-full" placeholder="Nome" />
        <label class="label">Email</label>
        <input required type="email" name="email" class="input w-full" placeholder="Email" />
        <label class="label">Senha</label>
        <input required type="password" name="password" class="input w-full" placeholder="Senha" />
        <div><a href="login" class="link link-hover">Já possui conta?</a></div>
        <button type="submit" class="btn btn-neutral mt-4">Registrar - Se</button>
    </form>
</div>