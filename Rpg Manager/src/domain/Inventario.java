package domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inventario extends JFrame {
    private TabelaReordenavel model;

    public Inventario() {
        // Configurações do JFrame
        setTitle("Inventário");
        setSize(400, 300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza o JFrame na tela

        Save save = new Save();
        save.autocarregar();

        // Cria um painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);

        // Cria um DefaultTableModel sem dados iniciais
        model = InventarioModel.getModel();
        // Cria uma JTable com os dados e colunas
        JTable tabela = new JTable(model);

        //Variaveis para controlar o arraste
        final int[] pressIndex = {-1};

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                pressIndex[0] = tabela.rowAtPoint(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e){
                int releaseIndex = tabela.rowAtPoint(e.getPoint());
                if (pressIndex[0] != -1 && releaseIndex != -1 && pressIndex[0] != releaseIndex) {
                    model.moveRow(pressIndex[0], releaseIndex);
                }
            }
        });

        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tabela.setRowSelectionInterval(row, row);
                }
            }
        });

        // Adiciona a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adiciona o JScrollPane ao JFrame
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Cria um painel inferior para o campo de texto e botão
        JPanel inputPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        JPanel BotaoPainel = new JPanel(new BorderLayout());
        inputPanel.add(BotaoPainel, BorderLayout.EAST);

        // Campo de texto para entrada de dados
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        inputPanel.add(textField, BorderLayout.CENTER);

        // Botão para confirmar a entrada
        JButton confirmarButton = new JButton("Adicionar");
        BotaoPainel.add(confirmarButton, BorderLayout.NORTH);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText().trim();
                    processarInput(input);
                    textField.setText("");
            }
        });

        // Botão para apagar uma entrada
        JButton apagarButton = new JButton("Apagar Selecionado");
        BotaoPainel.add(apagarButton, BorderLayout.SOUTH);

        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(
                            Inventario.this,
                            "Nenhum item selecionado!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                model.removeRow(row);
            }
        });
        setVisible(true);
}

    private void processarInput(String input) {
        try {
        String[] partes = input.split(",");
        if (partes.length != 4) {
            throw new IllegalArgumentException("Formato Inválido!");
        }

        String nome = partes[0].trim();
        String quantidade = partes[1].trim();
        String peso = partes[2].trim();
        String custo = partes[3].trim();

        model.addRow(new Object[]{nome, quantidade, peso, custo});
    } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro: Formato inválido. Use: Nome,Quantidade,Peso,Custo",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
}}
