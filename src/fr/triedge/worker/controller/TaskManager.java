package fr.triedge.worker.controller;

import java.util.ArrayList;

import fr.triedge.worker.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TaskManager {

	private Stage stage;
	private Scene scene;
	private SplitPane split;
	private TableView<Task> tableTask;
	private TabPane tabPane;
	private MenuBar menuBar;
	private MenuItem itemAddTask;
	private ArrayList<Task> tasks = new ArrayList<>();
	
	public void buildUI() {
		stage = new Stage();
		stage.setTitle("Task Manager");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons8-tentacles-64.png")));
		
		BorderPane root = new BorderPane();
		root.getStylesheets().add("/dark-theme.css");
		scene = new Scene(root, 600, 400);
		
		// Menubar
		menuBar = new MenuBar();
		Menu menuTask = new Menu("Task");
		itemAddTask = new MenuItem("New task");
		
		menuTask.getItems().add(itemAddTask);
		menuBar.getMenus().add(menuTask);
		
		// Split Pane
		split = new SplitPane();
		split.setOrientation(Orientation.VERTICAL);
		
		// Table view
		buildTable();
		
		// Tab Pane
		tabPane = new TabPane();
		
		//ScrollPane scrollTable = new ScrollPane(tableTask);
		//split.getItems().add(scrollTable);
		split.getItems().add(tableTask);
		split.getItems().add(tabPane);
		
		root.setTop(menuBar);
		root.setCenter(split);
		
		stage.setScene(scene);
		stage.show();
	}
	
	@SuppressWarnings("unchecked")
	private void buildTable() {
		tableTask = new TableView<>();
		
		// Set collumns
		TableColumn<Task, String> colTitle = new TableColumn<Task, String>("Name");
		TableColumn<Task, String> colDesc = new TableColumn<Task, String>("Description");
		
		// Set callbacks
		colTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
		colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		
		//ObservableList<Task> list = FXCollections.observableArrayList(user1, user2, user3)
		
		tableTask.getColumns().addAll(colTitle, colDesc);
	}
}
