<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="admNav">
	<ul>
		<li class="expander">
			<i class="fa fa-bars"></i>
		</li>
		<li class="navIndex">
			<i class="fa fa-tachometer"></i>
			<span class="navPt">AdminPanel</span>
		</li>
		<li class="navGeneral">
			<i class="fa fa-codepen"></i>
			<span class="navPt">Allgemein</span>
		</li>
		<c:if test="${navigation.acpNavigation eq 'general'}">
			<li class="subNav navDescription">
				<span class="navPt">Seitenbeschreibung</span>
			</li>
			<li class="subNav navImpressum">
				<span class="navPt">Impressum</span>
			</li>
			<li class="subNav navTermsofuse">
				<span class="navPt">Nutzungsbestimmungen</span>
			</li>
		</c:if>
		<li class="navUser">
			<i class="fa fa-user"></i>
			<span class="navPt">Benutzerverwaltung</span>
		</li>
		<c:if test="${navigation.acpNavigation eq 'user'}">
			<li class="subNav navUserManage">
				<span class="navPt">Benutzer</span>
			</li>
		</c:if>
		<li class="navManage">
			<i class="fa fa-lock"></i>
			<span class="navPt">Forenverwaltung</span>
		</li>
		<c:if test="${navigation.acpNavigation eq 'boardmanagement'}">
			<li class="subNav navEditCategories">
				<span class="navPt">Foren bearbeiten</span>
			</li>
		</c:if>
		<li class="navSettings">
			<i class="fa fa-cog"></i>
			<span class="navPt">Einstellungen</span>
		</li>
		<c:if test="${navigation.acpNavigation eq 'settings'}">
			<li class="subNav navServiceMode">
				<span class="navPt">Wartungsmodus</span>
			</li>
		</c:if>
	</ul>
</div>