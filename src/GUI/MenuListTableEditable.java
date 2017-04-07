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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import RestaurantObjects.Menu;

public class MenuListTableEditable extends JPanel {

	static ArrayList<RestaurantObjects.MenuItem> menuFromJTable = new ArrayList<RestaurantObjects.MenuItem>();
	private static final String LINE_BREAK = "\n";
	private static final String CELL_BREAK = "\t";
	private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
	private final int firstColumn = 0;
	private final int secondColumn = 1;
	private final int thirdColumn = 2;
	private final int fourthColumn = 3;
	private boolean isEdited;

	public MenuListTableEditable() {

		String[] header = { "Име", "Цена", "Количество", "Категория" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		JTable table = new JTable(tableModel);

		setTableProperties(table);
		fillTable(table, tableModel);

		table.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexForCategory = 3;
		sortKeys.add(new RowSorter.SortKey(columnIndexForCategory, SortOrder.ASCENDING));
		int columnIndexForName = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexForName, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setComparator(columnIndexForName, new Comparator<String>() {
			@Override
			public int compare(String name1, String name2) {
				return name1.compareTo(name2);
			}
		});
		sorter.sort();

		setPopupMenu(table, tableModel);
	}

	public static void main(String[] args) {
		runMenuTableTest();
	}

	public void runTable() {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		MenuListTableEditable menuTable = new MenuListTableEditable();
		tableFrame.setTitle("Меню - редактиране");
		tableFrame.setVisible(true);
		tableFrame.add(menuTable);
		tableFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(false);
				int response = JOptionPane.showConfirmDialog(null, "Направени са промени.\nЖелаете ли да запаметите?",
						"Менюто е редактирано", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					tableFrame.dispose();
				} else if (response == JOptionPane.YES_OPTION) {
					saveTableToFile();
				} else if (response == JOptionPane.CLOSED_OPTION) {
				}

			}
		});

	}

	// public void windowClosing(WindowEvent e) {
	//
	// System.out.println("Trqbwaaa da se zapishe fajla");
	// if (isEdited) {
	//
	// JDialog.setDefaultLookAndFeelDecorated(false);
	// int response = JOptionPane.showConfirmDialog(null,
	// "Направени са промени.\nЖелаете ли да запаметите?", "Менюто е
	// редактирано",
	// JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	// if (response == JOptionPane.NO_OPTION) {
	// tableFrame.dispose();
	// } else if (response == JOptionPane.YES_OPTION) {
	// saveTableToFile();
	// } else if (response == JOptionPane.CLOSED_OPTION) {
	// }
	// }
	// }
	// });

	public static void runMenuTableTest() {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		MenuListTableEditable menuTable = new MenuListTableEditable();
		tableFrame.setTitle("Меню - редактиране");
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
		setTableMouseListener(table);
		setUpCategoryColumn(table, table.getColumnModel().getColumn(fourthColumn));
	}

	private void fillTable(JTable table, DefaultTableModel tableModel) {
		new Menu();
		for (int i = 0; i < Menu.getMenuList().size(); i++) {
			String name = Menu.getMenuList().get(i).getName();
			double price = Menu.getMenuList().get(i).getPrice();
			String quantity = Menu.getMenuList().get(i).getQuantity();
			String category = Menu.getMenuList().get(i).getCategory();
			Object[] data = { name, price, quantity, category };
			tableModel.addRow(data);
		}
	}

	private void setPopupMenu(JTable table, DefaultTableModel tableModel) {
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem mntmCut = new JMenuItem("Изрежи");
		mntmCut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copyToClipboard(true, table);
			}
		});
		popupMenu.add(mntmCut);

		JMenuItem mntmCopy = new JMenuItem("Копирай");
		mntmCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copyToClipboard(false, table);
			}
		});
		popupMenu.add(mntmCopy);

		JMenuItem mntmPaste = new JMenuItem("Постави");
		mntmPaste.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pasteFromClipboard(table);
			}
		});
		popupMenu.add(mntmPaste);

		JMenuItem mntmClear = new JMenuItem("Изчисти реда");
		mntmClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.removeRow(selectedRow);
				tableModel.insertRow(selectedRow, new String[0]);
			}
		});
		popupMenu.add(mntmClear);

		JMenuItem mntmDuplicate = new JMenuItem("Дублирай реда");
		mntmDuplicate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Vector<?> data = tableModel.getDataVector();
				Vector<?> row = (Vector<?>) data.elementAt(selectedRow);
				data = (Vector<?>) row.clone();
				tableModel.insertRow(selectedRow, data);
				table.clearSelection();
				table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		popupMenu.add(mntmDuplicate);

		JMenuItem menuInsert = new JMenuItem("Вмъкни нов ред");
		menuInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.insertRow(selectedRow, new String[0]);
			}
		});
		popupMenu.add(menuInsert);

		JMenuItem menuAdd = new JMenuItem("Добави нов ред");
		menuAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableModel.addRow(new String[0]);
			}
		});
		popupMenu.add(menuAdd);

		JMenuItem menuRemove = new JMenuItem("Изтрий реда");
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

	private void isTableEdited(boolean isEdited, JTable table) {
		System.out.println("Table is edited" + isEdited);
		if (isEdited) {
			this.isEdited = true;
			saveJTableToArrayList(table);
			printArrayList(menuFromJTable);
			// saveTableToFile();
		}
	}

	private void setTableMouseListener(JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isTableEdited(true, table);

			}
		});
	}

	private void saveJTableToArrayList(JTable table) {
		String name;
		String quantity;
		String category;
		double price;

		menuFromJTable.clear();
		for (int row = 0; row < table.getRowCount(); row++) {
			if (table.getValueAt(row, firstColumn) == null || table.getValueAt(row, secondColumn) == null
					|| table.getValueAt(row, thirdColumn) == null || table.getValueAt(row, fourthColumn) == null) {
				continue;
			}

			Object getPrice = table.getValueAt(row, secondColumn);
			if (getPrice instanceof Double) {
				price = (double) table.getValueAt(row, secondColumn);
			} else {
				String tempString = getPrice.toString().replace(',', '.');
				if (isDouble(tempString)) {
					price = Double.parseDouble(tempString);
				} else {
					continue;
				}

			}
			name = (String) table.getValueAt(row, firstColumn);
			quantity = (String) table.getValueAt(row, thirdColumn);
			category = (String) table.getValueAt(row, fourthColumn);
			menuFromJTable.add(new RestaurantObjects.MenuItem(name, price, quantity, category));
		}

	}

	private void printArrayList(ArrayList<RestaurantObjects.MenuItem> menuFromJTablet) {
		for (RestaurantObjects.MenuItem item : menuFromJTablet) {
			item.printItem();
		}
	}

	private boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void saveTableToFile() {

		File file = new File("restaurantMenu.sav");
		if (file.exists()) {
			replaceFile(file);
		}
		createFile();
	}

	private void replaceFile(File file) {
		File tempFile = new File("restaurantMenu.backup");
		if (tempFile.exists()) {
			tempFile.delete();
		}
		new File("restaurantMenu.sav").renameTo(new File("restaurantMenu.backup"));
	}

	private void createFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream("restaurantMenu.sav");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(menuFromJTable);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void setUpCategoryColumn(JTable table, TableColumn fourthColumn) {
		new Menu();
		JComboBox<String> comboBox = new JComboBox(Menu.categoryList.toArray());
		fourthColumn.setCellEditor(new DefaultCellEditor(comboBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		fourthColumn.setCellRenderer(renderer);
	}
}