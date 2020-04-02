package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {
		
		Corso corso=new Corso("09AQGPG",8,"Economia aziendale", 1);
		Model model = new Model();
		
		System.out.println(model.getTuttiICorsi());
		
		//System.out.println(model.getTuttiStudenti());

		//System.out.println(model.getStudentiIscrittiAlCorso(corso));
		
		//System.out.println(model.getCorsiStudente("146101"));
	}
	

}
