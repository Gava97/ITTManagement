<%-- 
    Document   : index
    Created on : 6-mag-2016, 9.45.28
    Author     : michaeldellalibera
--%>

<%@page import="DatabaseScolastico.DBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getParameter("registra") != null) {

                String nome;
                nome = request.getParameter("nome");
                String cognome;
                cognome = request.getParameter("cognome");
                String cf;
                cf = request.getParameter("cf");
                String tipo_utente;
                tipo_utente = request.getParameter("tipo_utente");
                String data_nascita;
                data_nascita = request.getParameter("data_nascita");
                String luogo_nascita;
                luogo_nascita = request.getParameter("luogo_nascita");
                String residenza;
                residenza = request.getParameter("residenza");
                String istituto;
                istituto = request.getParameter("istituto");
                String provenienza;
                provenienza = request.getParameter("provenienza");

                if (tipo_utente.equals("personale_ata")) {
                    DBManager db = new DBManager();
                    db.inserisciPersonaleATA(nome, cognome, cf, data_nascita, luogo_nascita, residenza, istituto, provenienza);
                } else if (tipo_utente.equals("preside")) {
                    DBManager db = new DBManager();
                    db.inserisciPersonaleATA(nome, cognome, cf, tipo_utente, luogo_nascita, residenza, istituto, provenienza);
                } else if (tipo_utente.equals("studente_triennio")) {
                    DBManager db = new DBManager();
                    db.inserisciPersonaleATA(nome, cognome, cf, tipo_utente, luogo_nascita, residenza, istituto, provenienza);
                }
            }

        %>
        <div id="reg">
            <form action="" method="post">
                <label for="nome">Nome:
                    <input type="text" name="nome" required><br>
                    <label for="cognome">Cognome:
                        <input type="text" name="cognome" required><br>
                        <label for="cf">Codice fiscale:
                            <input type="text" name="cf" required><br>	
                            <label for="tipo_utente">Tipo utente:
                                <input type="radio" name="tipo_utente" value="personale_ata">Personale ATA
                                <input type="radio" name="tipo_utente" value="professore">Professore
                                <input type="radio" name="tipo_utente" value="studente_biennio">Studente Biennio
                                <input type="radio" name="tipo_utente" value="studente_triennio">Studente Triennio<br>
                                <label for="data_nascita">Data di nascita:
                                    <input type="date" name="data_nascita" required><br>	
                                    <label for="luogo_nascita">Luogo di nascita:
                                        <input type="text" name="luogo_nascita" required><br>	
                                        <label for="residenza">Residenza:
                                            <input type="text" name="residenza" required><br>	
                                            <label for="residenza">Provenienza:
                                                <input type="text" name="provenienza" required><br>	
                                                <label for="istituto">Istituto:
                                                    <input type="radio" name="istituto" value="itt">ITT
                                                    <input type="radio" name="istituto" value="ipsia">IPSIA
                                                    <input type="radio" name="istituto" value="ite">ITE
                                                    provenienza<br>
                                                    <input type="submit" name="registra" value="Registra">
                                                    </form>
                                                    </div>
                                                    </body>
                                                    </html>



