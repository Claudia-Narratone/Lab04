package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO dao;
	private StudenteDAO sDao;
	
	
	public Model() {
		dao=new CorsoDAO();
		sDao=new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return dao.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiStudenti(){
		return sDao.getTuttiStudenti();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		Studente studente;
		List<String> matricole=dao.getStudentiIscrittiAlCorso(corso);
		List<Studente> studenti=new ArrayList<Studente>();
		for(String s:matricole) {
			studente=sDao.getStudente(s);
			studenti.add(studente);
		}
		return studenti;
	}
	
	public List<Corso> getCorsiStudente(String matricola){
		Corso corso;
		List<String> codins=sDao.getCorsiStudente(matricola);
		List<Corso> corsi=new ArrayList<Corso>();
		for(String s:codins) {
			if(dao.getCorso(s).equals(null)) {
				return null;
			}
			corso=dao.getCorso(s);
			corsi.add(corso);
		}
		return corsi;
	}
}
