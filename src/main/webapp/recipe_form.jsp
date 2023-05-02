<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.entity.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;
httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>FoodRecipe</title>
</head>
<body>

	<nav class="flex justify-between px-20 py-10 items-center bg-white">
		<h1 class="text-xl text-gray-800 font-bold">FoodRecipe</h1>
		<div class="flex items-center">
			<ul class="flex items-center space-x-6 ml-2">
				<li class="font-semibold text-gray-700"><%=session.getAttribute("username")%></li>
				<li class="font-semibold text-gray-700">
				<a href="logout">Logout</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="flex mb-20">
		<div
			class="flex flex-col my-auto pb-10 bg-white rounded-2xl shadow-lg w-2/5 mx-auto">
			<span class="bg-slate-200 py-5 rounded-t-2xl">
				<h2 class="font-bold text-gray ml-8">
				<c:if test="${recipe != null}">
				Edit Recipe
				</c:if>
				<c:if test="${recipe == null}">
				Add Recipe
				</c:if>
				</h2>
			</span>
			<c:if test="${recipe != null}">
			<form action="update" method="post">
			</c:if>
			<c:if test="${recipe == null}">
			<form action="insert" method="post">
			</c:if>
			<input class="hidden" name="recipeId" value="<c:out value='${recipe.recipeId}'/>"/>
				<div class="mb-6 mt-10 mx-16 grid justify-items-start">
					<label class="font-bold inline-block mb-2 text-gray-700">Title</label>
					<input
						class="w-full px-3 py-1.5 text-base font-normal text-gray-700
                            bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out
                            m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
						placeholder="The Best Cookie Recipe" name="title" type='text' value="<c:out value='${recipe.title}'/>" required />
				</div>
				<div class="mb-6 mx-16  grid justify-items-start">
					<label class="font-bold inline-block mb-2 text-gray-700">Description</label>
					<textarea
						class="w-full px-3 py-1.5 text-base font-normal text-gray-700 h-16
                            bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out
                            m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
						placeholder='A chewy chocolate chip for my special trip...' name="description"><c:out value='${recipe.description}'/></textarea>
				</div>
				<div class="mb-6 mx-16 grid justify-items-start">
					<label class="font-bold inline-block mb-2 text-gray-700">Category</label>
					<select
						class="w-full px-3 py-1.5 text-base font-normal text-gray-700
                            bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out
                            m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
						name="category"
						list="categoryList" required>
						<option class="text-gray-700"
						<c:if test='${recipe.category == Category.ENTREE}'>
						selected
						</c:if>
						 value="Entree" />
						
						<option class="text-gray-700"
						<c:if test='${recipe.category == Category.MAINCOURSE}'>
						selected
						</c:if>
						value="Main Course" />
						
						<option class="text-gray-700"
						<c:if test='${recipe.category == Category.DRINK}'>
						selected
						</c:if>
						value="Drink" />
						
						<option class="text-gray-700" 
						<c:if test='${recipe.category == Category.DESSERT}'>
						selected
						</c:if>
						value="Dessert" />
					</select>
				</div>
				<div class="mb-6 mx-16  grid justify-items-start">
					<label class="font-bold inline-block text-gray-700">Ingredients</label>
					<h3 class="text-sm font-regular inline-block mb-2 text-gray-400">Separate each ingredient with a comma ","</h3>
					<textarea
						class="w-full px-3 py-1.5 text-base font-normal text-gray-700 h-28
                            bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out
                            m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
						placeholder='Sugar, Butter, Flour, ...' name="ingredients"><c:if test="${recipe != null}"><c:out value='${String.join(", ", recipe.ingredients)}'/></c:if></textarea>
				</div>
				<div class="flex items-center mx-16 space-x-5 mt-8">
					<button
						class="inline-flex items-center py-2 px-20 text-sm font-medium text-center text-white
                            bg-blue-500 rounded-lg hover:bg-blue-600 focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 
                            dark:hover:bg-blue-700 dark:focus:ring-blue-800 ml-8"
						type="submit">Save</button>
					<a
					href="<%=request.getContextPath()%>/list"
						class="inline-flex items-center py-2 px-20 text-sm font-medium text-center text-gray-900 
                                bg-white rounded-lg border border-gray-300 hover:bg-gray-100 focus:ring-4 focus:ring-blue-300 
                                dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-700 
                                dark:focus:ring-gray-800 w-fit">Cancel</a>

				</div>

			</form>
		</div>
	</div>

</body>
</html>