import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.event.*;
import javax.swing.table.*;

public class MainWindow extends JPanel {
    final String TYPE_DIESEL     = "diesel";
    final String TYPE_JETTURBO   = "jetturbo";
    final String TYPE_COMBUSTION = "combustion";
    JTable jtabNumInfo;
    JTextField jtfFilter = new JTextField();
    JButton addItem      = new JButton("Add");
    JButton deleteItem   = new JButton("Delete");
    NumInfoModel model;


    MainWindow(Group arg) {

        model = new NumInfoModel(arg);
        jtabNumInfo = new JTable(model);

        jtabNumInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Сортировка при нажатии на заголовок
        jtabNumInfo.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>((TableModel) jtabNumInfo.getModel());
        jtabNumInfo.setRowSorter(sorter);

        // Включение данных в панель с прокруткой
        JScrollPane jscrlp = new JScrollPane(jtabNumInfo);

        // Установка размеров прокручиваемой области просмотра
        jtabNumInfo.setPreferredScrollableViewportSize(new Dimension(450, 110));

        // создание нового контейнера JPanel и размещение элементов
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Specify a word to match:"),
                BorderLayout.WEST);
        panel.add(jtfFilter, BorderLayout.CENTER);

        JPanel buttom = new JPanel(new BorderLayout());
        buttom.add(addItem, BorderLayout.CENTER);
        buttom.add(deleteItem, BorderLayout.SOUTH);
        panel.add(buttom, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(jscrlp, BorderLayout.CENTER);

        // поиск
        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });


        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.removeRow(jtabNumInfo.getSelectedRow());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Window();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

    }


    public static void main(String[] args) {

        List<Engine> engines = new ArrayList<>();

        engines.add(new DieselEngine("qwe", 5, 10, true));
        engines.add(new JetTutboEngine("asd", 5, 10, 30));
        engines.add(new CombustionEngine("zcx", 5, 10, false));
        engines.add(new DieselEngine("vbn", 5, 10, false));
        engines.add(new JetTutboEngine("fgh", 5, 10, 50));
        engines.add(new CombustionEngine("rty", 5, 10, true));

        Group group = new Group(engines);

        JFrame frame = new JFrame("Row Filter");
        frame.add(new MainWindow(group));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // окно добавление строки в таблицу
    class Window extends JFrame {
        Window() {
            final JFrame wd = new JFrame("Add row");
            wd.setLayout(new GridLayout(6, 2));
//            wd.setSize(new Dimension(300, 300));
            wd.setVisible(true);
            wd.setResizable(false);
            wd.setLocationRelativeTo(null);

            wd.add(new Label("Type"));
            JSpinner spinner = new JSpinner(new SpinnerListModel(new String[] {TYPE_DIESEL, TYPE_JETTURBO, TYPE_COMBUSTION}));
            wd.add(spinner);
            wd.add(new Label("Name"));
            TextField tName = new TextField();
            wd.add(tName);
            wd.add(new Label("Power"));
            TextField tPower = new TextField();
            wd.add(tPower);
            wd.add(new Label("Torque"));
            TextField tTorque = new TextField();
            wd.add(tTorque);
            wd.add(new Label("Unique"));
            TextField tUnique = new TextField();
            wd.add(tUnique);
            JButton addItem = new JButton("Add");
            addItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (spinner.getValue().equals(TYPE_DIESEL)) {
                            if (tUnique.getText().toLowerCase().equals("true") ||
                                    tUnique.getText().toLowerCase().equals("false")) {
                                model.addRow(new DieselEngine(tName.getText(), Integer.parseInt(tPower.getText()),
                                        Integer.parseInt(tTorque.getText()),
                                        Boolean.parseBoolean(tUnique.getText().toLowerCase())));
                                wd.dispose();
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Error enter!!");
                        } else if (spinner.getValue().equals(TYPE_JETTURBO)) {
                            model.addRow(new JetTutboEngine(tName.getText(), Integer.parseInt(tPower.getText()),
                                    Integer.parseInt(tTorque.getText()),
                                    Integer.parseInt(tUnique.getText())));
                            wd.dispose();
                        } else if (spinner.getValue().equals(TYPE_COMBUSTION)) {
                            if (tUnique.getText().toLowerCase().equals("true") ||
                                    tUnique.getText().toLowerCase().equals("false")) {
                                model.addRow(new CombustionEngine(tName.getText(), Integer.parseInt(tPower.getText()),
                                        Integer.parseInt(tTorque.getText()),
                                        Boolean.parseBoolean(tUnique.getText().toLowerCase())));
                                wd.dispose();
                            }
                            else JOptionPane.showMessageDialog(null, "Error enter!!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error enter!!");
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }

                }
            });
            wd.add(addItem);
            JButton closeWindow = new JButton("Cancel");
            closeWindow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wd.dispose();
                }
            });
            wd.add(closeWindow);
            wd.pack();
        }
    }

}

