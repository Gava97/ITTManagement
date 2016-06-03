<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DatabaseScolastico.Utente"%>
<%@page import="DatabaseScolastico.DBManager"%>
<%@page import="DatabaseScolastico.cercaJ"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        

    <body>
        <%
            cercaJ x = new cercaJ();
            int utente = 0;
            try{
                utente = Integer.parseInt(request.getParameter("bho"));
                if(request.getParameter("selez") != null){   //in base al tipo di utente stampo il menu
                    out.println(x.stampaMenu(utente));
                }
            }catch(NumberFormatException ex){ }
            
                try{ 
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    out.println(nome);
                    out.println(cognome);
                    //DBManager database = new DBManager();
                    //List<Utente> ar = new ArrayList<Utente>();
                    //         ar= database.cercaPersonaleATAPerCognomeENome("lol", "lol");
                    
                   // out.println(x.cerca(utente, nome, cognome));
                }catch(NumberFormatException ex){ }
            
            
            
        %>
            
        <br/><br/>
        <div id="selez">
            <form action="" method="post" name="utente">
                <select name="bho">
                    <option value="">-----</option>
                    <option value="1">Preside/Vicepreside</option> <!-- tutto     PRESIDE-->
                    <option value="3">Segreteria Didattica</option> <!-- studenti     SG1-->
                    <option value="2">Segreteria Amministrativa</option>  <!--dipendenti      SG2-->
                </select> 
                <input type="submit" name="selez" value="selez" >
            </form>
        </div>


        <div id="visualizza">

        </div>
    </body>
</html>
