<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suche</title>
</head>
<body>
 <jsp:useBean id="Searchbean" class="search.Searchbean"/>
 
<form method="get" action="Search.jsp">
<jsp:setProperty name="Searchbean" property="what" param="what"/>
<jsp:setProperty name="Searchbean" property="where" param="where"/>

<table>
      <tr>
          <td>Search for</td>
          <td>
           </td>
           <td>Found</td>
       </tr>
       <tr>
           <td>
            
              <Input type="text" name="what" value="${Searchbean.what}"/>
              </td>
              <td>
              <label name="where" for="search_type">Search in:</label>
			  <select id="search_type" class="primary" onchange="change_type()" name="where">
		      <option class="" value=1>Threads</option>
			  <option class="" value=2>User</option>
			  <option class="" value=3>Categories</option>
			  
			
              
             </td>
         <td>=
            <jsp:getProperty name="Searchbean" property="result"/>
                   </td>  
            </tr>
        <tr><td>
         <button name="startsearch" type="submit">Search</button>
          </table>
            
          

</form>
</body>
</html>