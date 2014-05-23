<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="before">
		<jsp:setProperty name="view" property="name" value="Registrieren"/>
	</jsp:attribute>
    <jsp:attribute name="title">Registrieren</jsp:attribute>
    <jsp:attribute name="js">
    	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
    	<script src="js/register.js"></script>
    </jsp:attribute>
    <jsp:body>
   		<div class="row" ng-app="RegisterApp" ng-controller="RegisterController">
   			<div class="col-md-4 center-block">
   				<form ng-submit="register()">
   					<div class="form-group" ng-class="hasError('name')">
	   					<label for="name" class="control-label">Benutzername</label>
	   					<input id="name" type="text" class="form-control" placeholder="Benutzername" ng-model="name">
   					</div>
   					<div class="form-group" ng-class="hasError('email')">
	   					<label for="email" class="control-label">E-Mail</label>
	   					<input id="email" type="text" class="form-control" placeholder="E-Mail" ng-model="email">
   					</div>
   					<div class="form-group" ng-class="hasError('password')">
	   					<label for="password" class="control-label">Passwort</label>
	   					<input id="password" type="password" class="form-control" placeholder="Passwort" ng-model="password">
	   				</div>
   					<div class="form-group" ng-class="hasError('confirm')">
	   					<label for="confirm" class="control-label">Passwort wiederholen</label>
	   					<input id="confirm" type="password" class="form-control" placeholder="Passwort wiederholen" ng-model="confirm">
	   				</div>
   					<hr></hr>
   					<button type="submit" class="btn btn-primary btn-block" ng-disabled="isLoading">
   						<strong>Registrieren</strong>
   					</button>
   				</form>
   			</div>
   		</div>
    </jsp:body>
</t:layout>