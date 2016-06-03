<%-- 
    Document   : index
    Created on : 15-apr-2016, 9.11.07
    Author     : stefanogava
--%>

<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="DatabaseScolastico.Utente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DatabaseScolastico.DBManager" %>
<!DOCTYPE html>
<html>
    <head>
        <!--<link href="stile.css" rel="stylesheet" type="text/css"/>-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper">
            
        <div class="header">
            <div class="h1">
                Menu'
            </div>
            <div id="logout_button">
                <form action="" method="post">
                    <input type="submit" value="logout" name="logout"/>
                </form>
            </div>
        </div>
        <div class="content">
            <div class="inserimento">
                <h3>Test Inserimento</h3>
            </div>
            <div class="ricerca">
                <h3>Test Ricerca</h3>
            </div>
            <div class="visualizza">
                <h3>Test Visualizzazione</h3>
                <!--<div class="visualizza_file">-->
                <% 
                    DBManager dbmanager = new DBManager();           
                    List<Utente> utenti=dbmanager.getAllProfessori();
                    //utenti=dbmanager.getAllPersonaleATA();
                    int i = 0;
                    //for(int i=0; i<utenti.size(); i++){
                        out.println("nome : "+utenti.get(i).getNome()+"<br>");
                        out.println("cognome : "+utenti.get(i).getCognome()+"<br>");
                        //out.println("fare altro for per paths : " + utenti.get(i).getPaths().+"<br>");
                        for(int j=0;j<utenti.get(i).getPaths().size();j++){
                            out.println("<form action='index.jsp' method='post' name='allegati'>");
                            //out.println("<input type='checkbox' name='elimina' value='"+utenti.get(i).getPaths().get(j)+"'/>");
                            out.println("<input type='hidden' name='idFile' value='"+utenti.get(i).getPaths().get(j)+"'/>");
                            out.println("allegati : <a href='"+utenti.get(i).getPaths().get(j)+"' download>"+utenti.get(i).getPaths().get(j)+"</a><br>");
                            out.println("<input type='submit' name='eliminaFile' value='EliminaAllegato'/>");
                            out.println("</form>");
                        }
                        
                        out.println("id : "+utenti.get(i).getId()+"<br>");
                        out.println("CF : " + utenti.get(i).getCodiceFiscale()+"<br>");
                        out.println("tipo : "+utenti.get(i).getTipoUtente()+"<br>");
                    //}
                %>
                <form action="index.jsp" method="POST">
                    <input type="submit" name="elimina" value="elimina"/>
                </form>
                <%
                    //'personale_ata','professore','studente_biennio','studente_triennio'
                    if(request.getParameter("elimina")!=null){
                        if(utenti.get(i).getTipoUtente().equals("personale_ata"))
                            dbmanager.eliminaPersonaleATA(utenti.get(i).getId());
                        if(utenti.get(i).getTipoUtente().equals("professore"))
                            dbmanager.eliminaProfessore(utenti.get(i).getId());
                        if(utenti.get(i).getTipoUtente().equals("studente_biennio"))
                            dbmanager.eliminaStudenteBiennio(utenti.get(i).getId());
                        if(utenti.get(i).getTipoUtente().equals("studente_triennio"))
                            dbmanager.eliminaStudenteTriennio(utenti.get(i).getId());
                        //dbmanager.inserisciLOG(session.getAttribute("username").toString(),"Eliminazione utente: "+utenti.get(i).getTipoUtente());
                        File f= new File("../docroot/files/"+utenti.get(i).getId()+"/");
                        //File[] a=f.listFiles();
                        //FileUtils.cleanDirectory(f); 
                        //f.delete();
                        dbmanager.deleteFolder(f);
                }
                    
                    if(request.getParameter("eliminaFile")!=null){
                        dbmanager.eliminaFile(request.getParameter("idFile"));
                        File f= new File("../docroot/"+request.getParameter("idFile"));                      
                        f.delete();
                        //dbmanager.inserisciLOG(session.getAttribute("username").toString(),"Eliminazione file: "+"../docroot/"+request.getParameter("idFile")+" -> utente: "+utenti.get(i).getId());
                    }
                %>
                <form action ="index.jsp" method="post">
                    <input type="submit" name="modifica" value="Modifica"/>
                </form> 
                <br>
                <%
                    if(request.getParameter("modifica")!=null){
                        %>
                        <form action="index.jsp" method="post">
                            <input type="text" name="modNome" placeholder="Nome"/>
                            <br>
                            <input type="text" name="modCognome" placeholder="Cognome"/>
                            <br>
                            <input type="text" name="modCF" placeholder="CF"/>
                            <br>
                            <select name="selezione">
                                <option value="campo_vuoto"></option>
                                <option value="studente_biennio">Studente Biennio</option>
                                <option value="studente_triennio">Studente Triennio</option>
                                <option value="professore">Professore</option>
                                <option value="personale_ata">Personale ATA</option>
                            </select>
                            <br>
                            <input type="text" name="modDataNascita" placeholder="Data di Nascita (AAAA-MM-GG)"/>
                            <br>
                            <input type="text" name="modLuogoNascita" placeholder="Luogo di Nascita"/>
                            <br>
                            <input type="text" name="modProvenienza" placeholder="Provenienza"/>
                            <br>
                            <select name="selezione2">
                                <option value="campo_vuoto2"></option>
                                <option value="ITT">ITT</option>
                                <option value="IPSIA">IPSIA</option>
                                <option value="ITE">ITE</option>
                            </select>
                            <br>
                            <input type="text" name="modResidenza" placeholder="Residenza"/>
                            <br>
                            <input type="submit" name="salvaMod" value="Salva"/>
                        </form>
                        <!-- parte nuova -->
                        <h3>File Upload:</h3>
                        Select a file to upload: <br />
                        <form action="UploadFile.jsp" method="post" enctype="multipart/form-data">
                        <input type="file" name="file"/>
                        <br/>
                        <input type="hidden" name="chi" value="<%out.println(utenti.get(i).getId());%>">
                        <!-->
                        <%
                            Integer userid = utenti.get(i).getId();
                            session.setAttribute("userId", userid);
                        %>
                        <!-->
                        <input type="submit" name="upload" value="Upload" />
                        </form>
                        <!-- fine -->
                        <%
                    }
                    if(request.getParameter("salvaMod")!=null){
                        String Nome=request.getParameter("modNome");
                        String Cognome=request.getParameter("modCognome");
                        String CF=request.getParameter("modCF");
                        String S1=request.getParameter("selezione");
                        String DataNascita=request.getParameter("modDataNascita");
                        String LuogoNascita=request.getParameter("modLuogoNascita");
                        String Provenienza=request.getParameter("modProvenienza");
                        String S2=request.getParameter("selezione2");
                        String Residenza=request.getParameter("modResidenza");
                        dbmanager.effettuaModifiche(utenti.get(i).getId(),Nome, Cognome, CF, S1, DataNascita, LuogoNascita, Provenienza, S2, Residenza);
                        //dbmanager.inserisciLOG(session.getAttribute("username").toString(), "Modifica parametro utente :"+utenti.get(i).getId());
                    }
                %>  
                <%
                    //PROVE
                    out.println(System.getProperty("user.dir"));
                    //File creation
                    /*String strPath = "files/1/example.txt";
                    File strFile = new File(strPath);
                    boolean fileCreated = strFile.createNewFile();
                    //File appending
                    Writer objWriter = new BufferedWriter(new FileWriter(strFile));
                    objWriter.write("This is a test");
                    objWriter.flush();

                   objWriter.close();*/
                %> 

            </div>
        </div>
        <div class="footer">
            Credits
        </div>
        </div>
    </body>
</html>
<%
%>