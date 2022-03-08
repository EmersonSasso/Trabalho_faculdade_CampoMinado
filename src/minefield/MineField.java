package minefield;

//<editor-fold defaultstate="collapsed" desc="Importações">
import java.awt.Color;
import java.awt.Container;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;//</editor-fold>

public class MineField extends JFrame implements Runnable {

    public MineField() {
        super("MineField");
    }

//<editor-fold defaultstate="collapsed" desc="Declarações">
    public int tamx, tamy, nummina;
    /*Tamanho do Eixo de X, do Eixo de Y e o numero de Minas, variavel de controle q gera as minas, respectivamente.*/

    private int[][] vetor, verifica, flag, trava;
    /*O jogo e a variavel de verificação para nao checar a mesma posição (x,y) 2x*/

    private JButton[][] comp;/*quadrados das minas*/

    JButton button, button2;/*button reiniciar e novo jogo*/

    private int contador, flags, perda, time, paratempo, gerado, controle, aviso;/*controles*/

    MineField frame;
    private JLabel campo, campo1, campo2;
    private JLabel campo3;
    Aviso notify = new Aviso(frame, true);
    Thread t1;
    StringBuffer resulta;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Métodos">
    private void Matriz() {
        /*Gerar o Jogo*/

        perda = 0;
        vetor = new int[tamx][tamy];
        verifica = new int[tamx][tamy];
        flag = new int[tamx][tamy];
        trava = new int[tamx][tamy];
        for (int h = 0; h < tamx; h++) {
            for (int j = 0; j < tamy; j++) {
                vetor[h][j] = 0;
                verifica[h][j] = 0;
                flag[h][j] = 0;
                trava[h][j] = 0;
            }
        }
    }

    private void GerarMina(int x, int y) {
        /*Gerar as minas*/

        for (int i = 0; i < nummina; i++) {
            int eixox = (int) (Math.random() * tamx);
            int eixoy = (int) (Math.random() * tamy);
            if ((eixox != x) || (eixoy != y)) {/*Excluir o Primeiro local clicado*/

                if ((vetor[eixox][eixoy] < 9) && (InsereNumero(eixox, eixoy) == 1)) {/*Conferir se ja havia uma mina e se vai haver uma casa adjacente no local para inserer a mina*/

                    vetor[eixox][eixoy] = 9;
                } else {
                    i--;
                }
            } else {
                i--;
            }
        }
        Retira10();
        gerado = 1;
    }

    private void VerificaClique(int x, int y) {/*Verifica o botão Clicado se:*/

        if (vetor[x][y] == 9) {/*-For uma bomba*/

            comp[x][y].setBackground(Color.BLACK);
            RevelaBomba();
        } else {/*-For algo diferente de uma bomba*/

            verifica[x][y] = 2;
            Verifica(x, y);
            do {
                controle = 0;
                for (int aux = 0; aux < tamx; aux++) {
                    for (int aux2 = 0; aux2 < tamy; aux2++) {
                        if (verifica[aux][aux2] == 2) {
                            Verifica(aux, aux2);
                            controle = 1;
                        }
                    }
                }
            } while (controle == 1);
        }
    }

    private void Verifica(int x, int y) {/*Verifica se:*/

        if (verifica[x][y] == 2) {/*A- Ja foi verificado este botão*/

            if (vetor[x][y] == 0) {/*B- Se ha um espaço vazio*/

                comp[x][y].setSelected(true);/*Inutiliza ele*/

                comp[x][y].setEnabled(false);
                comp[x][y].setBackground(Color.lightGray);
                Revela(x, y);
                verifica[x][y] = 1;
                trava[x][y] = 1;/*deixa o botao nao clicavel*/

            } else {/*C- Se ha um numero*/

                comp[x][y].setText(String.valueOf(vetor[x][y]));
                comp[x][y].setEnabled(false);/*Inutiliza ele*/

                CorTexto(vetor[x][y], x, y);/*muda a cor do fundo do botão*/

                trava[x][y] = 1;/*deixa o botao nao clicavel*/

                verifica[x][y] = 1;/*muda a variavel de verificação de clique para verificado*/

            }
        }
    }

