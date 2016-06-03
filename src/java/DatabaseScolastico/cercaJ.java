package DatabaseScolastico;


import DatabaseScolastico.DBManager;
import DatabaseScolastico.DBManager;
import DatabaseScolastico.Utente;
import DatabaseScolastico.LogLine;
import DatabaseScolastico.Utente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacopodenardi
 */
public class cercaJ {
    //variabili per le query
    String nome;
    String cognome;
    //variabili per i risultati 
    List<String> nomeL = new ArrayList<String>();
    List<String> cognomeL = new ArrayList<String>();
    List<String> cfL = new ArrayList<String>();
    List<Integer> idL = new ArrayList<Integer>();
    ArrayList<Utente> res = new ArrayList<Utente>();
    //variabili DB
    //quando instanzo l'oggeto ti connetti direttamente al db
    DBManager dbm = new DBManager();
    
    public cercaJ() {
        
    }
    
    public String stampaMenu(int utente){
        String menu ="";
        menu = menu.concat("<div id='cerca'>");
        menu = menu.concat("<table border='0' width='300' align='left' bgcolor='#E0E0E0'>");
        menu = menu.concat("<form method='post' name='frm' action='search.jsp'>");
        menu = menu.concat("<tr><td colspan=2 style='font-size:12pt;' align='center'>");
        menu = menu.concat("<h3>Ricerca Utente</h3></td></tr>");
        menu = menu.concat("<tr><td ><b>Nome:</b></td>");
        menu = menu.concat("<td> <input type='text' name='nome' id='pid'></td>");
        menu = menu.concat("</tr>");
        menu = menu.concat("<tr><td colspan=2 style='font-size:12pt;' align='center'>");
        menu = menu.concat("<tr><td ><b>Cognome:</b></td>");
        menu = menu.concat("<td> <input  type='text' name='cognome' id='pid'>");
        menu = menu.concat("</td></tr> ");
        menu = menu.concat("<tr> <td>");
        switch (utente) {
            case 0: break;
            case 1: //preside vicepreside                    
                    menu = menu.concat("<input type='radio' name='lavoro' value=1 checked='checked'/>Professori </br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value=2 />Studente biennio  </br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value=3 />Studente triennio</br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value=0 />Personale ATA</br>");
                    /*
                    menu = menu.concat("<input type='radio' name='lavoro' value='prof' checked='checked'/>Professori </br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value='stud_biennio'/>Studente biennio  </br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value='stud_triennio'/>Studente triennio</br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value='ata'/>Personale ATA</br>");
                            */
                break;
            case 2: //Segreteria Amministrativa

                    menu = menu.concat("<input type='radio' name='lavoro' value='1' checked='checked'/>Professori </br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value='0'/>Personale ATA</br>");
                break;
            case 3:  //segreteria didattica                    
                    menu = menu.concat("<input type='radio' name='lavoro' value='2'/>Studente biennio  </br>");
                    menu = menu.concat("<input type='radio' name='lavoro' value='3'/>Studente triennio</br>");
                break;
            default:
                break;
        }
        
        menu = menu.concat("</td></tr> ");
        menu = menu.concat("<tr><td colspan=2 align='center'>");
        menu = menu.concat("<input type='submit' name='submit' value='Cerca'></td></tr>");
        menu = menu.concat("</form>");
        menu = menu.concat("</table>");
        menu = menu.concat("</div>");
        return menu;
    }
    
    public String stampaTabella(int nRis){
        int i=0;
        String tab ="";
        tab = tab.concat("<table>");
        //intestazione
        tab = tab.concat("<tr>");
        tab = tab.concat("<td>");
        tab = tab.concat("id");
        tab = tab.concat("</td>");
        tab = tab.concat("<td>");
        tab = tab.concat("nome");
        tab = tab.concat("</td>");
        tab = tab.concat("<td>");
        tab = tab.concat("cognome");
        tab = tab.concat("</td>");
        tab = tab.concat("<td>");
        tab = tab.concat("codice fiscale");
        tab = tab.concat("</td>");
        tab = tab.concat("</tr>");
        //risultati
        while(i<nRis){
            tab = tab.concat("<tr>");
            tab = tab.concat("<td>");
            tab = tab.concat(idL.get(i).toString());
            tab = tab.concat("</td>");
            tab = tab.concat("<td>");
            tab = tab.concat(nomeL.get(i));
            tab = tab.concat("</td>");
            tab = tab.concat("<td>");
            tab = tab.concat(cognomeL.get(i));
            tab = tab.concat("</td>");
            tab = tab.concat("<td>");
            tab = tab.concat(cfL.get(i));
            tab = tab.concat("</td>");
            tab = tab.concat("<td>");
            tab = tab.concat("<form action='paginadizanna.lol'>");
            tab = tab.concat("<input type=\"submit\" value=\"");
            tab = tab.concat(idL.get(i).toString());
            tab = tab.concat("\" name=\"invia\" />");
            tab = tab.concat("</form>");
            tab = tab.concat("</td>");
            tab = tab.concat("</tr>");
            i++;
        }
        tab = tab.concat("</table>");
        return tab;
    }
    
