package hackatton.service;

import hackatton.dao.InscricaoDao;
import hackatton.model.Evento;

import java.util.List;

public class InscricaoService {

    private final InscricaoDao dao = new InscricaoDao();
    private final EventoService eventoService = new EventoService(); // ← Aqui estava faltando isso

    public List<String[]> listarAlunosPorEvento(Long idEvento) {
        return dao.listarAlunosPorEvento(idEvento);
    }

    public List<Evento> listarEventos() {
        return eventoService.listarEventos(); // ← Agora está certo
    }
}
