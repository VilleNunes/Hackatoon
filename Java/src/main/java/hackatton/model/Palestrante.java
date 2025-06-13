package hackatton.model;

public class Palestrante {

    private Long id;
    private String nome;
    private String minicurriculo;
    private String foto;

    public Palestrante() {}

    public Palestrante(Long id, String nome, String minicurriculo, String foto) {
        this.id = id;
        this.nome = nome;
        this.minicurriculo = minicurriculo;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMiniCurriculo() {
        return minicurriculo;
    }

    public void setMinicurriculo(String minicurriculo) {
        this.minicurriculo = minicurriculo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Palestrante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", minicurriculo='" + minicurriculo + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}