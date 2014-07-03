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
			<form name="login" ng-submit="login()">
				<div class="alert">{{login.input.$error}}</div>
				<div class="form-group" ng-class="hasError('username')">
					<label for="username">Username</label>
					<input id="username" ng-model="username" type="text" ng-pattern="^[A-Za-z0-9_-]" ng-minlength="3" ng-maxlength="15" placeholder="Username" />
				</div>
				<div class="form-group" ng-class="hasError('password')">
					<label for="password">Password</label>
					<input id="password" ng-model="password" type="password" ng-pattern="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%])." ng-minlength="8" ng-maxlength="20" placeholder="Password" />
					<a href="forgotten.jsp">Forgot Password?</a>
				</div>
				<button type="submit">Login</button>
			</form>
		</div>
		</div>
	</jsp:body>
</t:genericPage>
