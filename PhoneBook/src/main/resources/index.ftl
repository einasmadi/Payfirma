<!DOCTYPE html>
<html>
  <head>
    <title>Contacts</title>
	<style>
		<#include "css/style.css">
	</style>	
    <script src="js/script.js"></script>
  </head>
  <body>
    
    <div id="container">
      <header>
        <h1>Add, Find, Update or Delete a contact</h1>
      </header>
      <section>
      <br>
      <div id="results"></div>
        
        <form method="POST">
          
          <table>
            <tr>
              <th>Name:</th>
              <th><input type="text" name="name" value="${name!""}"></th>
            </tr>
            <tr>
              <th>Occupation:</th>
              <th><input type="text" name="occupation" value="${occupation!""}"></th>
            </tr>
            <tr>
              <th>Phone :</th>
              <th><input type="text" name="phone" value="${phone!""}"></th>
            </tr>
          </table>
          
          <br><br>
          <table>
          	<tr>
          		<td><input type="submit" name="action" value="Add" /></td>
          		<td><input type="submit" name="action" value="Find" /></td>
          		<td><input type="submit" name="action" value="Update" /></td>
          		<td><input type="submit" name="action" value="Delete" /></td>
          	</tr>
          </table>
        </form>
      </section>
      <footer>
        <p>Copyright &copy; 2017; All rights reserved.</p>
      </footer>
    </div>
  </body>
</html>