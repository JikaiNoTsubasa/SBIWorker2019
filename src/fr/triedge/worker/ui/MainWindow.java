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
	private MenuItem itemExit, itemStorageLocation, itemOpenTaskManager, itemNewNote, itemOpenNote, itemBrowserNavigate;
	private Menu menuFile, menuNote, menuTaskManager, menuRecentNote, menuBrowser;
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
        setMenuNote(new Menu("Notes"));
        setMenuBrowser(new Menu("Browser"));
        setMenuTaskManager(new Menu("Task Manager"));
        setMenuRecentNote(new Menu("Recent notes"));
        
        setItemExit(new MenuItem("Exit"));
        setItemNewNote(new MenuItem("New note"));
        setItemOpenNote(new MenuItem("Open note..."));
        setItemOpenTaskManager(new MenuItem("View tasks"));
        setItemStorageLocation(new MenuItem("Change Workspace"));
        setItemBrowserNavigate(new MenuItem("Navigate"));
        
        getMenuFile().getItems().add(itemStorageLocation);
        getMenuFile().getItems().add(new SeparatorMenuItem());
        getMenuFile().getItems().add(itemExit);
        getMenuNote().getItems().add(itemNewNote);
        getMenuNote().getItems().add(itemOpenNote);
        getMenuNote().getItems().add(getMenuRecentNote());
        getMenuBrowser().getItems().add(getItemBrowserNavigate());
        getMenuTaskManager().getItems().add(itemOpenTaskManager);
        getMenuBar().getMenus().add(menuFile);
        getMenuBar().getMenus().add(menuNote);
        getMenuBar().getMenus().add(menuTaskManager);
        getMenuBar().getMenus().add(getMenuBrowser());
        
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

	public Menu getMenuRecentNote() {
		return menuRecentNote;
	}

	public void setMenuRecentNote(Menu menuRecentNote) {
		this.menuRecentNote = menuRecentNote;
	}

	public Menu getMenuBrowser() {
		return menuBrowser;
	}

	public void setMenuBrowser(Menu menuBrowser) {
		this.menuBrowser = menuBrowser;
	}

	public MenuItem getItemBrowserNavigate() {
		return itemBrowserNavigate;
	}

	public void setItemBrowserNavigate(MenuItem itemBrowserNavigate) {
		this.itemBrowserNavigate = itemBrowserNavigate;
	}

}
