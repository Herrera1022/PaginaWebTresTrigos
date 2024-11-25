<%-- 
    Document   : ensayo
    Created on : 25/11/2024, 1:35:15 p. m.
    Author     : juanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form id="contactForm" action="agendar" method="post">
                    <div class="row align-items-stretch mb-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="idNumber" class="form-label">Ingresa tu nombre *</label>
                                <input type="text" name="nombre" id="name" class="form-control" placeholder="Ingresa tu nombre" data-sb-validations="required">
                            </div>
                            <div class="form-group">
                                <label for="idNumber" class="form-label">Ingresa tu identificación *</label>
                                <input type="number" name="id" id="idNumber" class="form-control" placeholder="Ejemplo: 12345678" data-sb-validations="required">
                                <div class="invalid-feedback" data-sb-feedback="idNumber:required">La identificación es obligatoria.</div>
                                <div class="invalid-feedback" id="numberError" style="display: none;">Debes ingresar un número válido.</div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="form-label">Ingresa tu correo *</label>
                                <input type="email" name="email" id="email" class="form-control" placeholder="example@domain.com" data-sb-validations="required email">
                                <div class="invalid-feedback" data-sb-feedback="email:required">El correo es obligatorio.</div>
                                <div class="invalid-feedback" data-sb-feedback="email:email">Debes ingresar un correo válido.</div>
                            </div>
                            <div class="form-group">
                                <label for="fecha" class="form-label">Ingresa la fecha *</label>
                                <input type="date" name="fecha" id="fecha" class="form-control" data-sb-validations="required">
                                <div class="invalid-feedback" data-sb-feedback="fecha:required">Debes seleccionar una fecha.</div>
                            </div>
                        </div>
                        <div class="row">
                            <!-- Combobox principal y subcombobox agrupados -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <!-- Combobox principal -->
                                    <label for="category" class="form-label">Categoría</label>
                                    <select class="form-control" name="categoria" id="category" data-sb-validations="required" onchange="updateOptions()">
                                        <option value="" disabled selected>Elige el producto *</option>
                                        <option value="snacks">Snacks</option>
                                        <option value="croissants">Croissants</option>
                                        <option value="panes_conscientes">Panes conscientes</option>
                                    </select>
                                    <div class="invalid-feedback" data-sb-feedback="category:required">Debes elegir una categoría.</div>
                        
                                    <!-- Subcombobox (justo debajo del principal) -->
                                    <label for="subcategory" class="form-label mt-3">Opciones</label>
                                    <select class="form-control" name="producto" id="subcategory" data-sb-validations="required">
                                        <option value="" disabled selected>Elige una opción *</option>
                                    </select>
                                    <div class="invalid-feedback" data-sb-feedback="subcategory:required">Debes elegir una opción.</div>
                                </div>
                            </div>
                        </div>
                        
                        <style>
                            /* Estilo de los campos */
                            .form-control {
                                font-family: Arial, sans-serif;
                                font-size: 1rem;
                                padding: 10px;
                                border: 1px solid #ced4da;
                                border-radius: 4px;
                                width: 100%;
                            }
                        
                            .form-label {
                                font-weight: bold;
                                font-size: 0.9rem;
                                margin-bottom: 5px;
                                display: block; /* Asegura que el label esté sobre el combobox */
                            }
                        
                            .mt-3 {
                                margin-top: 1rem;
                            }
                        
                            /* Alineación local sin afectar todo el diseño */
                            .form-group {
                                display: flex;
                                flex-direction: column;
                            }
                        </style>
                        
                        <script>
                            // Opciones para cada categoría
                            const options = {
                                snacks: [
                                    "Pie De Limón",
                                    "Porción De Quiche Hongo y Espinaca",
                                    "Galleta De Macadamia",
                                    "Roll De Jamón Serrano",
                                    "Roll De Vegetales Asados",
                                    "Sánduche De Queso"
                                ],
                                croissants: [
                                    "Croissant Almendras",
                                    "Croissant Arándanos",
                                    "NY Cheesecake Croissant",
                                    "Croissant Brownie",
                                    "Croissant Pistacho",
                                    "Croissant Espinaca y Queso"
                                ],
                                panes_conscientes: [
                                    "Sourdough",
                                    "Avena y Arroz Integral",
                                    "Cereales Andinos",
                                    "Multigranos",
                                    "Nuez Nogal y Arándanos",
                                    "Ezequiel"
                                ]
                            };
                        
                            // Función para actualizar las opciones del subcombobox
                            function updateOptions() {
                                const category = document.getElementById("category").value;
                                const subcategory = document.getElementById("subcategory");
                        
                                // Limpia las opciones anteriores
                                subcategory.innerHTML = '<option value="" disabled selected>Elige una opción *</option>';
                        
                                // Agrega nuevas opciones basadas en la categoría seleccionada
                                if (options[category]) {
                                    options[category].forEach(option => {
                                        const opt = document.createElement("option");
                                        opt.value = option.toLowerCase().replace(/\s+/g, "_");
                                        opt.textContent = option;
                                        subcategory.appendChild(opt);
                                    });
                                }
                            }
                        </script>
                        
                    </div>
                    <!-- Submit success message-->
                    <!---->
                    <!-- This is what your users will see when the form-->
                    <!-- has successfully submitted-->
                    <div class="d-none" id="submitSuccessMessage">
                        <div class="text-center text-white mb-3">
                            <div class="fw-bolder">¡Pedido agendado con éxito!</div>
                            Gracias por tu pedido, se ha agendado correctamente.
                        </div>
                    </div>
                    
                    <div class="d-none" id="submitErrorMessage">
                        <div class="text-center text-danger mb-3">¡Error al agendar el pedido!</div>
                    </div>
                    
                        <button class="btn btn-primary btn-xl text-uppercase"  id="submitButton" type="submit">Agendar</button>
                    
                    
                </form>
    </body>
    
</html>
