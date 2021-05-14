<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Student details</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/main.js"></script>
	</head>
<body>
<div class="container">
<div class="row">
<div class="col-8">
 
 <h1 >Funding Details</h1>
 
	<form id="formFund" name="formFund">
		Owner Of funds:  <input id="fname" name="fname" type="text" class="form-control"><br> 
		
		Project Requiremnets: <input id="frequirment" name="frequirment" type="text" class="form-control"><br>
		
		Name of Company:<input id="fcompany" name="fcompany" type="text" class="form-control"><br> 
		 
		Type of project:<input type="ftype" id="ftype" name="txtName" class="form-control"><br> 
	
		Excepted date: <input id="fdate" name="fdate" type="text" class="form-control"><br> 
	
	 	Amount of fund: <input id="famount" name="famount" type="text" class="form-control"><br>
	
	 	Payment Description: <input id="fdescription" name="fdescription" type="text" class="form-control"><br>	
	 
		 
 
	<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input 
						id="hidFundIDSave" name="hidFundIDSave" value="">
						
		</form>
		
		
		<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

			</div>

			<br>

			<div id="divFundsGrid">
				<%
				fund fundObj = new fund();
				out.print(fundObj.readFunds());
				%>

			</div>
		</div>
	</div>
		
</body>
</html>