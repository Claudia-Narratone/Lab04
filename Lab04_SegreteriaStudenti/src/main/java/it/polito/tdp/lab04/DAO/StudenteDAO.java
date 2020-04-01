package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	private List<Studente> studenti=new ArrayList<Studente>();
	
	public List<Studente> getTuttiStudenti(){
		
		final String sql = "SELECT * FROM studente ";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				String matricola=rs.getString("matricola");
				String cognome=rs.getString("cognome");
				String nome=rs.getString("nome");
				String cds=rs.getString("cds");
				
				System.out.println(matricola + " " + cognome + " " + nome + " " + cds);
				
				Studente s=new Studente(matricola, cognome, nome, cds);
				studenti.add(s);
			}
			
			conn.close();
			
			return studenti;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
	
	public Studente getStudente(String matricola) {
		for(Studente s:studenti) {
			if(s.getMatricola().equals(matricola))
				return s;
		}
		return null;
	}
	

}
