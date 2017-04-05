package GUI;

import RestaurantObjects.*;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuListTable extends JPanel {

	JTable tableMenu;

	public MenuListTable() {

		String[] header = { "Име", "Цена", "Категория" };
//		String[][] data = new String[1000][];

		DefaultTableModel model = new DefaultTableModel(header, 0);

		JTable table = new JTable(model);
		table.getColumn("Име").setMinWidth(400);
		table.getColumn("Цена").setMinWidth(100);
		table.getColumn("Цена").setMaxWidth(100);
		table.getColumn("Цена").setResizable(false);
		table.setPreferredScrollableViewportSize(new Dimension(770, 530));
		table.setFillsViewportHeight(true);

		JScrollPane js = new JScrollPane(table);
		js.setVisible(true);
		add(js);

		ArrayList<MenuItem> originalLeagueList = new ArrayList<MenuItem>();

		originalLeagueList.add(new FootballClub(1, "Arsenal", 35, 11, 2, 2, 15, 30, 11, 19));
		originalLeagueList.add(new FootballClub(2, "Liverpool", 30, 9, 3, 3, 15, 34, 18, 16));
		originalLeagueList.add(new FootballClub(3, "Chelsea", 30, 9, 2, 2, 15, 30, 11, 19));
		originalLeagueList.add(new FootballClub(4, "Man City", 29, 9, 2, 4, 15, 41, 15, 26));
		originalLeagueList.add(new FootballClub(5, "Everton", 28, 7, 1, 7, 15, 23, 14, 9));
		originalLeagueList.add(new FootballClub(6, "Tottenham", 27, 8, 4, 3, 15, 15, 16, -1));
		originalLeagueList.add(new FootballClub(7, "Newcastle", 26, 8, 5, 2, 15, 20, 21, -1));
		originalLeagueList.add(new FootballClub(8, "Southampton", 23, 6, 4, 5, 15, 19, 14, 5));

		for (int i = 0; i < originalLeagueList.size(); i++){
		   int position = originalLeagueList.get(i).getPosition();
		   String name = originalLeagueList.get(i).getName();
		   int points = originalLeagueList.get(i).getPoinst();
		   int wins = originalLeagueList.get(i).getWins();
		   int defeats = originalLeagueList.get(i).getDefeats();
		   int draws = originalLeagueList.get(i).getDraws();
		   int totalMatches = originalLeagueList.get(i).getTotalMathces();
		   int goalF = originalLeagueList.get(i).getGoalF();
		   int goalA = originalLeagueList.get(i).getGoalA();
		   in ttgoalD = originalLeagueList.get(i).getTtgoalD();

		   Object[] data = {position, name, points, wins, defeats, draws, 
		                               totalMatches, goalF, goalA, ttgoalD};

		   tableModel.add(data);

		}
	}

	public static void main(String[] a) {

		JFrame tableFrame = new JFrame();
		MenuListTable tab = new MenuListTable();
		tableFrame.setTitle("Table");
		tableFrame.setSize(800, 600);
		tableFrame.setVisible(true);
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(tab);

	}

}