<%@tag import="tinf13b4.forum.beans.SettingsBean"%>
<%@tag import="tinf13b4.forum.model.User"%>
<%@tag import="com.sun.scenario.Settings"%>
<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>
<%@attribute name="js" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="user"></c:set>

<t:genericPage>
<%-- 	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute> --%>	
	<body ng-app="usermanageApp" ng-controller="UsermanageController">
		<div class="admContent">
			<h1>Willkommen</h1> Benutzerverwaltung
			<p>
		    <table ng-table="tableParams" class="table">
	        	<tr ng-init="user in $data">
					<td data-title="'Id'">
	                	<span ng-if="!user.$edit">${user.id }</span>
	               		<div ng-if="user.$edit"><input class="form-control" type="text" ng-model="user.id" /></div>
	            	</td>
		            <td data-title="'Name'" width="200">
		                <span ng-if="!user.$edit">${user.name }</span>
		                <div ng-if="user.$edit"><input class="form-control" type="number" ng-model="user.name" /></div>
		            </td>
		            <td data-title="'Mail'" width="200">
		                <span ng-if="!user.$edit">${user.mail }</span>
	               		<div ng-if="user.$edit"><input class="form-control" type="text" ng-model="user.mail" /></div>
	            	</td>
		            <td data-title="'Picture'" width="200">
		                <span ng-if="!user.$edit">${user.picture }</span>
	               		<div ng-if="user.$edit"><input class="form-control" type="text" ng-model="user.picture" /></div>
	            	</td>
		            <td data-title="'Confirmed'" width="200">
		                <span ng-if="!user.$edit">${user.confirmed }</span>
	               		<div ng-if="user.$edit"><input class="form-control" type="text" ng-model="user.confirmed" /></div>
	            	</td>
		            <td data-title="'Action'" width="200">
		                <a ng-if="!user.$edit" href="" class="btn btn-default btn-xs" ng-click="user.$edit = true">Edit</a>
		                <a ng-if="user.$edit" href="" class="btn btn-primary btn-xs" ng-click="user.$edit = false">Save</a>
		            </td>
	        	</tr>
		    </table>
    		<form method="POST">
				<input ng-submit="submit()" type="submit" class="btn btn-primary"></input>
			</form>
		</p>
	</div>
  </body>
</t:genericPage>