import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class NumInfoModel extends DefaultTableModel implements TableModel{
    private Group obj;

    private int numRows;

    private String colNames[] = {"Type", "Name", "Power", "Torque", "Additives", "Quality", "Density"};


    @Override
    public boolean isCellEditable(int row, int column) {
        if (column != 0 && column <= 4)
            return true;
        return false;
    }

    private NumInfoModel(){}

    public NumInfoModel(Group obj1) {
        //super();
        this.obj = obj1;
        numRows = obj.getLenght();
    }

    @Override
    public int getRowCount() {
        return numRows == 0 ? numRows : obj.getLenght();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }


    @Override
    public String getColumnName(int column) {
        String result = colNames[column];
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if (column == 1)
            obj.engines.get(row).setName(aValue.toString());
        else if (column == 2)
            obj.engines.get(row).setPower(Integer.parseInt((String) aValue));
        else if (column == 3)
            obj.engines.get(row).setTorque(Integer.parseInt((String) aValue));
//        super.setValueAt(aValue, row, column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return obj.getEngines().get(rowIndex).getType();
        else if (columnIndex == 1)
            return obj.getEngines().get(rowIndex).getName();
        else if (columnIndex == 2)
            return obj.getEngines().get(rowIndex).getPower();
        else if (columnIndex == 3)
            return obj.getEngines().get(rowIndex).getTorque();
        else if (columnIndex == 4 && obj.getEngines().get(rowIndex) instanceof CombustionEngine)
            return ((CombustionEngine) obj.getEngines().get(rowIndex)).getAdditivies();
        else if (columnIndex == 5 && obj.getEngines().get(rowIndex) instanceof DieselEngine)
            return ((DieselEngine) obj.getEngines().get(rowIndex)).getFuel_quality();
        else if (columnIndex == 6 && obj.getEngines().get(rowIndex) instanceof JetTutboEngine)
            return ((JetTutboEngine) obj.getEngines().get(rowIndex)).getFuel_density();
        else if (columnIndex == 4 && obj.getEngines().get(rowIndex) instanceof DieselEngine ||
                obj.getEngines().get(rowIndex) instanceof JetTutboEngine)
            return "-";
        else if (columnIndex == 5 && obj.getEngines().get(rowIndex) instanceof CombustionEngine ||
                obj.getEngines().get(rowIndex) instanceof JetTutboEngine)
            return "-";
        else if (columnIndex == 6 && obj.getEngines().get(rowIndex) instanceof DieselEngine ||
                obj.getEngines().get(rowIndex) instanceof CombustionEngine)
            return "-";
        return null;
    }

    public <T extends Engine> void addRow(T rowData) {
        obj.addItem(rowData);
        fireTableDataChanged();
    }


    @Override
    public void removeRow(int row) {
        obj.deleteItem(row);
        fireTableDataChanged();
    }
}
