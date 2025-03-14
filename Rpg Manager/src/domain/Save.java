package domain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class Save extends Component {
    private DefaultTableModel model = InventarioModel.getModel();

    public void salvar() {
        JTable tabela = new JTable(model);

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("saveRPG.csv")))
        {
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
                if (i < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        }
        System.out.println("Salvo com sucesso!");
    } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
}

    public void autocarregar(){
        File arquivo = new File("saveRPG.csv");
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
            // Cria um JFileChooser para seleção do arquivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecionar arquivo de save"); // Título da janela

            // Opcional: Define o diretório inicial (ex: diretório do projeto)
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            // Mostra a janela de diálogo "Abrir"
            int resultado = fileChooser.showOpenDialog(this);

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
                        this,
                        "Erro ao carregar: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


