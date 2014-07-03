<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<h1>Welcome</h1>
			to the AdminPanel!
			<div class="dash">
				<div class="dashobj dash1 navGeneral">
					<i class="fa fa-codepen"></i>
					<p>General</p>
				</div>
				<div class="dashobj dash2 navUser">
					<i class="fa fa-user"></i>
					<p>User Management</p>
				</div>
				<div class="dashobj dash3 navManage">
					<i class="fa fa-share-alt-square"></i>
					<p>Forum Management</p>
				</div>
				<div class="dashobj dash4 navSettings">
					<i class="fa fa-cog"></i>
					<p>Settings</p>
				</div>
			</div>
			<br />
		</div>
	</jsp:body>
</t:genericPage>