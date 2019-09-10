<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Book Store</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />

<script type="text/javascript" src="js/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
			
			<div align="center">
				<h3>${message}</h3>
			</div>
			<div class="col-md-12">
				<c:if test="${messageSuccess != null}">
					<div class="alert alert-success" role="alert" style="text-align:center">
  						${messageSuccess}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</c:if>
			</div>
			<div class="col-md-12">
				<c:if test="${messageError != null}">
					<div class="alert alert-danger" role="alert" style="text-align:center">
  						${messageError}
  						<span>
  							<a href="view_contact_form">Back</a>
  						</span>
					</div>
				</c:if>
			</div>
			<jsp:directive.include file="footer.jsp" />
			
			<script type="text/javascript" src="js/jquery-3.4.0.min.js" ></script>
			<script type="text/javascript" src="js/bootstrap.min.js"></script>
		</body>

  <script type="text/javascript">
		
			$(document).ready(function(){

				$('#back').click(function(){

					history.go(-1);
					});
				});
		</script>
</html>