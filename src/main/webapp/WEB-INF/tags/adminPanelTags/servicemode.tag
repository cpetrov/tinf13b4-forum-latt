<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="settings"/>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:attribute name="js">
		<!-- <script src="/js/sevicemode.js"></script> -->
	</jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<c:if test="${not empty param.serviceReason }">
				<c:set target="${settings}" property="serviceReason" value="${param.serviceReason}"></c:set>
				<c:set target="${settings}" property="serviceMode" value="${not empty param.onoffswitch? true : false}"></c:set>
			</c:if>		
			<form method="POST">
				<h1>Wartungsmodus</h1>
					<p>
						Wartungsmodus bearbeiten:
					</p>
				<div class="onoffswitch">
					<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch"
						${settings.serviceMode ? 'checked' : ''} />
					<label class="onoffswitch-label" for="myonoffswitch"> 
						<span class="onoffswitch-inner"></span> <span class="onoffswitch-switch"></span>
					</label>
				</div>
				<p>
					<textarea id="service_reason" name="serviceReason" cols="100" rows="10" disabled="${!settings.serviceMode}">
					${settings.serviceReason}
					</textarea>
				</p>
				<button id="save" type="submit" class="navButton">Speichern</button>
			</form>			
		</div>
	</jsp:body>
</t:genericPage>