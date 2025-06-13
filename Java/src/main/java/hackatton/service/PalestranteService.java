package hackatton.service;

import hackatton.dao.PalestranteDao;
import hackatton.model.Palestrante;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PalestranteService {

    public boolean salvar(Palestrante palestrante) {
        var dao = new PalestranteDao();
        if (palestrante.getId() == null) {
            return dao.salvar(palestrante);
        } else {
            return dao.atualizar(palestrante);
        }
    }

    public List<Palestrante> listar() {
        List<Palestrante> palestrantes = new ArrayList<>();
        var dao = new PalestranteDao();

        dao.listar().forEach(obj -> palestrantes.add((Palestrante) obj));
        return palestrantes;
    }

    public boolean deletar(Long id) {
        var dao = new PalestranteDao();
        return dao.deletar(id);
    }

    public String listarTexto() {
        var dao = new PalestranteDao();

        StringBuilder result = new StringBuilder();
        for (Object palestrante : dao.listar()) {
            result.append("\n").append(palestrante);
        }

        return result.toString();
    }

    public boolean atualizarBD(Palestrante palestrante) {
        var dao = new PalestranteDao();
        return dao.atualizar(palestrante);
    }

    private void escreverArquivo(String conteudo, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.newLine();
            writer.write(conteudo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
