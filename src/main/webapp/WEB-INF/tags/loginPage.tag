<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="category" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="js">
	   	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
    	<script src="js/login.js"></script>
	</jsp:attribute>
	<jsp:attribute name="header">
		<t:headerClean />
	</jsp:attribute>
	<jsp:body>
		<div id="inputBlock" ng-app="LoginApp" ng-controller="LoginController">
		<div class="inputHolder">
			<form ng-submit="login()">
				<div class="alert"></div>
				<div class="form-group" ng-class="hasError('name')">
					<label for="name">Username</label>
					<input id="name" ng-model="name" type="text" placeholder="Username" />
				</div>
				<div class="form-group" ng-class="hasError('password')">
					<label for="password">Password</label>
					<input id="password" ng-model="password" type="password" placeholder="Password" />
				</div>
				<button type="submit">Login</button>
			</form>
		</div>
		</div>
	</jsp:body>
</t:genericPage>