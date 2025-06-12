package hackatton.gui;

import hackatton.model.Evento;
import hackatton.service.EventoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EventoGui extends JFrame implements GuiUtil {

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
        jlIdPalestrante = new JLabel("ID Palestrante:");
        tfIdPalestrante = new JTextField(20);
        jlIdCurso = new JLabel("ID Curso:");
        tfIdCurso = new JTextField(20);
        jlLocalizacao = new JLabel("Localização:");
        tfLocalizacao = new JTextField(20);

        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(this::salvarEvento);

        btListar = new JButton("Listar Eventos");
        btListar.addActionListener(this::listarEventos);

        btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(this::deletarEvento);



        jPanel.add(jlTitulo, montarGrid(0, 1));
        jPanel.add(tfTitulo, montarGrid(1, 1));
        jPanel.add(jlDescricao, montarGrid(0, 2));
        jPanel.add(tfDescricao, montarGrid(1, 2));
        jPanel.add(jlDataInicio, montarGrid(0, 3));
        jPanel.add(tfDataInicio, montarGrid(1, 3));
        jPanel.add(jlDataFim, montarGrid(0, 4));
        jPanel.add(tfDataFim, montarGrid(1, 4));
        jPanel.add(jlIdPalestrante, montarGrid(0, 5));
        jPanel.add(tfIdPalestrante, montarGrid(1, 5));
        jPanel.add(jlIdCurso, montarGrid(0, 6));
        jPanel.add(tfIdCurso, montarGrid(1, 6));
        jPanel.add(jlLocalizacao, montarGrid(0, 7));
        jPanel.add(tfLocalizacao, montarGrid(1, 7));
        jPanel.add(btSalvar, montarGrid(0, 8));
        jPanel.add(btListar, montarGrid(1, 8));
        jPanel.add(btExcluir, montarGrid(2, 8));

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


    private void selecionarEvento(ListSelectionEvent event) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            eventoSelecionadoId = (Long) tabela.getValueAt(selectedRow, 0); // <-- salva o ID

            tfTitulo.setText((String) tabela.getValueAt(selectedRow, 1));
            tfDescricao.setText((String) tabela.getValueAt(selectedRow, 2));
            tfDataInicio.setText((String) tabela.getValueAt(selectedRow, 3));
            tfDataFim.setText((String) tabela.getValueAt(selectedRow, 4));
            tfIdPalestrante.setText(tabela.getValueAt(selectedRow, 5).toString());
            tfIdCurso.setText(tabela.getValueAt(selectedRow, 6).toString());
            tfLocalizacao.setText((String) tabela.getValueAt(selectedRow, 7));
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
        Evento evento = new Evento(
                eventoSelecionadoId,
                tfTitulo.getText(),
                tfDescricao.getText(),
                tfDataInicio.getText(),
                tfDataFim.getText(),
                Long.valueOf(tfIdPalestrante.getText()),
                Long.valueOf(tfIdCurso.getText()),
                tfLocalizacao.getText()
        );

        boolean sucesso;

        if (eventoSelecionadoId == null) {
            sucesso = eventoService.salvarBD(evento); // novo
        } else {
            sucesso = eventoService.atualizarBD(evento); // edição
        }

        if (sucesso) {
            limparCampos();
            JOptionPane.showMessageDialog(this, "Evento salvo com sucesso!");
            tabela.setModel(carregarEventos());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar evento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        eventoSelecionadoId = null;
        tfTitulo.setText("");
        tfDescricao.setText("");
        tfDataInicio.setText("");
        tfDataFim.setText("");
        tfIdPalestrante.setText("");
        tfIdCurso.setText("");
        tfLocalizacao.setText("");
    }

}
