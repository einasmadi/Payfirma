<html>
<head>
	<title>Welcome</title>
	<style>
		<#include "css/style.css">
	</style>	
</head>
<body>
    <div id="container">

		<center><h1>Results</h1></center>
		<table id="results">
			<thead>
            	<tr>
                	<th>Name</th>
                    <th>Occupation</th>
                    <th>Phone Number(s)</th>
           		</tr>
     		</thead>
        	<tbody class="table_row">
				<#list contacts as contact>
    				<tr>
    					<td>${contact.name}</td>
    					<td>${contact.occupation}</td>
    					<td>${contact.phone}</td>
    				</tr>
  				</#list>       		
  			</tbody>
		</table>
	</div>
</body>
</html>