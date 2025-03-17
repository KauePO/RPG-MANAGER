package domain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class Save{
    private DefaultTableModel model = InventarioModel.getModel();
    private JFileChooser fileChooser = new JFileChooser();
    private File caminho;

    public Save() {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        caminho = new File (System.getProperty("user.dir"));
    }

    public void escolherCaminho() {
        fileChooser.setDialogTitle("Escolha a pasta do Save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(caminho);

        int resultado = fileChooser.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION) {
            caminho = fileChooser.getSelectedFile();
            System.out.println(caminho.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma pasta selecionada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void salvar() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(new File(caminho.getAbsolutePath(), "saveRPG.csv")))){
            System.out.println(caminho.getAbsolutePath());
            //CABEÇALHO
           for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            //CONTEUDO
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                writer.write(model.getValueAt(i,j).toString());
                if (j < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        }
        System.out.println("Salvo com sucesso!");

            JOptionPane.showMessageDialog(
                    null,
                    "Dados salvos com sucesso em:\n" + caminho.getAbsolutePath(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

    } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
}

    public void autocarregar(){
        File arquivo = new File(caminho, "saveRPG.csv");
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            if (linha == null) {
                return;
            }

            model.setRowCount(0);
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                model.addRow(partes);
            }

            System.out.println("Carregado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void carregar(){
            fileChooser.setDialogTitle("Selecionar arquivo de save"); // Título da janela

            // Define o diretório inicial
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            // Mostra a janela de diálogo "Abrir"
            int resultado = fileChooser.showOpenDialog(null);

            // Verifica se o usuário selecionou um arquivo
            if (resultado != JFileChooser.APPROVE_OPTION) {
                return; // Usuário cancelou a operação
            }

            File arquivo = fileChooser.getSelectedFile(); // Obtém o arquivo selecionado

            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha = reader.readLine();
                if (linha == null) {
                    return; // Arquivo vazio
                }

                model.setRowCount(0); // Limpa a tabela

                // Lê e processa cada linha do arquivo
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(",");
                        model.addRow(partes);
                }

                System.out.println("Carregado com sucesso!");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Erro ao carregar: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


