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
    	<script src="js/register.js"></script>
	</jsp:attribute>
	<jsp:attribute name="header">
		<t:headerClean />
	</jsp:attribute>
	<jsp:body>
		<div id="inputBlock" ng-app="RegisterApp" ng-controller="RegisterController">
			<div class="inputHolder">
				<form name="register" ng-submit="submit()">
				
					<!-- Username -->
					<div class="alert alert-danger" ng-show="!register.username.$valid && register.username.$dirty">
						<span ng-show="register.username.$error.required">Tell us your Username.</span>
						<span ng-show="register.username.$error.minlength">Username is too short!</span>
						<span ng-show="register.username.$error.maxlength">Username is too long!</span>
						<span ng-show="register.username.$error.pattern">Username contains invalid characters!</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !register.username.$valid && register.username.$dirty}">
						<label for="username">Username</label>
						<input id="username" name="username" ng-model="username" type="text" ng-pattern="/\w|\d|-|_/g" ng-minlength="3" ng-maxlength="15" placeholder="Username" required="required" />
					</div>
					<!-- Username -->
					
					<!-- Email Address -->
					<div class="alert alert-danger" ng-show="!register.emailAddress.$valid && register.emailAddress.$dirty">
						<span ng-show="register.emailAddress.$error.required">Tell us your E-Mail address.</span>
						<span ng-show="register.emailAddress.$error.pattern">E-Mail address is not valid!</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !register.emailAddress.$valid && register.emailAddress.$dirty}">
						<label for="emailAddress">Email Address</label>
						<input id="emailAddress" name="emailAddress" ng-model="emailAddress" type="email" ng-pattern="/[\w|\d|.|_|%|+|-]+@[\w|\d|.|-]+.[\w]{2,}/g" placeholder="E-mail" required="required" />
					</div>
					<!-- Email Address -->
					
					<!-- Password -->
					<div class="alert alert-danger" ng-show="!register.password.$valid && register.password.$dirty">
						<span ng-show="register.password.$error.required">Tell us your password.</span>
						<span ng-show="register.password.$error.minlength">Password is too short!</span>
						<span ng-show="register.password.$error.maxlength">Password is too long!</span>
						<span ng-show="register.password.$error.pattern">Password does not fullfill security requirements! Use 1x big letter, 1x small letter and one of these @,#,$,%</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !register.password.$valid && register.password.$dirty}">
						<label for="password">Password</label>
						<input id="password" name="password" ng-model="password" type="password" ng-pattern="/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%])/g" ng-minlength="8" ng-maxlength="20" placeholder="Password" required="required" />
					</div>
					<!-- Password -->
					
					<!-- Confirm-->
					<div class="alert alert-danger" ng-show="confirm !== password && register.confirm.$dirty">
						<span ng-show="confirm !== password">Confirm your password!</span>
						<span ng-show="register.confirm.$error.required">Tell us your password again.</span>
					</div>
					<div class="form-group" ng-class="{'has-error': !register.confirm.$valid && register.confirm.$dirty}">
						<label for="confirm">Repeat password</label>
						<input id="confirm" name="confirm" ng-model="confirm" type="password" placeholder="Repeat password" required="required" />
					</div>
					<!-- Confirm -->
					
					<button type="submit">Register</button>
				</form>
			</div>
		</div>
	</jsp:body>
</t:genericPage>