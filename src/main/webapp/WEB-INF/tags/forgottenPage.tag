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
    	<script src="js/forgotten.js"></script>
	</jsp:attribute>
	<jsp:attribute name="header">
		<t:headerClean />
	</jsp:attribute>
	<jsp:body>
		<div id="inputBlock" ng-app="ForgottenApp" ng-controller="ForgottenController">
			<div class="inputHolder">
				<form name="forgotten" ng-submit="forgotten()">
				
					<!-- Username -->
					<div class="alert alert-danger" ng-show="!register.username.$valid && register.username.$dirty">
						<span ng-show="register.username.$error.minlength">Username is too short!</span>
						<span ng-show="register.username.$error.maxlength">Username is too long!</span>
						<span ng-show="register.username.$error.pattern">Username contains invalid characters!</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !register.username.$valid && register.username.$dirty}">
						<label for="username">Username</label>
						<input id="username" name="username" ng-model="username" type="text" ng-pattern="/\w|\d|-|_/g" ng-minlength="3" ng-maxlength="15" placeholder="Username"/>
					</div>
					<!-- Username -->
					
					<div class="placeholder" >OR</div>				
					
					<!-- Email Address -->
					<div class="alert alert-danger" ng-show="!register.emailAddress.$valid && register.emailAddress.$dirty">
						<span ng-show="register.emailAddress.$error.pattern">E-Mail address is not valid!</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !register.emailAddress.$valid && register.emailAddress.$dirty}">
						<label for="emailAddress">Email Address</label>
						<input id="emailAddress" name="emailAddress" ng-model="emailAddress" type="email" ng-pattern="/[\w|\d|.|_|%|+|-]+@[\w|\d|.|-]+.[\w]{2,}/g" placeholder="E-mail"/>
					</div>
					<!-- Email Address -->
					
					<button type="submit">New Password</button>
				</form>
			</div>
		</div>
	</jsp:body>
</t:genericPage>