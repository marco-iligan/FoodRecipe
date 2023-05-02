<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<script src="https://cdn.tailwindcss.com"></script>
<title>FoodRecipe</title>
</head>
<body>

	<%-- <input type="hidden" id="status" value="<%= request.getAttribute("status") %>"> --%>

	<div
		class="bg-amber-100 absolute top-0 left-0 bg-gradient-to-b from-amber-400 via-amber-400 to-amber-100 bottom-0 leading-5 h-full w-full overflow-hidden">

	</div>
	<div
		class="relative   min-h-screen  sm:flex sm:flex-row  justify-center bg-transparent rounded-3xl shadow-xl">
		<div
			class="flex-col flex  self-center lg:px-14 sm:max-w-4xl xl:max-w-md  z-10">
			<div class="self-start hidden lg:flex flex-col  text-gray-300">

				<h1 class="my-3 font-bold text-4xl text-slate-700">Welcome
					Back!</h1>
				<p class="pr-3 text-sm opacity-80 text-slate-600">Lorem ipsum is
					placeholder text commonly used in the graphic, print, and
					publishing industries for previewing layouts and visual mockups</p>
			</div>
		</div>
		<div class="flex justify-center self-center  z-10">
			<div class="p-12 bg-white mx-auto rounded-3xl w-96 ">
				<div class="mb-7">
					<h3 class="font-bold text-2xl text-slate-700">Sign Up</h3>
					<p class="text-gray-400">
						Already have an account? <a href="login.jsp"
							class="text-sm text-amber-500 hover:text-amber-400">Sign In</a>
					</p>
				</div>
				<form class="space-y-6" method="post" action="register">
					<div class="">
						<input
							class=" w-full text-sm text-slate-700 px-4 py-3 bg-gray-200 focus:bg-gray-100 border  border-gray-200 rounded-lg focus:outline-none focus:border-amber-300"
							type="text" placeholder="First Name" name="firstName">
					</div>
					<div class="">
						<input
							class=" w-full text-sm text-slate-700 px-4 py-3 bg-gray-200 focus:bg-gray-100 border  border-gray-200 rounded-lg focus:outline-none focus:border-amber-300"
							type="text" placeholder="Middle Name" name="middleName">
					</div>
					<div class="">
						<input
							class=" w-full text-sm text-slate-700 px-4 py-3 bg-gray-200 focus:bg-gray-100 border  border-gray-200 rounded-lg focus:outline-none focus:border-amber-300"
							type="text" placeholder="Last Name" name="lastName">
					</div>
					<div class="">
						<input
							class=" w-full text-sm  text-slate-700 px-4 py-3 bg-gray-200 focus:bg-gray-100 border  border-gray-200 rounded-lg focus:outline-none focus:border-amber-300"
							type="texr" placeholder="Username" name="username">
					</div>


					<div class="relative" x-data="{ show: true }">
						<input placeholder="Password" name="password"
							:type="show ? 'password' : 'text'"
							class="text-sm text-slate-700 px-4 py-3 rounded-lg w-full bg-gray-200 focus:bg-gray-100 border border-gray-200 focus:outline-none focus:border-amber-300">
						<div
							class="flex items-center absolute inset-y-0 right-0 mr-3  text-sm leading-5">

							<svg @click="show = !show"
								:class="{'hidden': !show, 'block':show }"
								class="h-4 text-amber-500" fill="none"
								xmlns="http://www.w3.org/2000/svg" viewbox="0 0 576 512">
								<path fill="currentColor"
									d="M572.52 241.4C518.29 135.59 410.93 64 288 64S57.68 135.64 3.48 241.41a32.35 32.35 0 0 0 0 29.19C57.71 376.41 165.07 448 288 448s230.32-71.64 284.52-177.41a32.35 32.35 0 0 0 0-29.19zM288 400a144 144 0 1 1 144-144 143.93 143.93 0 0 1-144 144zm0-240a95.31 95.31 0 0 0-25.31 3.79 47.85 47.85 0 0 1-66.9 66.9A95.78 95.78 0 1 0 288 160z">
								</path>
							</svg>

							<svg @click="show = !show"
								:class="{'block': !show, 'hidden':show }"
								class="h-4 text-amber-500" fill="none"
								xmlns="http://www.w3.org/2000/svg" viewbox="0 0 640 512">
								<path fill="currentColor"
									d="M320 400c-75.85 0-137.25-58.71-142.9-133.11L72.2 185.82c-13.79 17.3-26.48 35.59-36.72 55.59a32.35 32.35 0 0 0 0 29.19C89.71 376.41 197.07 448 320 448c26.91 0 52.87-4 77.89-10.46L346 397.39a144.13 144.13 0 0 1-26 2.61zm313.82 58.1l-110.55-85.44a331.25 331.25 0 0 0 81.25-102.07 32.35 32.35 0 0 0 0-29.19C550.29 135.59 442.93 64 320 64a308.15 308.15 0 0 0-147.32 37.7L45.46 3.37A16 16 0 0 0 23 6.18L3.37 31.45A16 16 0 0 0 6.18 53.9l588.36 454.73a16 16 0 0 0 22.46-2.81l19.64-25.27a16 16 0 0 0-2.82-22.45zm-183.72-142l-39.3-30.38A94.75 94.75 0 0 0 416 256a94.76 94.76 0 0 0-121.31-92.21A47.65 47.65 0 0 1 304 192a46.64 46.64 0 0 1-1.54 10l-73.61-56.89A142.31 142.31 0 0 1 320 112a143.92 143.92 0 0 1 144 144c0 21.63-5.29 41.79-13.9 60.11z">
								</path>
							</svg>

						</div>

					</div>
					<button type="submit"
						class="w-full flex justify-center bg-amber-500  hover:bg-amber-400 text-gray-100 p-3  rounded-lg tracking-wide font-semibold  cursor-pointer transition ease-in duration-500">
						Sign Up</button>
				</form>
				<div class="mt-7 text-center text-gray-300 text-xs">
					<span> Copyright © 2021-2023</span>
				</div>
			</div>

		</div>


	</div>

	<svg class="absolute bottom-0 left-0 "
		xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
		<path fill="#f9fafb" fill-opacity="1"
			d="M0,0L40,42.7C80,85,160,171,240,197.3C320,224,400,192,480,154.7C560,117,640,75,720,74.7C800,75,880,117,960,154.7C1040,192,1120,224,1200,213.3C1280,203,1360,149,1400,122.7L1440,96L1440,320L1400,320C1360,320,1280,320,1200,320C1120,320,1040,320,960,320C880,320,800,320,720,320C640,320,560,320,480,320C400,320,320,320,240,320C160,320,80,320,40,320L0,320Z"></path></svg>
	<script
		src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.js"></script>
		
	<%-- <script type="text/javascript">
	
		var status = document.getElementById("status").value;
		if(status == "success"){
			swal({
	            title: "Success!",
	            text: "You have successfully registered.",
	            type: "success"
	        }, function() {
	            window.location = "login.jsp";
	        });
		}
	</script> --%>
</body>
</html>