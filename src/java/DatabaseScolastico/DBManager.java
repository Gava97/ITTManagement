package DatabaseScolastico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Joker
 */
public class DBManager {
  private final String nomeUtenteDB="root";
  private final String nomeHostDB="localhost";
  private final String pswDB="";
  private final String driver = "com.mysql.jdbc.Driver";
  private final String url = "jdbc:mysql://localhost:3306/db_database_scolastico";
  
  private Connection conn=null;
  
    /**
     *
     * @param args
     */
     
  private void connectDB(){
    try { 
        Class.forName(driver).newInstance(); 
        this.conn = DriverManager.getConnection(url,nomeUtenteDB,pswDB);
    } 
    catch (Exception e) 
    { 
        e.printStackTrace(); 
    }
    
  }
  
  private ResultSet select(String query) throws SQLException{
      //Se non è connesso al DB si connette
      if(this.conn==null||this.conn.isClosed()){
        this.connectDB();
      }
      
      //Questo è ciò che una query tira fuori
      ResultSet res=null;
      try{
        Statement st = conn.createStatement(); 
        res = st.executeQuery(query);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      return res;
  }
 
  
  private boolean insertOrUpdateOrDelete(String query) throws SQLException{
      //Se non è connesso al DB si connette
      if(this.conn==null||this.conn.isClosed()){
        this.connectDB();
      }
      
      //Questo è ciò che una query tira fuori
      boolean res=false;
      try{
        Statement st = conn.createStatement(); 
        st.executeUpdate(query);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      return res;
  }
 
  
  //METODI IMPORTANTI
  //METODI GESTIONE LOG
  
  public boolean  inserisciLOG(String utente_param,String azioneeseguita_param) throws SQLException{
     boolean res=true;
      String query="INSERT INTO log_line (utente,azione_eseguita,datetime_esecuzione) VALUES ('"+utente_param+"','"+azioneeseguita_param+"',NOW())";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
  public ArrayList<LogLine>  cercaLogPerUtente(String utente_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<LogLine> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT utente,datetime_esecuzione,azione_eseguita FROM log_line WHERE utente LIKE '%"+utente_param+"%'  ORDER BY datetime_esecuzione");
     while (query_result.next()) 
        {
            String utente = query_result.getString("utente"); 
            String azione_eseguita = query_result.getString("azione_eseguita"); 
            String datetime_esecuzione = query_result.getString("datetime_esecuzione"); 
            
            res.add(new LogLine(utente,azione_eseguita,datetime_esecuzione));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  
  
  
  public ArrayList<LogLine>  getAllLOGS() throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<LogLine> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT utente,datetime_esecuzione,azione_eseguita FROM log_line ORDER BY datetime_esecuzione");
     while (query_result.next()) 
        { 
            String utente = query_result.getString("utente"); 
            String azione_eseguita = query_result.getString("azione_eseguita"); 
            String datetime_esecuzione = query_result.getString("datetime_esecuzione"); 
            
            res.add(new LogLine(utente,azione_eseguita,datetime_esecuzione));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  
  
  
  
  
  //METODI DI INSERIMENTO UTENTI
  
  public boolean  inserisciStudenteTriennio(int id_utente_param,String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
	 String query="INSERT INTO utente (nome,cognome,cf,tipo_utente,data_nascita,luogo_nascita,provenienza,istituto,residenza) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','studente_triennio','"+dataNascita_param+"','"+dataNascita_param+"','"+luogoNascita_param+"','"+provenienza_param+"','"+istituto_param+"','"+residenza_param+"')";
     
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
  
  
  public boolean  inserisciStudenteBiennio(String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
     String query="INSERT INTO utente (nome,cognome,cf,tipo_utente,data_nascita,luogo_nascita,provenienza,istituto,residenza) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','studente_biennio','"+dataNascita_param+"','"+dataNascita_param+"','"+luogoNascita_param+"','"+provenienza_param+"','"+istituto_param+"','"+residenza_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
   
  
  
  public boolean  inserisciProfessore(String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
	 String query="INSERT INTO utente (nome,cognome,cf,tipo_utente,data_nascita,luogo_nascita,provenienza,istituto,residenza) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','professore','"+dataNascita_param+"','"+luogoNascita_param+"','"+provenienza_param+"','"+istituto_param+"','"+residenza_param+"')";
     
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  
  public boolean  inserisciPersonaleATA(String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
     String query="INSERT INTO utente (nome,cognome,cf,tipo_utente,data_nascita,luogo_nascita,provenienza,istituto,residenza) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','personale_ata','"+dataNascita_param+"','"+luogoNascita_param+"','"+provenienza_param+"','"+istituto_param+"','"+residenza_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
  
  public boolean  aggiungiFileAdUtente(int id_utente_param,String path_file_param) throws SQLException{
     boolean res=true;
      String query="INSERT INTO file (id_utente,path_file) VALUES ("+id_utente_param+",'"+path_file_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
  
  
  //METODI AGGIORNAMENTO UTENTE GIA PRESENTE
  public boolean  aggiornaPersonaleATA(int id_utente_param,String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
      String query="UPDATE utente SET nome='"+nome_param+"', cognome='"+cognome_param+"',data_nascita='"+dataNascita_param+"', luogo_nascita='"+luogoNascita_param+"',residenza='"+residenza_param+"',istituto='"+istituto_param+"',provenienza='"+provenienza_param+"', cf='"+cf_param+"' WHERE id = "+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  public boolean  aggiornaProfessore(int id_utente_param,String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
      String query="UPDATE utente SET nome='"+nome_param+"', cognome='"+cognome_param+"',data_nascita='"+dataNascita_param+"', luogo_nascita='"+luogoNascita_param+"',residenza='"+residenza_param+"',istituto='"+istituto_param+"',provenienza='"+provenienza_param+"', cf='"+cf_param+"' WHERE id = "+id_utente_param;
    this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  public boolean  aggiornaStudenteBiennio(int id_utente_param,String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
      String query="UPDATE utente SET nome='"+nome_param+"', cognome='"+cognome_param+"',data_nascita='"+dataNascita_param+"', luogo_nascita='"+luogoNascita_param+"',residenza='"+residenza_param+"',istituto='"+istituto_param+"',provenienza='"+provenienza_param+"', cf='"+cf_param+"' WHERE id = "+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
  
  
  public boolean  aggiornaStudenteTriennio(int id_utente_param,String nome_param,String cognome_param,String cf_param,String dataNascita_param,String luogoNascita_param,String residenza_param,String istituto_param,String provenienza_param) throws SQLException{
     boolean res=true;
      String query="UPDATE utente SET nome='"+nome_param+"', cognome='"+cognome_param+"',data_nascita='"+dataNascita_param+"', luogo_nascita='"+luogoNascita_param+"',residenza='"+residenza_param+"',istituto='"+istituto_param+"',provenienza='"+provenienza_param+"', cf='"+cf_param+"' WHERE id = "+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  //METODI ELIMINAZIONE UTENTI
  
  
  
  public boolean  eliminaProfessore(int id_utente_param) throws SQLException{
     boolean res=true;
      String query="DELETE FROM file WHERE id_utente="+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      query="DELETE FROM utente WHERE id="+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  
  public boolean  eliminaFile(String path) throws SQLException{
     boolean res=true;
      String query="DELETE FROM file WHERE path_file='"+path+"'";
     this.insertOrUpdateOrDelete(query);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  
  public boolean  eliminaStudenteTriennio(int id_utente_param) throws SQLException{
     boolean res=true;
      String query="DELETE FROM file WHERE id_utente="+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      query="DELETE FROM utente WHERE id="+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  
  public boolean  eliminaPersonaleATA(int id_utente_param) throws SQLException{
     boolean res=true;
      String query="DELETE FROM file WHERE id_utente="+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      query="DELETE FROM utente WHERE id="+id_utente_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
  
  //METODI PER AVERE TUTTI UTENTI
  public ArrayList<Utente>  getAllStudentiTriennio() throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_triennio' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  getAllStudentiBiennio() throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_biennio' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  getAllPersonaleATA() throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='personale_ata' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  getAllProfessori() throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='professore' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  
  //METODI DI RICERCA STUDENTI
  
  public ArrayList<Utente>  cercaStudentiTriennioPerNome(String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_triennio' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaStudentiTriennioPerCognome(String cognome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_triennio' AND cognome LIKE '%"+cognome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaStudentiTriennioPerCognomeENome(String cognome_param,String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_triennio' AND cognome LIKE '%"+cognome_param+"%' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaStudentiBiennioPerNome(String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_biennio' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaStudentiBiennioPerCognome(String cognome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_biennio' AND cognome LIKE '%"+cognome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaStudentiBiennioPerCognomeENome(String cognome_param,String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='studente_biennio' AND cognome LIKE '%"+cognome_param+"%' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  
  
  //METODI DI RICERCA PROFESSORI
  
  public ArrayList<Utente>  cercaProfessoriPerNome(String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='professore' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaProfessoriPerCognome(String cognome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='professore' AND cognome LIKE '%"+cognome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaProfessoriPerCognomeENome(String cognome_param,String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='professore' AND cognome LIKE '%"+cognome_param+"%' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  
  
  //METODI DI RICERCA PERSONALE ATA
  
  public ArrayList<Utente>  cercaPersonaleATAPerNome(String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='personale_ata' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaPersonaleATAPerCognome(String cognome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='personale_ata' AND cognome LIKE '%"+cognome_param+"%' ORDER BY cognome,nome;");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  public ArrayList<Utente>  cercaPersonaleATAPerCognomeENome(String cognome_param,String nome_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     ArrayList<Utente> res=new ArrayList();
     
     ResultSet query_result = this.select("SELECT * FROM utente WHERE tipo_utente='personale_ata' AND cognome LIKE '%"+cognome_param+"%' AND nome LIKE '%"+nome_param+"%' ORDER BY cognome,nome");
     while (query_result.next()) 
        { 
            int id = query_result.getInt("id"); 
            String nome = query_result.getString("nome"); 
            String cognome = query_result.getString("cognome"); 
            String cf = query_result.getString("cf"); 
			String dataNascita=query_result.getString("data_nascita");
			String luogoNascita=query_result.getString("luogo_nascita");
			String residenza=query_result.getString("residenza");
			String istituto=query_result.getString("istituto");
			String provenienza=query_result.getString("provenienza");
            String tipo_utente = query_result.getString("tipo_utente"); 
            res.add(new Utente(id,nome,cognome,cf,tipo_utente,this.prendiPathsDiUtente(id),dataNascita,luogoNascita,residenza,provenienza,istituto));
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     
     return res;
  }
  
  
  //METODO PER AVERE LISTA DEI PATH DEI FILE DI UN UTENTE
  
  
  private List prendiPathsDiUtente(int id_param) throws SQLException{
      //Crea una lista di utenti, che andrà riempita da risultato della query
     List<String> res=new ArrayList<String>();
     
     ResultSet query_result = this.select("SELECT * FROM file WHERE id_utente="+id_param+" ORDER BY path_file");
     while (query_result.next()) 
        { 
            res.add(query_result.getString("path_file"));
        } 
     
         
     return res;
  }
    //------------------------------------------------------------------------------------------------------------------//
  
  //Inserimento amministratori

public boolean  inserisciAdmin(String nome_param,String cognome_param,String cf_param,String password_param, String email_param, String username_param) throws SQLException{
     boolean res=true;
     String query="INSERT INTO amministratore (nome,cognome,cf,tipo_utente,email,password,username) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','-1','"+email_param+"','"+password_param+"','"+username_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
    
public boolean  inserisciPreside(String nome_param,String cognome_param,String cf_param,String password_param, String email_param,String username_param) throws SQLException{
     boolean res=true;
        String query="INSERT INTO amministratore (nome,cognome,cf,tipo_utente,email,password,username) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','0','"+email_param+"','"+password_param+"','"+username_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
  
public boolean  inserisciVicepreside(String nome_param,String cognome_param,String cf_param,String password_param, String email_param,String username_param) throws SQLException{
     boolean res=true;
     String query="INSERT INTO amministratore (nome,cognome,cf,tipo_utente,email,password,username) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','1','"+email_param+"','"+password_param+"','"+username_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
    
public boolean  inserisciSegrAmm(String nome_param,String cognome_param,String cf_param,String password_param, String email_param, String username_param) throws SQLException{
    boolean res=true;
     String query="INSERT INTO amministratore (nome,cognome,cf,tipo_utente,email,password,username) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','2','"+email_param+"','"+password_param+"','"+username_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }
        
        
public boolean inserisciSegrDida(String nome_param,String cognome_param,String cf_param,String password_param, String email_param,String username_param) throws SQLException{
     boolean res=true;
     String query="INSERT INTO amministratore (nome,cognome,cf,tipo_utente,email,password,username) VALUES ('"+nome_param+"','"+cognome_param+"','"+cf_param+"','3','"+email_param+"','"+password_param+"','"+username_param+"')";
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
  }

//ELIMINA amministratori
public boolean  eliminaAmministratore(int username_param) throws SQLException{
     boolean res=true;
     String query="DELETE FROM amministratore WHERE username="+username_param;
     this.insertOrUpdateOrDelete(query);
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return res;
 }



//GET password e ruolo attraverso username

  public String[] getPasswordETipo(String username_param) throws SQLException{
     String dati[] = new String[2];  
     ResultSet query_result = this.select("SELECT password,tipo_utente FROM amministratore WHERE email='"+username_param+"';");
     while (query_result.next()) 
        { 
            String password = query_result.getString("password"); 
            String tipo_utente = query_result.getString("tipo_utente"); 
            
            dati[0]=password;
            dati[1]=tipo_utente;
          
        } 
     
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     return dati;
  }
  
  public String getSha2(String string) throws SQLException{
      String dati = null;
     ResultSet query_result = this.select("SELECT SHA2(\""+string+"\",256)");
     while (query_result.next()) 
        { 
          dati = query_result.getString(1);
            
        }
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
     
     return dati;
  }
  
public String effettuaModifiche(String nome, String cognome, String cf, String s1, String datanascita, String luogonascita, String provenienza, String s2, String residenza) throws SQLException{
      boolean res=true;
      String ris = "Modifiche Effettuate";
      this.connectDB();
      String NomeF = "";String CognomeF = "";String CFF = "";String S1F = "";String DataNascitaF = "";String LuogoNascitaF = "";String ProvenienzaF = "";String S2F = "";String ResidenzaF = "";
      //
      String idQ = "";String nomeQ = "";String cognomeQ = "";String cfQ = "";String s1Q = "";String datanascitaQ = "";String luogonascitaQ = "";String provenienzaQ = "";String s2Q = "";String residenzaQ = "";
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE id='1'");
     while (rs.next()){
        idQ = rs.getString("id");
        nomeQ= rs.getString("nome");
        cognomeQ= rs.getString("cognome");
        cfQ = rs.getString("cf");
        s1Q= rs.getString("tipo_utente");
        datanascitaQ= rs.getString("data_nascita");
        luogonascitaQ = rs.getString("luogo_nascita");
        provenienzaQ= rs.getString("provenienza");
        s2Q= rs.getString("istituto");
        residenzaQ= rs.getString("residenza");
     }
     if(nome.equals("")){
         NomeF = nomeQ;
     }else{
         NomeF = nome;
     }
     
     if(cognome.equals("")){
         CognomeF = cognomeQ;
     }else{
         CognomeF = cognome;
     }
     
     if(cf.equals("")){
         CFF = cfQ;
     }else{
         CFF = cf;
     }
     
     if(s1.equals("campo_vuoto")){
         S1F = s1Q;
     }else{
         S1F = s1;
     }     
          
     if(datanascita.equals("")){
         DataNascitaF = datanascitaQ;
     }else{
         DataNascitaF = datanascita;
     }
     
     if(luogonascita.equals("")){
         LuogoNascitaF = luogonascitaQ;
     }else{
         LuogoNascitaF = luogonascita;
     }
     
     if(provenienza.equals("")){
         ProvenienzaF = provenienzaQ;
     }else{
         ProvenienzaF = provenienza;
     }
     
     if(s2.equals("campo_vuoto2")){
         S2F = s2Q;
     }else{
         S2F = s2;
     }
     
     if(residenza.equals("")){
        ResidenzaF = residenzaQ;
     }else{
         ResidenzaF = residenza;
     }
     
     this.insertOrUpdateOrDelete("UPDATE utente SET id='"+idQ+"', nome='"+NomeF+"', cognome='"+CognomeF+"', cf='"+CFF+"', tipo_utente='"+S1F+"', data_nascita='"+DataNascitaF+"', luogo_nascita='"+LuogoNascitaF+"', provenienza='"+ProvenienzaF+"', istituto='"+S2F+"', residenza='"+ResidenzaF+"' WHERE id = '1'");
      //Chiude la connessione (IMPORTANTE);
      try{
        this.conn.close();
        res=true;
      }
      catch(Exception e){
        res=false;
        e.printStackTrace();
      }
     return ris;
  }
  
}
