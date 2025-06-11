package hackatton.dao;
import hackatton.model.Evento;
import java.util.ArrayList;
import java.util.List;

public class EventoDao extends Dao implements DaoInterface {
    @Override
    public boolean salvar(Object entity) {
        try {
            var evento = (Evento) entity;

            String sqlInsert = "insert into evento(titulo, descricao, data_inicio, data_fim, id_palestrante, id_curso) values(?,?,?,?,?,?)";

            var ps = getConnection().prepareStatement(sqlInsert);
            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getDescricao());
            ps.setString(3, evento.getDataInicio());
            ps.setString(4, evento.getDataFim());
            ps.setLong(5, evento.getIdPalestrante());
            ps.setLong(6, evento.getIdCurso());

            return ps.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        return false;
    }

    @Override
    public List<Object> listar() {
        List<Evento> eventos = new ArrayList<>();

        try {
            var resultSet = getConnection()
                    .prepareStatement("select * from evento")
                    .executeQuery();

            while (resultSet.next()) {
                var evento = new Evento(
                        null,
                        resultSet.getString("titulo"),
                        resultSet.getString("descricao"),
                        resultSet.getString("data_inicio"),
                        resultSet.getString("data_fim"),
                        resultSet.getLong("id_palestrante"),
                        resultSet.getLong("id_curso")
                );
                eventos.add(evento);
            }

            resultSet.close();

        } catch (Exception e) {
            System.out.println("Erro ao listar eventos:");
            e.printStackTrace();
        }


        return new ArrayList<>(eventos);
    }

    @Override
    public Object buscarPorId(Long id) {
        return null;
    }

    @Override
    public boolean deletar(Long id) {
        return false;
    }
}