    public String cerca(int utente, String nomeC, String cognomeC){
        // !! utente da prendere da session.getAttribute("livello")--> 0-Preside, 1-vicepreside, 3-segAmm, 3-segDid
        int k=0;
        switch (utente) {
            case 3://stud triennio--controllre dal checkbox (come avere le var?)
                //controllo se stringa nome o cognome è vuota
                if(nomeC != null && cognomeC != null){
                    try {
                        res = dbm.cercaStudentiTriennioPerCognomeENome(cognomeC, nomeC);
                    } catch (SQLException ex) {
                        Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(nomeC == null){
                        try {
                            //cognome ok
                            res = dbm.cercaStudentiTriennioPerCognome(cognomeC);
                        } catch (SQLException ex) {
                            Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        if(cognomeC == null){
                            try {
                                //nome ok
                                res = dbm.cercaStudentiTriennioPerNome(nomeC);
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{ 
                            try {
                                res = dbm.getAllStudentiTriennio();
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }                
            break;
                            
            case 2://stud biennio
                //controllo se stringa nome o cognome è vuota
                if(nomeC != null && cognomeC != null){
            try {
                res = dbm.cercaStudentiBiennioPerCognomeENome(cognomeC, nomeC);
            } catch (SQLException ex) {
                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
            }
                }else{
                    if(nomeC == null){
                        try {
                            //cognome ok
                            res = dbm.cercaStudentiBiennioPerCognome(cognomeC);
                        } catch (SQLException ex) {
                            Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        if(cognomeC == null){
                            try {
                                //nome ok
                                res = dbm.cercaStudentiBiennioPerNome(nomeC);
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{ 
                            try {
                                res = dbm.getAllStudentiBiennio();
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            break;
                
            case 1://prof
                //controllo se stringa nome o cognome è vuota
                if(nomeC != null && cognomeC != null){
                    try {
                        res = dbm.cercaProfessoriPerCognomeENome(cognomeC, nomeC);
                    } catch (SQLException ex) {
                        Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(nomeC == null){
                        try {
                            //cognome ok
                            res = dbm.cercaProfessoriPerCognome(cognomeC);
                        } catch (SQLException ex) {
                            Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        if(cognomeC == null){
                            try {
                                //nome ok
                                res = dbm.cercaProfessoriPerNome(nomeC);
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{ 
                            try {
                                res = dbm.getAllProfessori();
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
                break;
               
           /* case 0://ata
                //controllo se stringa nome o cognome è vuota
                if(nomeC != "" && cognomeC != ""){
                    //http://stackoverflow.com/questions/4802015/difference-between-null-and-empty-java-string
                    try {
                        res = dbm.cercaPersonaleATAPerCognomeENome(cognomeC, nomeC);
                    } catch (SQLException ex) {
                        Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(nomeC == null){
                        try {
                            //cognome ok
                            res = dbm.cercaPersonaleATAPerCognome(cognomeC);
                        } catch (SQLException ex) {
                            Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(cognomeC == null){
                            try {
                                //nome ok
                                res = dbm.cercaPersonaleATAPerNome(nomeC);
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                    if(nomeC == null && cognomeC == null){
                            try {
                                res = dbm.getAllPersonaleATA();
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }

   
                }   
                break;*/
                case 0://personale ata
                //controllo se stringa nome o cognome è vuota
                if(nomeC != null && cognomeC != null){
                    try {
                        res = dbm.cercaPersonaleATAPerCognomeENome(cognomeC, nomeC);
                    } catch (SQLException ex) {
                        Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(nomeC == null){
                        try {
                            //cognome ok
                            res = dbm.cercaPersonaleATAPerCognome(cognomeC);
                        } catch (SQLException ex) {
                            Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        if(cognomeC == null){
                            try {
                                //nome ok
                                res = dbm.cercaPersonaleATAPerNome(nomeC);
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{ 
                            try {
                                res = dbm.getAllPersonaleATA();
                            } catch (SQLException ex) {
                                Logger.getLogger(cercaJ.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
                break;
                default: break;
        }

        int lung = res.size();
        for(Utente ut : res){
            nomeL.add(ut.getNome());
            cognomeL.add(ut.getCognome());
            cfL.add(ut.getCodiceFiscale());
            idL.add(ut.getId());
        }

        
        return stampaTabella(lung);
    }
     
}
