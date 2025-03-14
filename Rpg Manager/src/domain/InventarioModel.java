package domain;
import javax.swing.table.DefaultTableModel;

public class InventarioModel {
    private static DefaultTableModel model;

    public InventarioModel() {
        String [] colunas = {"Nome", "Quantidade", "Peso", "Custo"};
        model = new DefaultTableModel(colunas, 0);
    }

    public static DefaultTableModel getModel() {
        return model;
    }
}
