package domain;
import javax.swing.table.DefaultTableModel;

public class InventarioModel {
    static String[] colunas = {"Nome", "Quantidade", "Peso", "Custo"};
    private static DefaultTableModel model = new DefaultTableModel(colunas,0);

    public static DefaultTableModel getModel() {
        return model;
    }
}
