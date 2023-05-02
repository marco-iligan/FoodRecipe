<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.entity.Category"%>
<%
HttpServletResponse httpResponse = (HttpServletResponse)response;
httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if (session.getAttribute("username") == null) {
	response.sendRedirect("login.jsp");
}
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script src="https://cdn.tailwindcss.com"></script>
<title>FoodRecipe</title>
</head>

<body>
	<nav class="flex justify-between px-20 py-10 items-center bg-white">
		<h1 class="text-xl text-gray-800 font-bold">FoodRecipe</h1>
		<div class="flex items-center">
			<form class="flex items-center mr-5 w-4/5 pt-4" action="search" method="get">
			<input type="submit" value="search"/>
				<input class="ml-2 outline-none bg-transparent text-sm" type="text"
					name="keyword" placeholder="Search title, ingredient, or category..."/>
			</form>
			<ul class="flex items-center space-x-6 ml-2">
				<li class="font-semibold text-gray-700"><%=session.getAttribute("username")%></li>
				<li class="font-semibold text-gray-700">
				<a href="logout">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="flex items-center ml-28 space-x-5 mt-5">
		<a
			class="inline-flex items-center py-2 px-8 text-sm font-medium text-center text-white
                            bg-blue-500 rounded-lg hover:bg-blue-600 focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 
                            dark:hover:bg-blue-700 dark:focus:ring-blue-800 ml-8"
			href="<%=request.getContextPath()%>/list">Get All Recipes</a>
			<a
			class="inline-flex items-center py-2 px-8 text-sm font-medium text-center text-white
                            bg-blue-500 rounded-lg hover:bg-blue-600 focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 
                            dark:hover:bg-blue-700 dark:focus:ring-blue-800 ml-8"
			href="<%=request.getContextPath()%>/new">Add New Recipe</a>
	</div>
	<div class="flex flex-row items-center mt-10 justify-items-center">
		<%-- <div class="py-0 px-0 w-4/5 flex flex-col items-center"> --%>
		<div class="grid justify-items-center w-4/5 mx-auto">
			<div class="grid gap-y-0 w-full">
				<div
					class="w-full px-5 bg-white shadow-md flex flex-cols-5 py-4 h-fit-content items-center">
					<h2 class="text-sm font-bold text-gray-700 w-full ml-12">Recipe #</h2>
					<h2 class="text-sm font-bold text-gray-700 w-full">Title</h2>
					<h2 class="text-sm font-bold text-gray-700 w-full">Category</h2>
					<h2 class="text-sm font-bold text-gray-700 w-full">Description</h2>
					<h2 class="text-sm font-bold text-gray-700 w-full ml-10">Ingredients</h2>
					<h2 class="text-sm font-bold text-gray-700 w-full">Actions</h2>
				</div>
				<c:forEach var="recipe" items="${recipeList}">
					<div
						class="w-full px-10 bg-white shadow-md flex flex-cols-5 py-4 h-fit-content items-center">
						<h2 class="text-sm font-bold text-gray-700 w-fit ml-10">
							<c:out value="${recipe.recipeId}" />
						</h2>
						<h2 class="text-sm font-bold text-gray-700 w-full ml-20">
							<c:out value="${recipe.title}" />
						</h2>
						<h2 class="text-sm font-bold text-gray-700 w-fit">
						<c:if test='${recipe.category == Category.ENTREE}'>
						Entree
						</c:if>
						<c:if test='${recipe.category == Category.MAINCOURSE}'>
						Main Course
						</c:if>
						<c:if test='${recipe.category == Category.DRINK}'>
						Drink
						</c:if>
						<c:if test='${recipe.category == Category.DESSERT}'>
						Dessert
						</c:if>
						</h2>
						<h2 class="text-sm font-bold text-gray-700 w-full ml-16">
							<c:out value="${recipe.description}" />
						</h2>
						<h2 class="text-sm font-bold text-gray-700 w-full">
							<c:forEach var="ingredient" items="${recipe.ingredients}">
								<c:out value="${ingredient}, " />
							</c:forEach>
						</h2>
						<div class="w-20">
							<a class="text-sm font-bold text-gray-700 mx-2"
								href="edit?recipeId=<c:out value='${recipe.recipeId}'/>">Edit</a>
							<a class="text-sm font-bold text-gray-700 mx-2"
								href="delete?recipeId=<c:out value='${recipe.recipeId}'/>">Delete</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
</body>
</html>
