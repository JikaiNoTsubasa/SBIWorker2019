package fr.triedge.worker.ui;

import javafx.concurrent.Worker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class Browser extends BorderPane{

	private WebView webView;
	private TextField urlField;
	private String currentURL = "http://google.fr";
	
	public void buildUI() {
		setWebView(new WebView());
		setUrlField(new TextField(getCurrentURL()));
		getWebView().getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED.equals(newValue)) {
                getUrlField().setText(getWebView().getEngine().getLocation());
            }
        });
		getUrlField().setOnKeyPressed(e -> actionOnURLBar(e));
		setTop(getUrlField());
		setCenter(getWebView());
		navigate(getCurrentURL());
	}
	
	private void actionOnURLBar(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			navigate(getUrlField().getText());
			System.out.println("Pressed: "+getUrlField().getText());
		}
	}

	public void navigate(String url) {
		getWebView().getEngine().load(url);
	}

	public WebView getWebView() {
		return webView;
	}

	public void setWebView(WebView webView) {
		this.webView = webView;
	}

	public String getCurrentURL() {
		return currentURL;
	}

	public void setCurrentURL(String currentURL) {
		this.currentURL = currentURL;
	}

	public TextField getUrlField() {
		return urlField;
	}

	public void setUrlField(TextField urlField) {
		this.urlField = urlField;
	}
}
