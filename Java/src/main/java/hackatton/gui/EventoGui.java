package hackatton.gui;

import hackatton.dao.PalestranteDao;
import hackatton.model.Curso;
import hackatton.model.Evento;
import hackatton.model.Palestrante;
import hackatton.service.EventoService;

import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EventoGui extends JFrame implements GuiUtil {

    private JComboBox<String> cbPalestrante;
    private JComboBox<String> cbCurso;

    private final java.util.Map<String, Long> mapaNomeParaIdPalestrante = new java.util.HashMap<>();
    private final java.util.Map<String, Long> mapaNomeParaIdCurso = new java.util.HashMap<>();

    private final EventoService eventoService;
    private Long eventoSelecionadoId = null;

    private JLabel jlTitulo;
    private JTextField tfTitulo;

    private JLabel jlDescricao;
    private JTextField tfDescricao;

    private JLabel jlLocalizacao;
    private JTextField tfLocalizacao;

    private JLabel jlDataInicio;
    private JTextField tfDataInicio;

    private JLabel jlDataFim;
    private JTextField tfDataFim;

    private JLabel jlIdPalestrante;
    private JTextField tfIdPalestrante;

    private JLabel jlIdCurso;
    private JTextField tfIdCurso;

    private JButton btSalvar;
    private JButton btListar;
    private JButton btExcluir;
    private JButton btEditar;

    private JLabel jlImagem;
    private JTextField tfImagemPath;
    private JButton btSelecionarImagem;



    private JTable tabela;

    public EventoGui(EventoService eventoService) {
        this.eventoService = eventoService;

        setTitle("Cadastro de Evento");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(montarPainelEntrada(), BorderLayout.NORTH);
        getContentPane().add(montarPainelSaida(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private void selecionarImagem(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            var fileSelecionado = fileChooser.getSelectedFile();
            String nomeArquivo = System.currentTimeMillis() + "_" + fileSelecionado.getName();
            String destino = "imagens/" + nomeArquivo;

            try {
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get("imagens"));
                java.nio.file.Files.copy(
                        fileSelecionado.toPath(),
                        java.nio.file.Paths.get(destino),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
                tfImagemPath.setText(nomeArquivo); // só o nome, não o caminho completo
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar imagem.");
            }
        }
    }

    private JPanel montarPainelEntrada() {
        var jPanel = new JPanel(new GridBagLayout());

        jlTitulo = new JLabel("Título:");
        tfTitulo = new JTextField(20);
        jlDescricao = new JLabel("Descrição:");
        tfDescricao = new JTextField(20);
        jlDataInicio = new JLabel("Data Início:");
        tfDataInicio = new JTextField(20);
        jlDataFim = new JLabel("Data Fim:");
        tfDataFim = new JTextField(20);
        jlIdPalestrante = new JLabel("Palestrante:");
        jlIdCurso = new JLabel("Curso:");

        cbPalestrante = new JComboBox<>();
        cbCurso = new JComboBox<>();

        carregarPalestrantes();
        carregarCursos();

        jlLocalizacao = new JLabel("Localização:");
        tfLocalizacao = new JTextField(20);

        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(this::salvarEvento);

        btListar = new JButton("Listar Eventos");
        btListar.addActionListener(this::listarEventos);

        btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(this::deletarEvento);

        btEditar = new JButton("Editar");
        btEditar.addActionListener(this::editarEvento);

        jPanel.add(jlTitulo, montarGrid(0, 1));
        jPanel.add(tfTitulo, montarGrid(1, 1));
        jPanel.add(jlDescricao, montarGrid(0, 2));
        jPanel.add(tfDescricao, montarGrid(1, 2));
        jPanel.add(jlDataInicio, montarGrid(0, 3));
        jPanel.add(tfDataInicio, montarGrid(1, 3));
        jPanel.add(jlDataFim, montarGrid(0, 4));
        jPanel.add(tfDataFim, montarGrid(1, 4));
        jPanel.add(jlIdPalestrante, montarGrid(0, 5));
        jPanel.add(cbPalestrante, montarGrid(1, 5));
        jPanel.add(jlIdCurso, montarGrid(0, 6));
        jPanel.add(cbCurso, montarGrid(1, 6));
        jPanel.add(jlLocalizacao, montarGrid(0, 7));
        jPanel.add(tfLocalizacao, montarGrid(1, 7));
        jPanel.add(btSalvar, montarGrid(0, 8));
        jPanel.add(btListar, montarGrid(1, 8));
        jPanel.add(btExcluir, montarGrid(2, 8));
        jPanel.add(btEditar, montarGrid(3, 8));

        jlImagem = new JLabel("Imagem:");
        tfImagemPath = new JTextField(20);
        tfImagemPath.setEditable(false);

        btSelecionarImagem = new JButton("Selecionar Imagem");
        btSelecionarImagem.addActionListener(this::selecionarImagem);

        jPanel.add(jlImagem, montarGrid(0, 9));
        jPanel.add(tfImagemPath, montarGrid(1, 9));
        jPanel.add(btSelecionarImagem, montarGrid(2, 9));

        return jPanel;
    }

    private JPanel montarPainelSaida() {
        var jPanel = new JPanel(new BorderLayout());

        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);

        tabela.setModel(carregarEventos());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarEvento);

        var scrollPanel = new JScrollPane(tabela);
        jPanel.add(scrollPanel, BorderLayout.CENTER);

        return jPanel;
    }

    private void deletarEvento(ActionEvent e) {
        if (eventoSelecionadoId != null) {
            boolean sucesso = eventoService.deletarEvento(eventoSelecionadoId);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Evento excluído com sucesso!");
                limparCampos();
                tabela.setModel(carregarEventos());
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir evento.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um evento na tabela para excluir.");
        }
    }
    private void editarEvento(ActionEvent e) {

        if (eventoSelecionadoId == null) {
            JOptionPane.showMessageDialog(this, "Selecione um evento na tabela para editar.");
            return;
        }

        if (tfTitulo.getText().isBlank() || tfDescricao.getText().isBlank() ||
                tfDataInicio.getText().isBlank() || tfDataFim.getText().isBlank() ||
                tfLocalizacao.getText().isBlank() || tfImagemPath.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios (incluindo imagem).", "Campos vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cbPalestrante.getSelectedItem() == null || cbCurso.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso e um palestrante.", "Seleção inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String regexDataHora = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";

        if (!tfDataInicio.getText().matches(regexDataHora) || !tfDataFim.getText().matches(regexDataHora)) {
            JOptionPane.showMessageDialog(this,
                    "Formato de data inválido, \nUse: yyyy-MM-dd HH:mm:ss\nEx: 2025-06-13 14:00",
                    "Data inválida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Evento evento = new Evento(
                eventoSelecionadoId,
                tfTitulo.getText(),
                tfDescricao.getText(),
                tfDataInicio.getText(),
                tfDataFim.getText(),
                Long.valueOf(tfIdPalestrante.getText()),
                Long.valueOf(tfIdCurso.getText()),
                tfLocalizacao.getText(),
                tfImagemPath.getText()
        );

        boolean sucesso = eventoService.atualizarBD(evento);

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Evento editado com sucesso!");
            tabela.setModel(carregarEventos());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao editar evento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void selecionarEvento(ListSelectionEvent event) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            eventoSelecionadoId = (Long) tabela.getValueAt(selectedRow, 0);

            tfTitulo.setText((String) tabela.getValueAt(selectedRow, 1));
            tfDescricao.setText((String) tabela.getValueAt(selectedRow, 2));
            tfDataInicio.setText((String) tabela.getValueAt(selectedRow, 3));
            tfDataFim.setText((String) tabela.getValueAt(selectedRow, 4));
            tfLocalizacao.setText((String) tabela.getValueAt(selectedRow, 7));

            Long idPalestrante = (Long) tabela.getValueAt(selectedRow, 5);
            Long idCurso = (Long) tabela.getValueAt(selectedRow, 6);

            mapaNomeParaIdPalestrante.forEach((nome, id) -> {
                if (id.equals(idPalestrante)) cbPalestrante.setSelectedItem(nome);
            });

            mapaNomeParaIdCurso.forEach((nome, id) -> {
                if (id.equals(idCurso)) cbCurso.setSelectedItem(nome);
            });
        }
    }

    private DefaultTableModel carregarEventos() {
        var model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Título");
        model.addColumn("Descrição");
        model.addColumn("Data Início");
        model.addColumn("Data Fim");
        model.addColumn("ID Palestrante");
        model.addColumn("ID Curso");
        model.addColumn("Localização");

        eventoService.listarBD().forEach(evento -> {
            model.addRow(new Object[]{
                    evento.getId(),
                    evento.getTitulo(),
                    evento.getDescricao(),
                    evento.getDataInicio(),
                    evento.getDataFim(),
                    evento.getIdPalestrante(),
                    evento.getIdCurso(),
                    evento.getLocalizacao()
            });
        });

        return model;
    }

    private void listarEventos(ActionEvent e) {
        JOptionPane.showMessageDialog(this, eventoService.listar());
    }

    private void salvarEvento(ActionEvent e) {
        Long idPalestrante = mapaNomeParaIdPalestrante.get((String) cbPalestrante.getSelectedItem());
        Long idCurso = mapaNomeParaIdCurso.get((String) cbCurso.getSelectedItem());

        Evento evento = new Evento(
                eventoSelecionadoId,
                tfTitulo.getText(),
                tfDescricao.getText(),
                tfDataInicio.getText(),
                tfDataFim.getText(),
                idPalestrante,
                idCurso,
                tfLocalizacao.getText(),
                tfImagemPath.getText()
        );

        boolean sucesso;

        if (eventoSelecionadoId == null) {
            sucesso = eventoService.salvarBD(evento);
        } else {
            sucesso = eventoService.atualizarBD(evento);
        }

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Evento salvo com sucesso!");
            tabela.setModel(carregarEventos());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar evento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        if (tfTitulo.getText().isBlank() || tfDescricao.getText().isBlank() ||
                tfDataInicio.getText().isBlank() || tfDataFim.getText().isBlank() ||
                tfLocalizacao.getText().isBlank() || tfImagemPath.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios (incluindo imagem).", "Campos vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cbPalestrante.getSelectedItem() == null || cbCurso.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso e um palestrante.", "Seleção inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String regexDataHora = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";

        if (!tfDataInicio.getText().matches(regexDataHora) || !tfDataFim.getText().matches(regexDataHora)) {
            JOptionPane.showMessageDialog(this,
                    "Formato de data inválido, \nUse: yyyy-MM-dd HH:mm:ss\nEx: 2025-06-13 14:00",
                    "Data inválida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

    }

    private void limparCampos() {
        eventoSelecionadoId = null;
        tfTitulo.setText("");
        tfDescricao.setText("");
        tfDataInicio.setText("");
        tfDataFim.setText("");
        tfLocalizacao.setText("");
        tfImagemPath.setText("");
        cbPalestrante.setSelectedIndex(0);
        cbCurso.setSelectedIndex(0);
    }

    private void carregarPalestrantes() {
        mapaNomeParaIdPalestrante.clear();
        cbPalestrante.removeAllItems();

        List<Palestrante> lista = (List<Palestrante>) eventoService.listarPalestrantes();
        for (Palestrante p : lista) {
            mapaNomeParaIdPalestrante.put(p.getNome(), p.getId());
            cbPalestrante.addItem(p.getNome());
        }
    }

    private void carregarCursos() {
        mapaNomeParaIdCurso.clear();
        cbCurso.removeAllItems();

        List<Curso> lista = (List<Curso>) eventoService.listarCursos();
        for (Curso c : lista) {
            mapaNomeParaIdCurso.put(c.getNome(), c.getId());
            cbCurso.addItem(c.getNome());
        }
    }

}
