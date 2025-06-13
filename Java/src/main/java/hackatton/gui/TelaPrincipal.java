package hackatton.gui;

import hackatton.service.CursoService;
import hackatton.service.EventoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame implements GuiUtil {
    private JMenuBar menuBar;

    public TelaPrincipal() {

        setTitle("HACKATHON");
        setSize(850, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setJMenuBar(montarMenuBar());
    }

    private JMenuBar montarMenuBar() {
        menuBar = new JMenuBar();
        menuBar.add(montarMenuCad());
        menuBar.add(montarMenuRel());
        menuBar.add(montarMenuConfig());
        return menuBar;
    }

    private JMenu montarMenuConfig() {
        var menuConfig = new JMenu("Config.");
        var miSobre = new JMenuItem("Sobre");
        var miEquipe = new JMenuItem("Equipe");
        var miSair = new JMenuItem("Sair");

        menuConfig.add(miSobre);
        menuConfig.add(miEquipe);
        menuConfig.addSeparator();
        menuConfig.add(miSair);

        miSair.addActionListener(this::exit);
        miEquipe.addActionListener(this::exibirEquipe);
        miSobre.addActionListener(this::exibirSobre);

        menuConfig.setFont(new Font("Arial",Font.PLAIN,16));
        miSobre.setFont(new Font("Arial",Font.PLAIN,14));
        miEquipe.setFont(new Font("Arial",Font.PLAIN,14));
        miSair.setFont(new Font("Arial",Font.PLAIN,14));

        return menuConfig;
    }

    private static JMenu montarMenuRel() {
        var menuRel = new JMenu("Relatórios");
        var miRelAluno = new JMenuItem("Alunos por Evento");
        menuRel.add(miRelAluno);

        menuRel.setFont(new Font("Arial",Font.PLAIN,16));
        miRelAluno.setFont(new Font("Arial",Font.PLAIN,14));

        return menuRel;
    }

    private JMenu montarMenuCad() {
        var menuCad = new JMenu("Cadastros");

        var miEvento = new JMenuItem("Eventos");
        var miPalestrante = new JMenuItem("Palestrantes");
        var miAluno = new JMenuItem("Alunos");
        var miCurso = new JMenuItem("Cursos");

        menuCad.add(miEvento);
        menuCad.add(miPalestrante);
        menuCad.add(miAluno);
        menuCad.add(miCurso);

        menuCad.setFont(new Font("Arial", Font.PLAIN, 16));
        miEvento.setFont(new Font("Arial", Font.PLAIN, 14));
        miPalestrante.setFont(new Font("Arial", Font.PLAIN, 14));
        miAluno.setFont(new Font("Arial", Font.PLAIN, 14));
        miCurso.setFont(new Font("Arial", Font.PLAIN, 14));

        miEvento.addActionListener(this::abrirEventoGui);
        miCurso.addActionListener(this::abrirCursoGui);

        return menuCad;
    }


    private void exibirSobre(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, """
                        Equipe Hackathon
                        
                sdflj lksflasd lskfjalsdf ee lkjj
                sffsff fd fssdfwefdf ffeergd fdsd
                sfe fdsfefg sdfesf fsesgdfggg sfd
                                
                """);
    }

    private void exibirEquipe(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, """
                  Equipe Hackathon
                    Aluno Nome Sobrenome
                    Aluno Nome Sobrenome
                    Aluno Nome Sobrenome
                    Aluno Nome Sobrenome 
                """);
    }

    private void exit(ActionEvent actionEvent) {
        var result = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair",
                "Finalizar Aplicação",
                JOptionPane.YES_NO_OPTION);

        if (result == 0) System.exit(0);
    }

    private void abrirEventoGui(ActionEvent actionEvent) {
        var gui = new EventoGui(new EventoService());
        gui.setVisible(true);
    }

    private void abrirCursoGui(ActionEvent actionEvent) {
        var gui = new CursoGui(new CursoService());
        gui.setVisible(true);
    }
}
