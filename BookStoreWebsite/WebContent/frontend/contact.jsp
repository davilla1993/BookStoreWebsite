<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Book Store</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/contact.css" />

<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<form action="send_email" class="form-signin" method="post">

		<div class="errorMessage alert alert-danger" style="display: none"></div>
		<c:if test="${param.sent eq true}">
			<div class="alert alert-success">Success! Email sent.</div>
		</c:if>
		<c:if test="${param.error == true}">
			<div class="alert alert-danger">Error! Could not send email!</div>
		</c:if>
		<input type="text" name="from" class="form-control from" placeholder="Enter your email address"><br /> 
		<input type="text" name="subject" class="form-control" placeholder="A title of your message"><br />
		<textarea rows="5" name="body" class="form-control" placeholder="Email body"></textarea>
		<br /> <input type="submit" value="Send"
			class="btn btn-lg btn-primary" style="height: 40px;">
	</form>
</body>
<script type="text/javascript">
// source: http://stackoverflow.com/questions/2855865/jquery-regex-validation-of-e-mail-address
function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
    return pattern.test(emailAddress);
};

$(document).ready(function() {
	$(".form-signin").submit(function() {
		var from = $(".from").val();
		var err = $(".errorMessage");
		if(!isValidEmailAddress(from)) {
			err.show();
			err.text("Could not send email, inside From field must be valid email!");
			$(".from").focus();
			return false;
		}
		var to = $(".to").val();
		if(!isValidEmailAddress(to)) {
			err.show();
			err.text("Could not send email, inside To field must be valid email!");
			$(".to").focus();
			return false;
		}
		return true;
	});
});
</script>

<jsp:directive.include file="footer.jsp" />
</html>