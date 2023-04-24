<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodrecipe.model.Ingredient" %>
<% List<Ingredient> ingredients = (List)session.getAttribute("ingredients"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingredients</title>
<!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<body id="page-top">

	<!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                   <i class="fas fa-utensils"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Food Recipe</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Recipes -->
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-receipt"></i>
                    <span>Recipes</span></a>
            </li>
            
            <!-- Nav Item - Ingredients -->
            <li class="nav-item active">
                <a class="nav-link" href="">
                    <i class="fas fa-shopping-basket"></i>
                    <span>Ingredients</span></a>
            </li>

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%= session.getAttribute("name") %></span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Ingredients</h1>
                    <!-- <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation</a>.</p> -->

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <a class="px-2 fas fa-plus" data-toggle="modal" data-target="#addModal"> Add Ingredient</a>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Actions</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach items="${ingredients}" var="ingredient">
                                        <tr>
                                            <td ><span id="curId${ingredient.getId()}"><c:out value="${ingredient.getId()}"/></span></td>
                                            <td><span id="curName${ingredient.getId()}"><c:out value="${ingredient.getName()}"/></span></td>
                                            <td><span id="curDesc${ingredient.getId()}"><c:out value="${ingredient.getDescription()}"/></span></td>
                                            <td>
                                            <a data-update-id="${ingredient.getId()}" class="px-2 fas fa-edit" data-toggle="modal" data-target="#updateModal"></a>
                                            <a data-ing-id="<%= request.getContextPath() %>/delete-ingredient?id=${ingredient.getId()}"  class="fas fa-trash" data-toggle="modal" data-target="#deleteModal"></a>
                                  			 <!-- onClick="{return confirmDelete();}" -->
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="<%= request.getContextPath() %>/logout">Logout</a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Delete Modal-->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Delete this Ingredient?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Delete" below if you really want to remove this Ingredient.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a id="delIngId" class="btn btn-primary" href="">Delete</a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Update Modal-->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Update this Ingredient?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <form id="updateIngForm" action="<%= request.getContextPath() %>/update-ingredient" method="post" class="user">
                <input type="hidden" id="updateIngredientId" name="updateIngredientId" value="">
                <div class="font-weight-bold col-sm-6 mb-3 mb-sm-0">Ingredient Name: <input type="text" class="form-control form-control-user" id="updateName" name="updateName"
                                            placeholder="Enter Ingredient name..." value=""></div>
                <div class="font-weight-bold col-sm-10 mb-3 mb-sm-0">Description: <textarea class="form-control form-control-user" id="updateDescription" name="updateDescription"
                                            placeholder="Enter Ingredient description..." value=""></textarea></div>
                <div class="modal-body">Select "Update" below if you really want to update this Ingredient.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <input type="submit" id="upIngId" class="btn btn-primary" href="" value="Update">
                </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Update Modal-->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New Ingredient</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <form id="newIngForm" action="<%= request.getContextPath() %>/add-ingredient" method="post" class="user">
                <div class="font-weight-bold col-sm-6 mb-3 mb-sm-0">Ingredient Name: <input type="text" class="form-control form-control-user" id="newName" name="newName"
                                            placeholder="Enter Ingredient name..." value=""></div>
                <div class="font-weight-bold col-sm-10 mb-3 mb-sm-0">Description: <textarea class="form-control form-control-user" id="newDescription" name="newDescription"
                                            placeholder="Enter Ingredient description..." value=""></textarea></div>
                <div class="modal-body">Select "Add" below if you really want to add this Ingredient.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <input type="submit" class="btn btn-primary" value="Add">
                </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>
    
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
    
    <script type="text/javascript">
    	$('#deleteModal').on('show.bs.modal', function(e){
    		var ingId = $(e.relatedTarget).data('ing-id');
    		
    		var a = document.getElementById('delIngId');
    		a.href = ingId;
    	});
    </script>
    
    <script type="text/javascript">
    	$('#updateModal').on('show.bs.modal', function(e){
    		var uptId = $(e.relatedTarget).data('update-id');
    		var id = document.getElementById('curId'+uptId).innerHTML;
    		var name = document.getElementById('curName'+uptId).innerHTML;
    		var desc = document.getElementById('curDesc'+uptId).innerHTML;
    		
    		var upId = document.getElementById('updateIngredientId');
    		var upName = document.getElementById('updateName');
    		var upDesc = document.getElementById('updateDescription');
    		upId.value = uptId;
    		upName.value = name;
    		upDesc.value = desc;
    	});
    </script>

</body>
</html>