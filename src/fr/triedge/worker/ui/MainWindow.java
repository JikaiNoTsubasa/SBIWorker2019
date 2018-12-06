package fr.triedge.worker.ui;

import fr.triedge.worker.utils.Utils;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {

	private Stage stage;
	private Scene scene;
	private BorderPane root;
	private TabPane tabPane;
	private MenuItem itemExit, itemOpenHtml, itemNewHtml, itemStorageLocation, itemOpenTaskManager, itemNewNote, itemOpenNote, itemOpenCanvas;
	private Menu menuFile, menuHtml, menuNote, menuTaskManager, menuCanvas, menuRecentNote;
	private MenuBar menuBar;
	
	public MainWindow(Stage stage, String title, double width, double height, String theme, String icon) {
		super();
		// Config stage
		this.stage = stage;
		stage.setTitle(title);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.getIcons().add(new Image(getClass().getResourceAsStream(icon)));
		
		// Root
        root = new BorderPane();
        root.getStylesheets().add(theme);
        tabPane = new TabPane();
        
        // Scene
        scene = new Scene(root);
        stage.setScene(scene);
        
        // Menubar
        setMenuBar(new MenuBar());
        setMenuFile(new Menu("File"));
        setMenuHtml(new Menu("Editor"));
        setMenuNote(new Menu("Notes"));
        setMenuTaskManager(new Menu("Task Manager"));
        setMenuCanvas(new Menu("Canvas"));
        setMenuRecentNote(new Menu("Recent notes"));
        
        setItemExit(new MenuItem("Exit"));
        setItemNewHtml(new MenuItem("New file"));
        setItemNewNote(new MenuItem("New note"));
        setItemOpenHtml(new MenuItem("Open file..."));
        setItemOpenNote(new MenuItem("Open note..."));
        setItemOpenTaskManager(new MenuItem("View tasks"));
        setItemStorageLocation(new MenuItem("Change Workspace"));
        
        getMenuHtml().getItems().add(itemNewHtml);
        getMenuHtml().getItems().add(itemOpenHtml);
        
        getMenuFile().getItems().add(itemStorageLocation);
        getMenuFile().getItems().add(new SeparatorMenuItem());
        getMenuFile().getItems().add(itemExit);
        getMenuNote().getItems().add(itemNewNote);
        getMenuNote().getItems().add(itemOpenNote);
        getMenuNote().getItems().add(getMenuRecentNote());
        getMenuTaskManager().getItems().add(itemOpenTaskManager);
        getMenuBar().getMenus().add(menuFile);
        getMenuBar().getMenus().add(menuHtml);
        getMenuBar().getMenus().add(menuNote);
        getMenuBar().getMenus().add(menuTaskManager);
        
        root.setTop(menuBar);
        root.setCenter(tabPane);
        root.setBottom(Utils.STATUS);
	}
	
	public void show() {
		stage.show();
	}
	
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public BorderPane getRoot() {
		return root;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
	public TabPane getTabPane() {
		return tabPane;
	}
	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public MenuItem getItemExit() {
		return itemExit;
	}

	public void setItemExit(MenuItem itemExit) {
		this.itemExit = itemExit;
	}

	public MenuItem getItemStorageLocation() {
		return itemStorageLocation;
	}

	public void setItemStorageLocation(MenuItem itemStorageLocation) {
		this.itemStorageLocation = itemStorageLocation;
	}

	public MenuItem getItemOpenTaskManager() {
		return itemOpenTaskManager;
	}

	public void setItemOpenTaskManager(MenuItem itemOpenTaskManager) {
		this.itemOpenTaskManager = itemOpenTaskManager;
	}

	public Menu getMenuFile() {
		return menuFile;
	}

	public void setMenuFile(Menu menuFile) {
		this.menuFile = menuFile;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public Menu getMenuTaskManager() {
		return menuTaskManager;
	}

	public void setMenuTaskManager(Menu menuTaskManager) {
		this.menuTaskManager = menuTaskManager;
	}

	public Menu getMenuHtml() {
		return menuHtml;
	}

	public void setMenuHtml(Menu menuHtml) {
		this.menuHtml = menuHtml;
	}

	public MenuItem getItemOpenHtml() {
		return itemOpenHtml;
	}

	public void setItemOpenHtml(MenuItem itemOpenHtml) {
		this.itemOpenHtml = itemOpenHtml;
	}

	public MenuItem getItemNewHtml() {
		return itemNewHtml;
	}

	public void setItemNewHtml(MenuItem itemNewHtml) {
		this.itemNewHtml = itemNewHtml;
	}

	public MenuItem getItemNewNote() {
		return itemNewNote;
	}

	public void setItemNewNote(MenuItem itemNewNote) {
		this.itemNewNote = itemNewNote;
	}

	public MenuItem getItemOpenNote() {
		return itemOpenNote;
	}

	public void setItemOpenNote(MenuItem itemOpenNote) {
		this.itemOpenNote = itemOpenNote;
	}

	public Menu getMenuNote() {
		return menuNote;
	}

	public void setMenuNote(Menu menuNote) {
		this.menuNote = menuNote;
	}

	public Menu getMenuCanvas() {
		return menuCanvas;
	}

	public void setMenuCanvas(Menu menuCanvas) {
		this.menuCanvas = menuCanvas;
	}

	public MenuItem getItemOpenCanvas() {
		return itemOpenCanvas;
	}

	public void setItemOpenCanvas(MenuItem itemOpenCanvas) {
		this.itemOpenCanvas = itemOpenCanvas;
	}

	public Menu getMenuRecentNote() {
		return menuRecentNote;
	}

	public void setMenuRecentNote(Menu menuRecentNote) {
		this.menuRecentNote = menuRecentNote;
	}

}
