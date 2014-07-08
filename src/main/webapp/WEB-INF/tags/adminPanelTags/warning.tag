<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="warning"></c:set>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<c:choose>
  				<c:when test="${settings.serviceMode eq 'true'}">
  					<h1>A Warning occurred</h1> 
					<p>Service Mode is active:  <a href="servicemode.jsp">Change Service Mode</a></p>	
  				</c:when>
  				<c:when test="${settings.serviceMode eq 'false'}">
  						<c:redirect url="/adminPanel/index.jsp"></c:redirect>
  				</c:when>
  			</c:choose>
		</div>
	</jsp:body>
</t:genericPage>