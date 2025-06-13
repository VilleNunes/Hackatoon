package hackatton.service;

import hackatton.dao.DaoInterface;
import hackatton.dao.EventoDao;
import hackatton.model.Evento;

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

        String result = "";
        for (Object evento : dao.listar()) {
            result = result + "\n" + evento;
        }

        return result;
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

    private void writerFile(String conteudo, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.newLine();
            writer.write(conteudo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
