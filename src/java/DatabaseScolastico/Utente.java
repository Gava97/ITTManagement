/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseScolastico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joker
 */
public class Utente {
    //ATTRIBUTI
    private int _id;
    private String _nome;
    private String _cognome;
    private String _codiceFiscale;
    private String _dataNascita;
    private String _luogoNascita;
    private String _residenza;
    private String _tipoUtente;
    private String _istituto;
    private String _provenienza;
    private List<String> _paths=new ArrayList<String>();
    
    //COSTRUTTORI
    public Utente(int id,String nome, String cognome, String codiceFiscale, String tipoUtente,List paths,String dataNascita,String luogoNascita,String residenza,String provenienza,String istituto){
        this._nome=nome;
        this._cognome=cognome;
        this._codiceFiscale=codiceFiscale;       
        this._paths=paths;
        this._id=id;    
        this._tipoUtente=tipoUtente;
		this._provenienza=provenienza;
		this._dataNascita=dataNascita;
		this._luogoNascita=luogoNascita;
		this._residenza=residenza;
		this._istituto=istituto;
		this._paths=paths;
    }
	
    public Utente(int id,String nome, String cognome, String codiceFiscale, String tipoUtente,String dataNascita,String luogoNascita,String residenza,String provenienza,String istituto){
        this._nome=nome;
        this._cognome=cognome;
        this._codiceFiscale=codiceFiscale;      
        this._tipoUtente=tipoUtente;
        this._id=id;
        this._paths=null;
        this._tipoUtente=tipoUtente;
		this._provenienza=provenienza;
		this._dataNascita=dataNascita;
		this._luogoNascita=luogoNascita;
		this._residenza=residenza;
		this._istituto=istituto;
    }

    
    //SETTER
    public void setNome(String a) {
        this._nome = a;
    }

    public void setCognome(String a) {
        this._cognome = a;
    }

    public void setCodiceFiscale(String a) {
        this._codiceFiscale = a;
    }
    public void setDataNascita(String a) {
        this._dataNascita = a;
    }

    public void setLuogoNascita(String a) {
        this._luogoNascita = a;
    }

    public void setResidenza(String a) {
        this._residenza = a;
    }
    public void setIstituto(String a) {
        this._istituto = a;
    }
    public void setProvenienza(String a) {
        this._provenienza = a;
    }

    public void setPaths(List _paths) {
        this._paths = _paths;
    }

    
    //GETTER
    public String getNome() {
        return _nome;
    }

    public String getCognome() {
        return _cognome;
    }

    public String getCodiceFiscale() {
        return _codiceFiscale;
    }

    public String getDataNascita() {
        return _dataNascita;
    }

    public String getLuogoNascita() {
        return this._luogoNascita;
    }

    public String getResidenza() {
        return this._residenza;
    }
    public String getIstituto() {
        return this._istituto;
    }
    public String getProvenienza() {
        return this._provenienza;
    }
    public List getPaths() {
        return _paths;
    }

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int a) {
        this._id = a;
    }

    /**
     * @return the _tipoUtente
     */
    public String getTipoUtente() {
        return _tipoUtente;
    }

    /**
     * @param _tipoUtente the _tipoUtente to set
     */
    public void setTipoUtente(String a) {
        this._tipoUtente = a;
    }
    
}
