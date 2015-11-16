<jsp:useBean id="manageprofanityfilterWord" scope="session" class="fr.paris.lutece.plugins.profanityfilter.web.WordJspBean" />
<% String strContent = manageprofanityfilterWord.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
