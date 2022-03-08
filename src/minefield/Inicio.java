package minefield;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Inicio extends JDialog {

    public Inicio(java.awt.Frame parent, boolean modal, MineField mf) {
        super(parent, modal);
        this.principal = mf;
        InitComponents();
    }

    private void InitComponents() {
        bg = new ButtonGroup();
        bg.add(jIntermediario);
        bg.add(jIniciante);
        bg.add(jAvancado);
        bg.add(jPersonalizado);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MineField");
        setResizable(false);
        jLargura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLarguraKeyReleased(evt);
            }
        });
        jAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAlturaKeyReleased(evt);
            }
        });
        JButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonIniciarActionPerformed(evt);
            }
        });
        jIniciante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInicianteActionPerformed(evt);
            }
        });
        jIntermediario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIntermediarioActionPerformed(evt);
            }
        });
        jAvancado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAvancadoActionPerformed(evt);
            }
        });
        jPersonalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonalizadoActionPerformed(evt);
            }
        });
        jFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFecharActionPerformed(evt);
            }
        });
        Container contentPane = this.getContentPane();
        GridLayout grid = new GridLayout(1, 2);
        GridLayout grid2 = new GridLayout(5, 3, 10, 20);
        GridLayout grid3 = new GridLayout(6, 1);
        BorderLayout bl = new BorderLayout();
        contentPane.setLayout(bl);
        center.setLayout(grid);
        center_opcoes.setLayout(grid3);
        center_perso.setLayout(grid2);
        page_end.setLayout(grid);
        center_opcoes.add(vazio);
        center_opcoes.add(jIniciante);
        center_opcoes.add(jIntermediario);
        center_opcoes.add(jAvancado);
        center_opcoes.add(jPersonalizado);
        center_perso.add(vazio1);
        center_perso.add(vazio2);
        center_perso.add(vazio3);
        center_perso.add(jLabelLargura);
        center_perso.add(jAltura);
        center_perso.add(jLabel2);
        center_perso.add(jLabelAltura);
        center_perso.add(jLargura);
        center_perso.add(jLabel1);
        center_perso.add(jLabelMina);
        center_perso.add(jMina);
        center_perso.add(jLabel3);
        center.add(center_opcoes);
        center.add(center_perso);
        page_end.add(JButtonIniciar);
        page_end.add(jFechar);
        page_start.add(vazio4);
        page_start.add(jTitulo);
        this.add(page_start, BorderLayout.PAGE_START);
        this.add(center, BorderLayout.CENTER);
        this.add(page_end, BorderLayout.PAGE_END);
        jPersonalizado.setSelected(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 434) / 2, (screenSize.height - 267) / 2, 434, 267);
    }

    private void JButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jIniciante.getSelectedObjects() != null) {
            principal.tamx = 10;
            principal.tamy = 10;
            principal.nummina = 10;
            this.setVisible(false);
        } else {
            if (jIntermediario.getSelectedObjects() != null) {
                principal.tamx = 16;
                principal.tamy = 16;
                principal.nummina = 40;
                this.setVisible(false);
            } else {
                if (jAvancado.getSelectedObjects() != null) {
                    principal.tamx = 30;
                    principal.tamy = 16;
                    principal.nummina = 99;
                    this.setVisible(false);
                } else {
                    try {
                        principal.tamx = Integer.parseInt(jAltura.getText());
                        principal.tamy = Integer.parseInt(jLargura.getText()); /*Dentro de Try Catch para nao autorizar entrada de caracter inválido*/

                        principal.nummina = Integer.parseInt(jMina.getText());
                        this.setVisible(false);
                    } catch (Exception e) {
                        Aviso notify = new Aviso(principal, true);
                        notify.jCampoAviso.setText("\n\n\n\n\n   Você inseriu um caractere inválido!");
                        notify.setVisible(true);
                    }
                }
            }
        }
    }

    private void jInicianteActionPerformed(java.awt.event.ActionEvent evt) {
        jAltura.setEnabled(false);
        jLabelAltura.setEnabled(false);
        jLargura.setEnabled(false);
        jLabelLargura.setEnabled(false);
        jMina.setEnabled(false);
        jLabelMina.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
    }

    private void jIntermediarioActionPerformed(java.awt.event.ActionEvent evt) {
        jAltura.setEnabled(false);
        jLabelAltura.setEnabled(false);
        jLargura.setEnabled(false);
        jLabelLargura.setEnabled(false);
        jMina.setEnabled(false);
        jLabelMina.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
    }

    private void jAvancadoActionPerformed(java.awt.event.ActionEvent evt) {
        jAltura.setEnabled(false);
        jLabelAltura.setEnabled(false);
        jLargura.setEnabled(false);
        jLabelLargura.setEnabled(false);
        jMina.setEnabled(false);
        jLabelMina.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
    }

    private void jPersonalizadoActionPerformed(java.awt.event.ActionEvent evt) {
        jAltura.setEnabled(true);
        jLabelAltura.setEnabled(true);
        jLargura.setEnabled(true);
        jLabelLargura.setEnabled(true);
        jMina.setEnabled(true);
        jLabelMina.setEnabled(true);
        jLabel1.setEnabled(true);
        jLabel2.setEnabled(true);
        jLabel3.setEnabled(true);
    }

    private void jFecharActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jLarguraKeyReleased(java.awt.event.KeyEvent evt) {
        try {
            int a = Integer.parseInt(jAltura.getText());
            int b = Integer.parseInt(jLargura.getText()); /*Dentro de Try Catch para nao autorizar entrada de caracter inválido*/

            jLabel3.setText("(" + (int) (a * b * 0.1) + " a " + (int) (a * b * 0.8) + ")");
        } catch (Exception e) {
        }
    }

    private void jAlturaKeyReleased(java.awt.event.KeyEvent evt) {
        try {
            int a = Integer.parseInt(jAltura.getText());
            int b = Integer.parseInt(jLargura.getText()); /*Dentro de Try Catch para nao autorizar entrada de caracter inválido*/

            jLabel3.setText("(" + (int) (a * b * 0.1) + " a " + (int) (a * b * 0.8) + ")");
        } catch (Exception e) {
        }
    }
    private JButton JButtonIniciar = new JButton("Iniciar");
    private JTextField jAltura = new JTextField("10");
    private JRadioButton jAvancado = new JRadioButton("Avançado (30x16)");
    private JButton jFechar = new JButton("Fechar");
    private JRadioButton jIniciante = new JRadioButton("Iniciante (10x10)");
    private JRadioButton jIntermediario = new JRadioButton("Intermediário (16x16)");
    private JLabel jLabel1 = new JLabel("(8 a 20)");
    private JLabel jLabel2 = new JLabel("(8 a 60)");
    private JLabel jLabel3 = new JLabel("(10 a 80)");
    private JLabel jLabelLargura = new JLabel("Largura");
    private JLabel jLabelAltura = new JLabel("Altura");
    private JLabel jLabelMina = new JLabel("Minas");
    private JTextField jLargura = new JTextField("10");
    private JTextField jMina = new JTextField("20");
    private JRadioButton jPersonalizado = new JRadioButton("Personalizado");
    private JLabel jTitulo = new JLabel("ESCOLHA O NIVEL DE DIFICULDADE");
    private ButtonGroup bg = new ButtonGroup();
    private JPanel page_end = new JPanel();
    private JPanel center = new JPanel();
    private JPanel center_perso = new JPanel();
    private JPanel center_opcoes = new JPanel();
    private JPanel page_start = new JPanel();
    private JLabel vazio = new JLabel();
    private JLabel vazio1 = new JLabel();
    private JLabel vazio2 = new JLabel();
    private JLabel vazio3 = new JLabel();
    private JLabel vazio4 = new JLabel();
    MineField principal = new MineField();
}
