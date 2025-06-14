package hackatton.gui;

import hackatton.service.CursoService;
import hackatton.service.EventoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame implements GuiUtil {
    private JMenuBar menuBar;
    private JPanel telaInicialPanel;

    public TelaPrincipal() {

        setTitle("HACKATHON");
        setSize(850, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        exibirTelaInicial();
    }

    private void exibirTelaInicial() {
        telaInicialPanel = new JPanel(new GridBagLayout());
        telaInicialPanel.setBackground(Color.white);

        JLabel titulo = new JLabel("Bem-vindo ao Sistema Hackathon", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(33, 102, 153));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton btnEntrar = new JButton("Entrar no Sistema");
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEntrar.setBackground( new Color(33, 102, 153));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setOpaque(true);
        btnEntrar.setContentAreaFilled(true);
        btnEntrar.setBorderPainted(false);
        btnEntrar.addActionListener(e -> iniciarSistema());

        JButton btnSair = new JButton("Sair");
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSair.setBackground( new Color(237, 40, 57));
        btnSair.setForeground(Color.WHITE);
        btnSair.setOpaque(true);
        btnSair.setContentAreaFilled(true);
        btnSair.setBorderPainted(false);
        btnSair.addActionListener(e -> System.exit(0));

        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btnEntrar.setBackground(new Color(21, 71, 107)); }
            public void mouseExited(java.awt.event.MouseEvent e) { btnEntrar.setBackground(new Color(33, 102, 153)); }
        });
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btnSair.setBackground(new Color(180, 30, 40)); }
            public void mouseExited(java.awt.event.MouseEvent e) { btnSair.setBackground(new Color(237, 40, 57)); }
        });

        ImageIcon imagem = new ImageIcon(getClass().getResource("/imagem/ilustracao_sistema.png"));
        JLabel imagemLabel = new JLabel(imagem);



        telaInicialPanel.add(titulo, montarGrid(0, 0, 1, 1));
        telaInicialPanel.add(btnEntrar, montarGrid(0, 1, 1, 1));
        telaInicialPanel.add(btnSair, montarGrid(0, 2, 1, 1));
        telaInicialPanel.add(imagemLabel, montarGrid(0, 3, 1, 1));

        add(telaInicialPanel, BorderLayout.CENTER);
    }

    private void iniciarSistema() {
        remove(telaInicialPanel);
        setJMenuBar(montarMenuBar());
        revalidate();
        repaint();
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

    private JMenu montarMenuRel() {
        var menuRel = new JMenu("Relatórios");
        var miRelAluno = new JMenuItem("Alunos por Evento");

        menuRel.add(miRelAluno);

        miRelAluno.addActionListener(this::abrirInscricaoGui);

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
        miPalestrante.addActionListener(this::abrirPalestranteGui);

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

    private void abrirPalestranteGui(ActionEvent actionEvent) {
        var gui = new PalestranteGui();
        gui.setVisible(true);
    }
    private void abrirInscricaoGui(ActionEvent actionEvent) {
        var gui = new InscricaoGui();
        gui.setVisible(true);
    }
}
