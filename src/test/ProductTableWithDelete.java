package test;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class ProductTableWithDelete {
    private static final int DON_GIA = 100000;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductTableWithDelete().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Bảng sản phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Object[][] data = {
            {"Sản phẩm A", 1, DON_GIA * 1, "Xóa"},
            {"Sản phẩm B", 2, DON_GIA * 2, "Xóa"},
            {"Sản phẩm C", 3, DON_GIA * 3, "Xóa"}
        };

        String[] columnNames = {"Tên sản phẩm", "Số lượng", "Thành tiền (VND)", "Hủy"};

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 3;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return Integer.class;
                if (columnIndex == 2) return Integer.class;
                return String.class;
            }
        };

        for (Object[] row : data) {
            model.addRow(row);
        }

        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor());

        table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof Number) {
                    label.setText(String.format("%,d", ((Number) value).intValue()));
                    label.setHorizontalAlignment(JLabel.RIGHT);
                }
                return label;
            }
        });

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Thêm sản phẩm");
        addButton.addActionListener(e -> {
            String ten = "Sản phẩm A"; // hoặc cho chọn
            boolean found = false;

            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(ten)) {
                    int currentQty = (int) model.getValueAt(i, 1);
                    model.setValueAt(currentQty + 1, i, 1);
                    model.setValueAt((currentQty + 1) * DON_GIA, i, 2);
                    found = true;
                    break;
                }
            }

            if (!found) {
                model.addRow(new Object[]{ten, 1, DON_GIA, "Xóa"});
            }

            updateTotal();
        });

        totalLabel = new JLabel();
        updateTotal();

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(addButton, BorderLayout.WEST);
        bottomPanel.add(totalLabel, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void updateTotal() {
        int total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 2);
            if (value instanceof Number) {
                total += ((Number) value).intValue();
            }
        }
        totalLabel.setText("Tổng tiền: " + String.format("%,d VND", total));
    }

    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        private int editingRow;

        public SpinnerEditor() {
            JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner.getEditor();
            JTextField textField = editor.getTextField();
            textField.setEditable(true);

            spinner.addChangeListener(e -> {
                if (table.isEditing()) {
                    int quantity = (Integer) spinner.getValue();
                    model.setValueAt(quantity * DON_GIA, editingRow, 2);
                    updateTotal();
                }
            });

            textField.addActionListener(e -> stopCellEditing());
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editingRow = row;
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public boolean stopCellEditing() {
            try {
                spinner.commitEdit();
                return super.stopCellEditing();
            } catch (java.text.ParseException e) {
                JOptionPane.showMessageDialog(table, "Giá trị không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            if (e instanceof MouseEvent) {
                return ((MouseEvent) e).getClickCount() >= 2;
            }
            return true;
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Xóa");
            setForeground(Color.RED);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private final JButton button = new JButton("Xóa");
        private int rowToDelete = -1;

        public ButtonEditor() {
            super(new JCheckBox());
            button.setForeground(Color.RED);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            rowToDelete = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            SwingUtilities.invokeLater(() -> {
                if (rowToDelete >= 0 && rowToDelete < model.getRowCount()) {
                    model.removeRow(rowToDelete);
                    updateTotal();
                }
            });
            return null;
        }
    }
}
