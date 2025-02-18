package com.example.miniwebbrowser;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    // Initialize variables
    @FXML
    private WebView webView;

    @FXML
    private TextField textField;

    private WebEngine engine;

    private WebHistory history;

    private String homePage;

    private double webZoom;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        engine = webView.getEngine();
        // Set a default url to load when starting the browser
        homePage = "www.google.com";
        textField.setText(homePage);
        // Initialize a default zoom of 1
        webZoom = 1;
        loadPage();
    }

    public void loadPage(){
        engine.load("http://" + textField.getText());
    }

    public void refreshPage(){
        engine.reload();
    }

    public void zoomIn(){
        webZoom+=.25;
        webView.setZoom(webZoom);
    }

    public void zoomOut(){
        webZoom-=.25;
        webView.setZoom(webZoom);
    }

    public void displayHistory(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        for(WebHistory.Entry entry : entries){
            System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
        }
    }

    public void back(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void forward(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(+1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void executeJS(){
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }
}