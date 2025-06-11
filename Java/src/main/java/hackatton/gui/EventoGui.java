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


    private JLabel jlTitulo;
    private JTextField tfTitulo;

    private JLabel jlDescricao;
    private JTextField tfDescricao;

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

    private JTable tabela;

    public EventoGui(EventoService eventoService) {
        this.eventoService = eventoService;

        setTitle("Cadastro de Evento");
        setSize(600, 450);
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

        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(this::salvarEvento);

        btListar = new JButton("Listar Eventos");
        btListar.addActionListener(this::listarEventos);


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
        jPanel.add(btSalvar, montarGrid(0, 7));
        jPanel.add(btListar, montarGrid(1, 7));

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

    private void selecionarEvento(ListSelectionEvent event) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            var id = (Long) tabela.getValueAt(selectedRow, 0);
            var titulo = (String) tabela.getValueAt(selectedRow, 1);
            var descricao = (String) tabela.getValueAt(selectedRow, 2);
            var dataInicio = (String) tabela.getValueAt(selectedRow, 3);
            var dataFim = (String) tabela.getValueAt(selectedRow, 4);
            var idPalestrante = (Long) tabela.getValueAt(selectedRow, 5);
            var idCurso = (Long) tabela.getValueAt(selectedRow, 6);

            limparCampos();


            tfTitulo.setText(titulo);
            tfDescricao.setText(descricao);
            tfDataInicio.setText(dataInicio);
            tfDataFim.setText(dataFim);
            tfIdPalestrante.setText(idPalestrante.toString());
            tfIdCurso.setText(idCurso.toString());
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

        eventoService.listarBD().forEach(evento -> {
            model.addRow(new Object[]{
                    evento.getId(),
                    evento.getTitulo(),
                    evento.getDescricao(),
                    evento.getDataInicio(),
                    evento.getDataFim(),
                    evento.getIdPalestrante(),
                    evento.getIdCurso()
            });
        });

        return model;
    }

    private void listarEventos(ActionEvent e) {
        JOptionPane.showMessageDialog(this, eventoService.listar());
    }

    private void salvarEvento(ActionEvent e) {


        var evento = new Evento(
                null,
                tfTitulo.getText(),
                tfDescricao.getText(),
                tfDataInicio.getText(),
                tfDataFim.getText(),
                Long.valueOf(tfIdPalestrante.getText()),
                Long.valueOf(tfIdCurso.getText())
        );

        eventoService.salvarBD(evento);
        limparCampos();
        JOptionPane.showMessageDialog(this, "Evento salvo com sucesso!");
        tabela.setModel(carregarEventos());
    }

    private void limparCampos() {
        tfTitulo.setText("");
        tfDescricao.setText("");
        tfDataInicio.setText("");
        tfDataFim.setText("");
        tfIdPalestrante.setText("");
        tfIdCurso.setText("");
    }
}
