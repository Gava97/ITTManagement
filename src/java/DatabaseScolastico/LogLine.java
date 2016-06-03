/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseScolastico;

/**
 *
 * @author Joker
 */
public class LogLine {
    private String _utente;
    private String _azione_eseguita;
    private String _datetime_esecuzione;
    
    //Costruttori
    
    public LogLine(String utente,String azione_eseguita,String datetime_esecuzione){
        this._azione_eseguita=azione_eseguita;
        this._datetime_esecuzione=datetime_esecuzione;
        this._utente=utente;
        
    }
    
    
    /**
     * @return the _utente
     */
    public String getUtente() {
        return _utente;
    }

    /**
     * @param _utente the _utente to set
     */
    public void setUtente(String _utente) {
        this._utente = _utente;
    }

    /**
     * @return the _azione_eseguita
     */
    public String getAzione_eseguita() {
        return _azione_eseguita;
    }

    /**
     * @param _azione_eseguita the _azione_eseguita to set
     */
    public void setAzione_eseguita(String _azione_eseguita) {
        this._azione_eseguita = _azione_eseguita;
    }

    /**
     * @return the _datetime_esecuzione
     */
    public String getDatetime_esecuzione() {
        return _datetime_esecuzione;
    }

    /**
     * @param _datetime_esecuzione the _datetime_esecuzione to set
     */
    public void setDatetime_esecuzione(String _datetime_esecuzione) {
        this._datetime_esecuzione = _datetime_esecuzione;
    }
    
  
}