    private void CorTexto(int numero, int a, int b) {/*muda o fundo do botao de acordo com o numero do mesmo*/

        if (numero == 1) {
            comp[a][b].setBackground(Color.RED);
        } else {
            if (numero == 2) {
                comp[a][b].setBackground(Color.GREEN);
            } else {
                if (numero == 3) {
                    comp[a][b].setBackground(Color.YELLOW);
                } else {
                    if (numero == 4) {
                        comp[a][b].setBackground(Color.CYAN);
                    } else {
                        if (numero == 5) {
                            comp[a][b].setBackground(Color.BLUE);
                        } else {
                            if (numero == 6) {
                                comp[a][b].setBackground(Color.MAGENTA);
                            } else {
                                if (numero == 7) {
                                    comp[a][b].setBackground(Color.PINK);
                                } else {
                                    if (numero == 8) {
                                        comp[a][b].setBackground(Color.ORANGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void Revela(int x, int y) {/*Revela os espaços vazios dependendo do local do clique*/

        if (verifica[x][y] == 2) {
            if ((x == 0) && (y == 0)) {
                MudaControle(x + 1, y + 1);
                MudaControle(x, y + 1);
                MudaControle(x + 1, y);
            } else {
                if ((x == 0) && (y < tamy - 1)) {
                    MudaControle(x + 1, y + 1);
                    MudaControle(x + 1, y - 1);
                    MudaControle(x, y - 1);
                    MudaControle(x, y + 1);
                    MudaControle(x + 1, y);
                } else {
                    if ((x == 0) && (y == tamy - 1)) {
                        MudaControle(x + 1, y - 1);
                        MudaControle(x, y - 1);
                        MudaControle(x + 1, y);
                    } else {
                        if ((x < tamx - 1) && (y == 0)) {
                            MudaControle(x - 1, y + 1);
                            MudaControle(x + 1, y + 1);
                            MudaControle(x - 1, y);
                            MudaControle(x + 1, y);
                            MudaControle(x, y + 1);
                        } else {
                            if ((x == tamx - 1) && (y == 0)) {
                                MudaControle(x - 1, y + 1);
                                MudaControle(x - 1, y);
                                MudaControle(x, y + 1);
                            } else {
                                if ((x == tamx - 1) && (y == tamy - 1)) {
                                    MudaControle(x - 1, y - 1);
                                    MudaControle(x, y - 1);
                                    MudaControle(x - 1, y);
                                } else {
                                    if ((x < tamx - 1) && (y == tamy - 1)) {
                                        MudaControle(x - 1, y - 1);
                                        MudaControle(x + 1, y - 1);
                                        MudaControle(x, y - 1);
                                        MudaControle(x - 1, y);
                                        MudaControle(x + 1, y);
                                    } else {
                                        if ((x == tamx - 1) && (y < tamy - 1)) {
                                            MudaControle(x - 1, y - 1);
                                            MudaControle(x - 1, y + 1);
                                            MudaControle(x, y - 1);
                                            MudaControle(x, y + 1);
                                            MudaControle(x - 1, y);
                                        } else {
                                            MudaControle(x - 1, y - 1);
                                            MudaControle(x - 1, y + 1);
                                            MudaControle(x + 1, y - 1);
                                            MudaControle(x + 1, y + 1);
                                            MudaControle(x + 1, y);
                                            MudaControle(x - 1, y);
                                            MudaControle(x, y - 1);
                                            MudaControle(x, y + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void RevelaBomba() {/*Revela todas as minas do jogo*/

        perda = 1;
        Fim((tamx * tamy) - nummina);/*Passa fim como valor maximo para finalizar*/

    }

    private void MudaControle(int u, int t) {
        if (verifica[u][t] == 0) {/*variavel controle para verificar o campo eh mudada*/

            verifica[u][t] = 2;
        }
    }

    private int InsereNumero(int x, int y) {/*Insere os numeros nas adjacencias das minas retornando:*/

        if ((x == 0) && (y == 0)) {/*1 se houver local disponivel e 0 caso nao houver*/

            vetor[x + 1][y + 1] = vetor[x + 1][y + 1] + 1;
            vetor[x][y + 1] = vetor[x][y + 1] + 1;
            vetor[x + 1][y] = vetor[x + 1][y] + 1;
            return 1;
        } else {
            if ((x == 0) && (y < tamy - 1)) {
                vetor[x + 1][y + 1] = vetor[x + 1][y + 1] + 1;
                vetor[x + 1][y - 1] = vetor[x + 1][y - 1] + 1;
                vetor[x][y - 1] = vetor[x][y - 1] + 1;
                vetor[x][y + 1] = vetor[x][y + 1] + 1;
                vetor[x + 1][y] = vetor[x + 1][y] + 1;
                return 1;
            } else {
                if ((x == 0) && (y == tamy - 1)) {
                    vetor[x + 1][y - 1] = vetor[x + 1][y - 1] + 1;
                    vetor[x][y - 1] = vetor[x][y - 1] + 1;
                    vetor[x + 1][y] = vetor[x + 1][y] + 1;
                    return 1;
                } else {
                    if ((x < tamx - 1) && (y == 0)) {
                        vetor[x - 1][y + 1] = vetor[x - 1][y + 1] + 1;
                        vetor[x + 1][y + 1] = vetor[x + 1][y + 1] + 1;
                        vetor[x - 1][y] = vetor[x - 1][y] + 1;
                        vetor[x + 1][y] = vetor[x + 1][y] + 1;
                        vetor[x][y + 1] = vetor[x][y + 1] + 1;
                        return 1;
                    } else {
                        if ((x == tamx - 1) && (y == 0)) {
                            vetor[x - 1][y + 1] = vetor[x - 1][y + 1] + 1;
                            vetor[x - 1][y] = vetor[x - 1][y] + 1;
                            vetor[x][y + 1] = vetor[x][y + 1] + 1;
                            return 1;
                        } else {
                            if ((x == tamx - 1) && (y == tamy - 1)) {
                                vetor[x - 1][y - 1] = vetor[x - 1][y - 1] + 1;
                                vetor[x][y - 1] = vetor[x][y - 1] + 1;
                                vetor[x - 1][y] = vetor[x - 1][y] + 1;
                                return 1;
                            } else {
                                if ((x < tamx - 1) && (y == tamy - 1)) {
                                    vetor[x - 1][y - 1] = vetor[x - 1][y - 1] + 1;
                                    vetor[x + 1][y - 1] = vetor[x + 1][y - 1] + 1;
                                    vetor[x][y - 1] = vetor[x][y - 1] + 1;
                                    vetor[x - 1][y] = vetor[x - 1][y] + 1;
                                    vetor[x + 1][y] = vetor[x + 1][y] + 1;
                                    return 1;
                                } else {
                                    if ((x == tamx - 1) && (y < tamy - 1)) {
                                        vetor[x - 1][y - 1] = vetor[x - 1][y - 1] + 1;
                                        vetor[x - 1][y + 1] = vetor[x - 1][y + 1] + 1;
                                        vetor[x][y - 1] = vetor[x][y - 1] + 1;
                                        vetor[x][y + 1] = vetor[x][y + 1] + 1;
                                        vetor[x - 1][y] = vetor[x - 1][y] + 1;
                                        return 1;
                                    } else {
                                        if (vetor[x][y] < 8) {
                                            vetor[x - 1][y - 1] = vetor[x - 1][y - 1] + 1;
                                            vetor[x - 1][y + 1] = vetor[x - 1][y + 1] + 1;
                                            vetor[x + 1][y - 1] = vetor[x + 1][y - 1] + 1;
                                            vetor[x + 1][y + 1] = vetor[x + 1][y + 1] + 1;
                                            vetor[x + 1][y] = vetor[x + 1][y] + 1;
                                            vetor[x - 1][y] = vetor[x - 1][y] + 1;
                                            vetor[x][y - 1] = vetor[x][y - 1] + 1;
                                            vetor[x][y + 1] = vetor[x][y + 1] + 1;
                                            return 1;
                                        } else {
                                            return 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void GerarCampo() {/*Criar os Botões do Campo*/ //<editor-fold defaultstate="collapsed" desc="Criar Frame"> 

        paratempo = 1;
        t1 = new Thread(this);
        frame = new MineField();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - ((tamx * 21) + 50)) / 2, (screenSize.height - (((tamy + 3) * 21) + 52)) / 2, ((tamx * 21) + 50), (((tamy + 3) * 21) + 52));
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(null);
        button = new JButton();
        button2 = new JButton();
        campo = new JLabel();
        campo1 = new JLabel();
        campo2 = new JLabel();
        campo3 = new JLabel();
        contador = nummina;
        time = flags = gerado = 0;
        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minefield/begin.gif")));
        button.setLocation((tamx * 21 / 2), 15);
        button.setSize(40, 40);
        button.setMargin(new java.awt.Insets(0, 0, 0, 0));
        frame.add(button);
        button2.setText("Início");
        button2.setLocation(((tamx * 21) - 38), 15);
        button2.setSize(60, 25);
        button2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        frame.add(button2);
        campo.setLocation(70, 15);
        campo.setSize(40, 25);
        frame.add(campo);
        campo.setText(String.valueOf(ContaMina()));
        campo1.setText("Minas:");
        campo1.setLocation(25, 15);
        campo1.setSize(50, 25);
        frame.add(campo1);
        campo2.setText("Tempo:");
        campo2.setLocation(25, 35);
        campo2.setSize(50, 25);
        frame.add(campo2);
        campo3.setText(String.valueOf(time));
        campo3.setLocation(70, 35);
        campo3.setSize(50, 25);
        frame.add(campo3);
        frame.setResizable(false);
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1.stop();
                paratempo = 9;
                frame.setVisible(false);
                Matriz();/*Zera a Matriz Antiga*/

                GerarCampo();
                frame.setVisible(true);
            }
        });
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paratempo = 9;
                frame.setVisible(false);
                MineField mf = new MineField();
                new Inicio(mf, true, mf).setVisible(true);
                mf.Matriz();
                mf.GerarCampo();
            }
        });//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Verifica Porcentagem de Minas, Altura e Largura">//verifica se os dados entrados pelo usuario estão
        resulta = new StringBuffer();
        aviso = 0;
        if ((nummina < 0.1 * (tamx * tamy)) || (nummina > 0.8 * (tamx * tamy))) {/*dentro dos permitidos*/

            this.setVisible(false);
            resulta.append("\n  Numero de minas inserido não se encontra entre 10% e 80% do tamanho do campo.\n");
            notify.jCampoAviso.setText(String.valueOf(resulta));
            aviso = 1;
        }
        if ((tamy < 8) || (tamy > 20)) {
            this.setVisible(false);
            resulta.append("\n  Numero de linhas inserido não se encontra entre 8 e 20.\n");
            notify.jCampoAviso.setText(String.valueOf(resulta));
            aviso = 1;
        }
        if ((tamx < 8) || (tamx > 60)) {
            this.setVisible(false);
            resulta.append("\n  Numero de colunas inserido não se encontra entre 8 e 60.");
            notify.jCampoAviso.setText(String.valueOf(resulta));
            aviso = 1;
        }
        if (aviso == 1) {
            notify.setVisible(true);
            new Inicio(this, true, this).setVisible(true);
            Matriz();
            GerarCampo();
        } else {//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Criar Grade">
            t1.start();
            comp = new JButton[tamx][tamy];
            for (int i = 0; i < tamx; i++) {
                for (int j = 0; j < tamy; j++) {
                    comp[i][j] = new JButton();
                    comp[i][j].setLocation((i + 1) * 21, (j + 3) * 21);
                    comp[i][j].setSize(20, 20);
                    comp[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
                    frame.add(comp[i][j]);
                    comp[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            int a, b;
                            a = ((evt.getComponent().getX() / 21) - 1);
                            b = ((evt.getComponent().getY() / 21) - 3);
                            if (trava[a][b] == 1) {
                            } else {
                                if (flag[a][b] == 1) {
                                } else {
                                    if (evt.getButton() == evt.BUTTON1) {
                                        if (gerado == 0) {
                                            paratempo = 0;
                                            GerarMina(a, b);
                                            VerificaClique(a, b);
                                            Fim(0);
                                        } else {
                                            VerificaClique(a, b);
                                            Fim(0);
                                        }
                                    }
                                }
                                if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                                    if (flag[a][b] == 0) {
                                        flags++;
                                        campo.setText(String.valueOf(ContaMina()));
                                        comp[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("/minefield/flag.gif")));
                                        flag[a][b] = 1;
                                        verifica[a][b] = 3;
                                    } else {
                                        flags--;
                                        campo.setText(String.valueOf(ContaMina()));
                                        comp[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
                                        flag[a][b] = 0;
                                        verifica[a][b] = 0;
                                    }
                                }
                            }
                        }
                    });
                }
            }
            frame.setVisible(true);
            frame.getGlassPane().setVisible(true);//</editor-fold>
        }
    }

    public void Fim(int fim) {/*verifica se todos os botões foram abertos para poder finalizar o jogo*/

        if (fim == 0) {
            for (int aux = 0; aux < tamx; aux++) {
                for (int aux2 = 0; aux2 < tamy; aux2++) {
                    if (verifica[aux][aux2] == 1) {
                        fim = fim + 1;
                    }
                }
            }
        }
        if (fim == ((tamx * tamy) - nummina)) {
            campo.setText("0");
            button.setText("");
            paratempo = 1;
            for (int i = 0; i < tamx; i++) {
                for (int j = 0; j < tamy; j++) {
                    if ((vetor[i][j] == 9) && (flag[i][j] != 1)) {
                        comp[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/minefield/mina.gif")));
                    } else {
                        if ((vetor[i][j] != 9) && (flag[i][j] == 1)) {/*verifica possiveis minas erradas*/

                            comp[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
                            comp[i][j].setForeground(Color.red);
                            comp[i][j].setBackground(Color.black);
                            comp[i][j].setText("X");
                        } else {
                        }
                    }
                    trava[i][j] = 1;
                }
            }
            if (perda == 0) {
                button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minefield/win.gif")));
                notify.jCampoAviso.setText("\n\n\n\n\t       Parabens!!\n\n        Você Venceu em " + time + " segundos.");
            } else {
                button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minefield/lost.gif")));
                notify.jCampoAviso.setText("\n\n\n\n\n\t     Você Perdeu!!");
            }
            notify.setVisible(true);
        }
    }

    private int ContaMina() {/*Conta as minas que o usuario axar*/

        contador = nummina - flags;
        return contador;
    }

    public void run() {/*Thread para contar o tempo*/

        do {
            do {
                if (paratempo == 0) {
                    try {
                        time = time + 1;
                        campo3.setText(String.valueOf(time));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            } while (paratempo == 0);
        } while (paratempo == 1);
    }

    private void Retira10() {/*Quando uma mina é adicionada perto de outra, ela faz a inicial aumentar de 9 para 10, 11, etc. esta função retorna as minas para 9.*/

        for (int h = 0; h < tamx; h++) {
            for (int j = 0; j < tamy; j++) {
                if (vetor[h][j] >= 9) {
                    vetor[h][j] = 9;
                }
            }
        }
    }//</editor-fold>

    public static void main(String[] args) {
        MineField mf = new MineField();
        new Inicio(mf, true, mf).setVisible(true);
        mf.Matriz();
        mf.GerarCampo();
    }
}
