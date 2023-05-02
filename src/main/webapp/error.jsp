<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
	<div>
		<h1>Error</h1>
		<h2><%=exception.getMessage()%></h2>
	</div>
	<div class="grid justify-items-center mt-52">
		<h1 class="font-bold text-gray-700 mt-2 mb-50 text-2xl">ERROR</h1>
		<h2 class="text-gray-400"><%=exception.getMessage()%></h2>
	</div>
</body>
</html>