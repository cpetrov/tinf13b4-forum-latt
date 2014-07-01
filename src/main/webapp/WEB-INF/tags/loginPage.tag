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
				
					<!-- Username -->
					<div class="alert alert-danger" ng-show="!login.username.$valid && login.username.$dirty">
						<span ng-show="login.username.$error.required">Tell us your Username.</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !login.username.$valid && login.username.$dirty}">
						<label for="username">Username</label>
						<input id="username" name="username" ng-model="username" type="text" placeholder="Username" required="required" />
					</div>
					<!-- Username -->
					
					<!-- Password -->
					<div class="alert alert-danger" ng-show="!login.password.$valid && login.password.$dirty">
						<span ng-show="login.password.$error.required">Tell us your password.</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !login.password.$valid && login.password.$dirty}">
						<label for="password">Password</label>
						<input id="password" name="password" ng-model="password" type="password" placeholder="Password" required="required" />
					</div>
					<!-- Password -->
					
					<a href="forgotten.jsp">Forgot Password</a>
						
					<button type="submit">Login</button>
				</form>
			</div>
		</div>
	</jsp:body>
</t:genericPage>
