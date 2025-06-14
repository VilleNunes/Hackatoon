package hackatton.service;

import hackatton.dao.CursoDao;
import hackatton.dao.EventoDao;
import hackatton.dao.PalestranteDao;
import hackatton.model.Curso;
import hackatton.model.Evento;
import hackatton.model.Palestrante;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoService {

    public boolean salvarBD(Evento evento) {
        var dao = new EventoDao();
        return dao.salvar(evento);
    }

    public List<Evento> listarBD() {
        List<Evento> eventos = new ArrayList<>();
        var dao = new EventoDao();

        dao.listar().forEach(object -> eventos.add((Evento) object));
        return eventos;
    }

    public boolean atualizarBD(Evento evento) {
        var dao = new EventoDao();
        return dao.atualizar(evento);
    }

    public Boolean deletarEvento(Long id) {
        if (id == null) return false;
        var dao = new EventoDao();
        return dao.deletar(id);
    }

    public String listar() {
        var dao = new EventoDao();

        StringBuilder result = new StringBuilder();
        for (Object evento : dao.listar()) {
            result.append("\n").append(evento);
        }

        return result.toString();
    }
    private List<String> readerFile(String nomeArquivo) {
        List<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }


    public List<Curso> listarCursos() {
        CursoDao cursoDao = new CursoDao();
        List<Curso> cursos = new ArrayList<>();
        cursoDao.listar().forEach(obj -> cursos.add((Curso) obj));
        return cursos;
    }

    public List<Palestrante> listarPalestrantes(){
        PalestranteDao palestranteDao = new PalestranteDao();
        List<Palestrante> palestrantes = new ArrayList<>();
        palestranteDao.listar().forEach(obj -> palestrantes.add((Palestrante) obj));
        return palestrantes;
    }

    public Long getIdCursoPorNome(String nome) {
        return listarCursos().stream()
                .filter(curso -> curso.getNome().equalsIgnoreCase(nome))
                .map(Curso::getId)
                .findFirst()
                .orElse(null);
    }

    public Long getIdPalestrantePorNome(String nome) {
        return listarPalestrantes().stream()
                .filter(palestrante -> palestrante.getNome().equalsIgnoreCase(nome))
                .map(Palestrante::getId)
                .findFirst()
                .orElse(null);
    }

    public String getNomeCursoPorId(Long id) {
        return listarCursos().stream()
                .filter(curso -> curso.getId().equals(id))
                .map(Curso::getNome)
                .findFirst()
                .orElse("");
    }

    public String getNomePalestrantePorId(Long id) {
        return listarPalestrantes().stream()
                .filter(palestrante -> palestrante.getId().equals(id))
                .map(Palestrante::getNome)
                .findFirst()
                .orElse("");
    }
    public List<Evento> listarEventos() {
        EventoDao eventoDao = new EventoDao();
        List<Evento> eventos = new ArrayList<>();
        eventoDao.listar().forEach(obj -> eventos.add((Evento) obj));
        return eventos;
    }


}
