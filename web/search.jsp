<%-- 
    Document   : search
    Created on : 24-mag-2016, 9.20.07
    Author     : jacopodenardi
--%>

<%@page import="DatabaseScolastico.Utente"%>
<%@page import="DatabaseScolastico.cercaJ"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Utente selezionato</h1>
        <%
            cercaJ x = new cercaJ();
            int utente = 4;     //prendere dalla sessione (in teoria), messo statico solo per fare i test 
                try{ 
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    utente = Integer.decode(request.getParameter("lavoro")) ;     //prendere dalla sessione (in teoria), messo statico solo per fare i test 
                    out.println(x.cerca(utente, nome, cognome));
                }catch(NumberFormatException ex){ }

        %>
        
    </body>
</html>
