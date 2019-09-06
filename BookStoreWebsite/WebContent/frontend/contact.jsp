<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>Evergreen Books - Contact Us</title>
			<link rel="stylesheet" href="css/bootstrap.min.css" />
			<link rel="stylesheet" href="css/style.css" />
		</head>
<body>
	<jsp:directive.include file="header.jsp" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<div class="container">
		<div>
			<c:if test="{result eq 1}">
				<div class="alert alert-success" role="alert">
  					The message have been sent successfully. Thank You!
				</div>
			</c:if>
		</div>
		<div>
			<c:if test="{result eq -1}">
				<div class="alert alert-danger" role="alert">
  					Oops!! Something wrong is happened.
				</div>
			</c:if>
		</div>
		
		<div class="col-lg-10 col-lg-offset-1">
			<form id="contact" action="send_email" method="post">
				<div class="form-row"> 
					<div class="form-group col-md-6">
						<label for="firstname">Firstname</label> <input type="text"
							class="form-control" id="firstname" name="firstname"
							placeholder="Firstname" />
					</div>
					<div class="form-group col-md-6">
						<label for="lastname">Lastname</label> <input type="text"
							class="form-control" id="lastname" name="lastname"
							placeholder="Lastname" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email" placeholder="Email" />
					</div>
					<div class="form-group col-md-6">
						<label for="telephone">Telephone</label> <input type="text"
							class="form-control" id="telephone" name="telephone"
							placeholder="Telephone" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="title">Title</label> <input type="text"
							class="form-control" id="title" name="title" placeholder="Title" />
					</div>
					<div class="form-group col-md-12">
						<label for="message">Message</label>
						<textarea class="form-control" id="message" name="message"
							rows="4" placeholder="Message..."></textarea>
					</div>
				</div>
				<div class="col-md-6">
					<input type="submit" class="btn btn-success" value="Send" style="height:35px"/>
					<button type="button" class="btn btn-secondary" id="buttonCancel">Cancel</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript" src="js/jquery-3.4.0.min.js" ></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<!-- <script type="text/javascript" src="js/bootstrap.min.js"></script> -->
</body>
<script type="text/javascript">

	$(document).ready(function(){

		$("#contact").validate({
	
			rules:{

				firstname:{
					required:true,
					minlength: 2
					},
					
				lastname:{
					required:true,
					minlength:2
					},

				email:{
					required:true,
					email:true
				},

				telephone:{

					required:true,
					number:true,
					minlength:6
				},
	
				title:"required",
				message:"required"
			},

			messages:{

				firstname:{
					required:"Please, enter your firstname",
					minlength:"Minimum 2 letters"
				},
					
				lastname:{
					required:"Please, enter your lastname",
					minlength: "Minimum 2 letters"
					},

				email:{
					required:"Please, enter your e-mail address",
					email:"Please, enter a valid e-mail address"
				},

				telephone:{

					required:"Enter your phone number",
					number:"Phone number must be numeric",
					minlength:"Minimum 6 digits are required"
				},

				title:"Please, enter a title",
				message:"Message field could not be empty"

			}
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});

	});
</script>
</html>