<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="before">
		<jsp:setProperty name="view" property="name" value="Registrieren"/>
	</jsp:attribute>
    <jsp:attribute name="title">Registrieren</jsp:attribute>
    <jsp:attribute name="css">
   		<link href="css/register.css" rel="stylesheet"/>
    </jsp:attribute>
    <jsp:body>
    <form role="form">
        
        	<!-- Input Nickname -->
		  	<div class="form-group">
		  		<label for="name">Nickname</label>
		    	<input type="text" class="form-control" id="name" placeholder="Enter Nichname">
		  	</div>
		  
		  	<div class="register-big-spacer"></div>
		  	
		  	<!-- Input Mail Adress -->
		  	<div class="form-group">
		    	<label for="email">Mail-Adresse</label>
		    	<input type="email" class="form-control" id="email" placeholder="Enter Mail Adress">
				<div class="register-small-spacer"></div>
				<input type="email" class="form-control" id="confirmemail" placeholder="Confirm Mail Adress">		  
		  	</div>
		  
		  	<div class="register-big-spacer"></div>
		  
		  	<!-- Input Password -->
			<div class="form-group">
				<label for="password">Passwort</label>
			    <input type="password" class="form-control" id="password" placeholder="Enter Password">
			    <div class="register-small-spacer"></div>
			    <input type="password" class="form-control" id="confirmpassword" placeholder="Confirm Password">
			</div>
			
			<div class="register-big-spacer"></div>
			
			<!-- Submit Button -->
			<button type="submit" class="btn btn-default">Absenden</button>
			  
	</form>
    </jsp:body>
</t:layout>