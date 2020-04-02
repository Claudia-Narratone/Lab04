package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.tools.javac.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private ObservableList<String> list=FXCollections.observableArrayList();
	private Model model;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceCorso;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;
    
    @FXML
    private Button btnCheck;
   
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button txtReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	String matricola=txtMatricola.getText();
    	if(model.getCorsiStudente(matricola).equals(null)) {
    		txtResult.setText("Studente non esistente del db");
    	}
    	for(Corso c:model.getCorsiStudente(matricola)) {
    		txtResult.appendText(c.toString()+"\n");
    	}
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtResult.clear();
    	String nomeCorso=choiceCorso.getValue();
    	ArrayList<Studente> studenti=new ArrayList<Studente>();
    	for(Corso c:model.getTuttiICorsi()) {
    		if(c.getNome().equals(nomeCorso)) {
    			studenti.addAll(model.getStudentiIscrittiAlCorso(c)) ;
    		}
    	}
    	for(Studente s:studenti) {
    		txtResult.appendText(s.toString()+"\n");
    	}
    }
    
    @FXML
    void doCheck(ActionEvent event) {
    	String matricola=txtMatricola.getText();
    	
    	if(matricola=="") {
    		txtResult.setText("Inserire matricola per poter completare automaticamente i campi Nome e Cognome");
    		return;
    	}
    	
    	for(Studente s:model.getTuttiStudenti()){
    		if(s.getMatricola().equals(matricola)) {
    			btnCheck.setText("V");
    			txtNome.setText(s.getNome());
    			txtCognome.setText(s.getCognome());
    			return;
    		}
    	}
    	
    	txtResult.setText("Studente non presente nel DB");
    }
    
  

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void initialize() {
        assert choiceCorso != null : "fx:id=\"choiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtReset != null : "fx:id=\"txtReset\" was not injected: check your FXML file 'Scene.fxml'.";
        
        for(Corso c:model.getTuttiICorsi()) {
        	 list.add(c.getNome());
        }
        list.add(" ");
        choiceCorso.setItems(list);
		choiceCorso.setValue(" ");
		

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }

}
