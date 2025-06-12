package hackatton.model;

public class Evento {

    private Long id;
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private Long idPalestrante;
    private Long idCurso;
    private String localizacao;

    public Evento() {}

    public Evento(Long id, String titulo, String descricao, String dataInicio, String dataFim,
                  Long idPalestrante, Long idCurso, String localizacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idPalestrante = idPalestrante;
        this.idCurso = idCurso;
        this.localizacao = localizacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdPalestrante() {
        return idPalestrante;
    }

    public void setIdPalestrante(Long idPalestrante) {
        this.idPalestrante = idPalestrante;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", idPalestrante=" + idPalestrante +
                ", idCurso=" + idCurso +
                ", localização=" + localizacao +
                '}';
    }
}
