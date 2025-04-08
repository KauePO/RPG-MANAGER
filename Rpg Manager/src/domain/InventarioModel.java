package domain;

public class InventarioModel {
    static String[] colunas = {"Nome", "Quantidade", "Peso", "Custo"};
    private static TabelaReordenavel model = new TabelaReordenavel(colunas,0);

    public static TabelaReordenavel getModel() {
        return model;
    }
}
