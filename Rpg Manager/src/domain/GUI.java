package domain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private Inventario inventario;
    private Iniciativa iniciativa;
    private final Save save = new Save();

    public GUI() {
        setTitle("RPG MANAGER");
        this.setSize(800, 400);
        this.setMinimumSize(new Dimension(220, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InventarioModel inventarioModel = new InventarioModel();

        // Cria um painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);

        JPanel BotaoPainel = new JPanel(new BorderLayout());
        mainPanel.add(BotaoPainel, BorderLayout.NORTH);

        // BOTÃO DO INVENTARIO
        JButton Inventario = new JButton("Inventário");
        Inventario.setFont(new Font("Arial", Font.PLAIN, 40));
        BotaoPainel.add(Inventario, BorderLayout.WEST);

        Inventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (inventario == null) {
                    inventario = new Inventario();
                }
                inventario.setVisible(true);
            }
        });

        JPanel SavesPainel = new JPanel(new BorderLayout());
        BotaoPainel.add(SavesPainel, BorderLayout.EAST);

        // BOTAO SALVAR
        JButton Salvar = new JButton("Salvar");
        Salvar.setFont(new Font("Arial", Font.PLAIN, 40));
        SavesPainel.add(Salvar, BorderLayout.SOUTH);

        Salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                save.salvar();
            }
        });

        // BOTAO CARREGAR
        JButton Carregar = new JButton("Carregar");
        Carregar.setFont(new Font("Arial", Font.PLAIN, 40));
        SavesPainel.add(Carregar, BorderLayout.NORTH);

        Carregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                save.carregar();
            }
        });

        // BOTAO ESCOLHER CAMINHO
        JButton eCaminho = new JButton("Escolher caminho");
        eCaminho.setFont(new Font("Arial", Font.PLAIN, 40));
        SavesPainel.add(eCaminho, BorderLayout.CENTER);

        eCaminho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                save.escolherCaminho();
            }
        });

        // BOTAO INICIATIVA
        JButton Iniciativa = new JButton("Iniciativa");
        Iniciativa.setFont(new Font("Arial", Font.PLAIN, 40));
        BotaoPainel.add(Iniciativa, BorderLayout.CENTER);

        Iniciativa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (iniciativa == null) {
                    iniciativa = new Iniciativa();
                }
                iniciativa.setVisible(true);
            }
        });
    }
}
