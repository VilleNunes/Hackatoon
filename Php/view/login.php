<div class="flex items-center bg-white justify-between"> 
    <form class="w-[350px] md:w-[400px] mx-auto flex flex-col p-10 shadow-black/50 shadow-2xl" action="login" method="post">
        <img class="w-[230px] mx-auto mb-5" src="../../imagens/logoAlfa.png" alt="">
        <h1 class="font-bold text-3xl mb-5">Login</h1>
        <label class="label">Email</label>
        <input required type="email" name="email" class="input w-full" placeholder="Email" />
        <label  class="label">Senha</label>
        <input required type="password" name="password" class="input w-full" placeholder="Senha" />
        <div><a href="register" class="link link-hover">Ainda não tem cadastro?</a></div>
        <button class="btn btn-neutral mt-4">Login</button>
    </form>
</div>