package domain;

import javax.swing.table.DefaultTableModel;


public class IniciativaModel {
    static String[] colunas = {"Entidade", "Iniciativa"};
    private static TabelaReordenavel model = new TabelaReordenavel(colunas,0);

    public static TabelaReordenavel getModel() {
        return model;
    }
}
