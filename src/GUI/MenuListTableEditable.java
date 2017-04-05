package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import RestaurantObjects.Menu;

public class MenuListTableEditable extends JPanel {

	private static final String LINE_BREAK = "\n";
	private static final String CELL_BREAK = "\t";
	private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

	public MenuListTableEditable() {

		String[] header = { "Име", "Цена", "Количество", "Категория" };
		// String[][] data = new String[1000][];

		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		JTable table = new JTable(tableModel);
		setTableProperties(table);
		new Menu();
		fillTable(table, tableModel);
		setPopupMenu(table, tableModel);
	}

	public static void main(String[] args) {
		runMenuTableTest();
	}

	public void runMenuTable() {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		MenuListTableEditable menuTable = new MenuListTableEditable();
		tableFrame.setTitle("Меню");
		// tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
		// tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);
	}

	public static void runMenuTableTest() {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);

		MenuListTableEditable menuTable = new MenuListTableEditable();
		tableFrame.setTitle("Меню");
		// tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private void copyToClipboard(boolean isCut, JTable table) {
		int numCols = table.getSelectedColumnCount();
		int numRows = table.getSelectedRowCount();
		int[] rowsSelected = table.getSelectedRows();
		int[] colsSelected = table.getSelectedColumns();
		if (numRows != rowsSelected[rowsSelected.length - 1] - rowsSelected[0] + 1 || numRows != rowsSelected.length
				|| numCols != colsSelected[colsSelected.length - 1] - colsSelected[0] + 1
				|| numCols != colsSelected.length) {

			JOptionPane.showMessageDialog(null, "Invalid Copy Selection", "Invalid Copy Selection",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		StringBuffer excelStr = new StringBuffer();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				excelStr.append(escape(table.getValueAt(rowsSelected[i], colsSelected[j])));
				if (isCut) {
					table.setValueAt(null, rowsSelected[i], colsSelected[j]);
				}
				if (j < numCols - 1) {
					excelStr.append(CELL_BREAK);
				}
			}
			excelStr.append(LINE_BREAK);
		}

		StringSelection sel = new StringSelection(excelStr.toString());
		CLIPBOARD.setContents(sel, sel);
	}

	private void pasteFromClipboard(JTable table) {
		int startRow = table.getSelectedRows()[0];
		int startCol = table.getSelectedColumns()[0];

		String pasteString = "";
		try {
			pasteString = (String) (CLIPBOARD.getContents(this).getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Paste Type", "Invalid Paste Type", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] lines = pasteString.split(LINE_BREAK);
		for (int i = 0; i < lines.length; i++) {
			String[] cells = lines[i].split(CELL_BREAK);
			for (int j = 0; j < cells.length; j++) {
				if (table.getRowCount() > startRow + i && table.getColumnCount() > startCol + j) {
					table.setValueAt(cells[j], startRow + i, startCol + j);
				}
			}
		}
	}

	private String escape(Object cell) {
		return cell.toString().replace(LINE_BREAK, " ").replace(CELL_BREAK, " ");
	}

	private void setTableProperties(JTable table) {
		table.getColumn("Име").setMinWidth(400);
		table.getColumn("Цена").setMinWidth(60);
		table.getColumn("Цена").setMaxWidth(60);
		table.getColumn("Количество").setMinWidth(100);
		table.getColumn("Количество").setMaxWidth(100);
		table.getColumn("Количество").setResizable(false);
		table.getColumn("Цена").setResizable(false);
		table.setPreferredScrollableViewportSize(new Dimension(770, 330));
		table.setFillsViewportHeight(true);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVisible(true);
		add(tableScroll);
		table.getTableHeader().setReorderingAllowed(false);
	}

	private void fillTable(JTable table, DefaultTableModel tableModel) {
		for (int i = 0; i < Menu.getMenuList().size(); i++) {
			String name = Menu.getMenuList().get(i).getName();
			double price = Menu.getMenuList().get(i).getPrice();
			String quantity = Menu.getMenuList().get(i).getQuantity();
			String category = Menu.getMenuList().get(i).getCategory();

			Object[] data = { name, price, quantity, category };
			tableModel.addRow(data);
		}
	}
	
	private void setPopupMenu (JTable table, DefaultTableModel tableModel) {
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copyToClipboard(true, table);
			}
		});
		popupMenu.add(mntmCut);

		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copyToClipboard(false, table);
			}
		});
		popupMenu.add(mntmCopy);

		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pasteFromClipboard(table);
			}
		});
		popupMenu.add(mntmPaste);

		JMenuItem mntmClear = new JMenuItem("Clear row");
		mntmClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.removeRow(selectedRow);
				tableModel.insertRow(selectedRow, new String[0]);
			}
		});
		popupMenu.add(mntmClear);

		JMenuItem mntmDuplicate = new JMenuItem("Duplicate row");
		mntmDuplicate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Vector<?> data = tableModel.getDataVector();
				Vector<?> row = (Vector<?>) data.elementAt(selectedRow);
				data = (Vector<?>) row.clone();
				tableModel.insertRow(selectedRow, data);
				table.clearSelection();
				table.addRowSelectionInterval(selectedRow,selectedRow);
			}
		});
		popupMenu.add(mntmDuplicate);

		JMenuItem menuInsert = new JMenuItem("Insert New Row");
		menuInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.insertRow(selectedRow, new String[0]);
			}
		});
		popupMenu.add(menuInsert);

		JMenuItem menuAdd = new JMenuItem("Add New Row");
		menuAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableModel.addRow(new String[0]);
			}
		});
		popupMenu.add(menuAdd);

		JMenuItem menuRemove = new JMenuItem("Delete Row");
		menuRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.removeRow(selectedRow);
			}
		});
		popupMenu.add(menuRemove);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				// selects the row at which point the mouse is clicked
				Point point = event.getPoint();
				int currentRow = table.rowAtPoint(point);
				table.setRowSelectionInterval(currentRow, currentRow);
			}
		});
	}
}