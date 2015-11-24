<%@page import="fr.paris.lutece.portal.web.LocalVariables" trimDirectiveWhitespaces="true"%>
<jsp:useBean id="filterXpage" scope="session" class="fr.paris.lutece.plugins.profanityfilter.web.FilterXPage" />
<%
	LocalVariables.setLocal( config, request, response );
%>
<%= filterXpage.isSwearWord( request ) %>