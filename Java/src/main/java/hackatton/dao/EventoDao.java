package hackatton.dao;

import hackatton.model.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDao extends Dao implements DaoInterface {

    @Override
    public boolean salvar(Object entity) {
        try {
            var evento = (Evento) entity;

            String sqlInsert = "INSERT INTO evento(titulo, descricao, data_inicio, data_fim, id_palestrante, id_curso, localizacao, imagem) VALUES (?,?,?,?,?,?,?,?)";

            var ps = getConnection().prepareStatement(sqlInsert);
            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getDescricao());
            ps.setString(3, evento.getDataInicio());
            ps.setString(4, evento.getDataFim());
            ps.setLong(5, evento.getIdPalestrante());
            ps.setLong(6, evento.getIdCurso());
            ps.setString(7, evento.getLocalizacao());


            String caminhoImagem = "Imagens/" + evento.getNomeImagem();
            ps.setString(8, caminhoImagem);

            ps.executeUpdate();
            ps.close();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar evento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        try {
            var evento = (Evento) entity;

            String sqlUpdate = "UPDATE evento SET titulo=?, descricao=?, data_inicio=?, data_fim=?, id_palestrante=?, id_curso=?, localizacao=?, imagem=? WHERE id=?";

            var ps = getConnection().prepareStatement(sqlUpdate);
            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getDescricao());
            ps.setString(3, evento.getDataInicio());
            ps.setString(4, evento.getDataFim());
            ps.setLong(5, evento.getIdPalestrante());
            ps.setLong(6, evento.getIdCurso());
            ps.setString(7, evento.getLocalizacao());
            ps.setString(8, "Imagens/" + evento.getNomeImagem());
            ps.setLong(9, evento.getId());

            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar evento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Evento> eventos = new ArrayList<>();

        try {
            var resultSet = getConnection()
                    .prepareStatement("SELECT * FROM evento")
                    .executeQuery();

            while (resultSet.next()) {
                var evento = new Evento(
                        resultSet.getLong("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("descricao"),
                        resultSet.getString("data_inicio"),
                        resultSet.getString("data_fim"),
                        resultSet.getLong("id_palestrante"),
                        resultSet.getLong("id_curso"),
                        resultSet.getString("localizacao"),
                        resultSet.getString("imagem")

                );
                eventos.add(evento);
            }

            resultSet.close();

        } catch (Exception e) {
            System.out.println("Erro ao listar eventos: " + e.getMessage());
        }

        return new ArrayList<>(eventos);
    }

    @Override
    public Object buscarPorId(Long id) {
        var evento = new Evento();

        try {
            String sql = "SELECT * FROM evento WHERE id = ?";
            var ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            var rs = ps.executeQuery();

            if (rs.next()) {
                evento.setId(rs.getLong("id"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setDataInicio(rs.getString("data_inicio"));
                evento.setDataFim(rs.getString("data_fim"));
                evento.setIdPalestrante(rs.getLong("id_palestrante"));
                evento.setIdCurso(rs.getLong("id_curso"));
                evento.setLocalizacao(rs.getString("localizacao"));
            }

            rs.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar evento por ID: " + e.getMessage());
        }

        return evento;
    }

    public boolean deletar(Long id) {
        try {
            String sqlDelete = "DELETE FROM evento WHERE id = ?";
            var ps = getConnection().prepareStatement(sqlDelete);
            ps.setLong(1, id);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao deletar evento: " + e.getMessage());
            return false;
        }
    }
}
