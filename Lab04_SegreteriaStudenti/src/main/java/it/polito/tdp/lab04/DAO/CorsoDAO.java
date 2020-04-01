package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	private List<Corso> corsi = new LinkedList<Corso>();
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Corso corso=new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(corso);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codIns) {
		for(Corso c:corsi) {
			if(c.getCodins().equals(codIns))
				return c;
		}
		return null;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<String> getStudentiIscrittiAlCorso(Corso corso) {
		String codins=corso.getCodins();
		
		List<String> matricole=new ArrayList<String>();
		
		String sql="SELECT matricola FROM iscrizione WHERE codins=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String matricola=rs.getString("matricola");
				matricole.add(matricola);
			}
			
			
			conn.close();
			
			return matricole;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
