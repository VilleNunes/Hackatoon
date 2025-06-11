package hackatton.service;

import hackatton.dao.EventoDao;
import hackatton.model.Evento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoService {

    public void salvarBD(Evento evento) {
        var dao = new EventoDao();
        dao.salvar(evento);
    }

    public List<Evento> listarBD() {
        List<Evento> eventos = new ArrayList<>();
        var dao = new EventoDao();

        dao.listar().forEach(object -> eventos.add((Evento) object));
        return eventos;
    }

    public void salvar(Evento evento) {
        var arquivo = new File(System.getProperty("user.dir"), "\\eventos.txt");
        writerFile(evento.toString(), arquivo.toString());
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
