<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="manageprofanityfilter" scope="session" class="fr.paris.lutece.plugins.profanityfilter.web.ManageProfanityfilterJspBean" />

<% manageprofanityfilter.init( request, manageprofanityfilter.RIGHT_MANAGEPROFANITYFILTER ); %>
<%= manageprofanityfilter.getManageProfanityfilterHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
