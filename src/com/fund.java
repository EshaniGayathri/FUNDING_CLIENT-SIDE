package com;

import java.sql.*;

public class fund {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/funddb","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	//insert
	public String insertfund(String fundname, String projrequrment, String companyname, String typeofproject,String Excepteddate,String Amountoffunds, String paymentdesc )
	{
		String output = "";
		System.out.println("insert method called");
			try {
				Connection con = connect();
				if (con == null) {
				return "Error while connecting to the database";
			}
				
				
				// create a prepared statement
				String query = " insert into fund_proposal_t(`fundID`,`fundName`,`projRequrment`,`companyName`,`typeOfProject`,`ExceptedDate`,`AmountOfFunds`,`paymentDesc`) "
		 		+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		 

			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fundname);
			preparedStmt.setString(3, projrequrment);
			preparedStmt.setString(4, companyname);
			preparedStmt.setString(5, typeofproject);
			preparedStmt.setString(6, Excepteddate);
			preparedStmt.setDouble(7, Double.parseDouble(Amountoffunds));
			preparedStmt.setString(8, paymentdesc);
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newfunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newfunds + "\"}";

		} catch (Exception e) {
			
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//read
	public String readFunds() 
	{
		String output = "";
			try {
				Connection con = connect();
				if (con == null) { 
					
				return "Error while connecting to the database for reading.";
			}
				
				
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Owner of Funds</th><th>Project Requirments</th>" +"<th>Company Name</th>"+
					"<th>Type of project</th>" +
					"<th>Excepted Date</th>"+ 
					"<th>Amount Of Funds</th>"+ 
					"<th>Payments description</th></tr>";
			
			String query = "select * from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String fundID = Integer.toString(rs.getInt("fundID"));
				String fundName = rs.getString("fundName");
				String projRequrment = rs.getString("projRequrment");
				String companyName = rs.getString("companyName");
				String typeOfProject = rs.getString("typeOfProject");
				String ExceptedDate = rs.getString("ExceptedDate");
				String AmountOfFunds = Double.toString(rs.getDouble("AmountOfFunds"));
				String paymentDesc = rs.getString("paymentDesc");
				
				// Add into the html table
				output += "<tr><td>" + fundName + "</td>";
				output += "<td>" + projRequrment + "</td>";
				output += "<td>" + companyName + "</td>";
				output += "<td>" + typeOfProject + "</td>";
				output += "<td>" + ExceptedDate + "</td>";
				output += "<td>" + AmountOfFunds + "</td>";
				output += "<td>" + paymentDesc + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				+		"<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + fundID + "'>" + "</form></td></tr>";
			}
			
			
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the Funds Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//delete
	public String deleteFund(String fundID)
	{
		System.out.println("delete method called");
		String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					
				return "Error while connecting to the database for deleting.";
			}
				

				// create a prepared statement
				String query = "delete from fund_proposal_t where fundID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setInt(1, Integer.parseInt(fundID));
				
				// execute the statement
				
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
				
			
			String newFunds = readFunds(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newFunds + "\"}"; 
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the fund.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//update
	public String updateFund(String ID,String fundname, String projrequrment, String companyname, String typeofproject,String Excepteddate,String Amountoffunds, String paymentdesc )
	{
		String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					
				return "Error while connecting to the database for updating.";
			}
				
				
				// create a prepared statement
				 
				 String query = "UPDATE fund_proposal_t SET fundName=?,projRequrment=?,companyName=?,typeOfProject=?,ExceptedDate=?,AmountOfFunds=?,paymentDesc=? WHERE fundID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 
				 preparedStmt.setString(1, fundname);
				 preparedStmt.setString(2, projrequrment);
				 preparedStmt.setString(3, companyname);
				 preparedStmt.setString(4, typeofproject);
				 preparedStmt.setString(5, Excepteddate);
				 preparedStmt.setDouble(6, Double.parseDouble(Amountoffunds));
				 preparedStmt.setString(7, paymentdesc);
				 preparedStmt.setInt(8, Integer.parseInt(ID));
				 
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
			
			
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
		} catch (Exception e) {
			
			output = "{\"status\":\"error\", \"data\":\"Error while updating the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}