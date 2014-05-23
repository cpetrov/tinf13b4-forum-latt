<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="before">
		<jsp:setProperty name="view" property="name" value="Registrieren"/>
	</jsp:attribute>
    <jsp:attribute name="title">Registrieren</jsp:attribute>
    <jsp:body>
    <form role="form">
        
        	<!-- Input Nickname -->
		  	<div class="form-group">
		  		<label for="name">Nickname</label>
		    	<input type="text" class="form-control" id="name" placeholder="Enter Nichname">
		  	</div>
		  
		  	<!-- Input Mail Adress -->
		  	<div class="form-group">
		    	<label for="email">Mail-Adresse</label>
		    	<input type="email" class="form-control" id="email" placeholder="Enter Mail Adress">
				<div class="register-spacer"></div>
				<input type="email" class="form-control" id="confirmmail" placeholder="Enter Mail Adress">		  
		  	</div>
		  
		  	<!-- Input Password -->
			<div class="form-group">
				<label for="password">Passwort</label>
			    <input type="password" class="form-control" id="password" placeholder="Enter Password">
			    <div class="register-spacer"></div>
			    <input type="password" class="form-control" id="confirmpassword" placeholder="Enter Password">
			</div>
			
			<!-- Submit Button -->
			<button type="submit" class="btn btn-default">Absenden</button>
			  
	</form>
    </jsp:body>
</t:layout>