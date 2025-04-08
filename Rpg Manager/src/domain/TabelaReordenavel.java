package domain;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TabelaReordenavel extends DefaultTableModel {

    public TabelaReordenavel(Object[] colunas, int rowCount) {
        super(colunas, rowCount);
    }

    public void moveRow(int from, int to) {
        if (from < 0 || to < 0 || from >= getRowCount() || to >= getRowCount()) {
            return;
        }
        // TROCA AS LINHAS DE POSIÇÃO
        Vector<?> rowData = (Vector<?>) getDataVector().elementAt(from);
        removeRow(from);
        insertRow(to, rowData.toArray());
    }

}
