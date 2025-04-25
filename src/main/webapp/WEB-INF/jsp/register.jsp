<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
        }
        .registration-card {
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .form-control:focus {
            border-color: #6c63ff;
            box-shadow: 0 0 0 0.25rem rgba(108, 99, 255, 0.25);
        }
        .btn-register {
            background: #6c63ff;
            border: none;
            transition: all 0.3s;
        }
        .btn-register:hover {
            background: #564dff;
            transform: translateY(-2px);
        }
        .input-group-text {
            background: transparent;
            border-right: none;
        }
        .form-floating>.form-control:not(:placeholder-shown)~label {
            color: #6c63ff;
        }
    </style>
</head>
<body class="d-flex align-items-center">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="registration-card bg-white">
                    <div class="p-4 p-md-5">
                        <div class="text-center mb-4">
                            <h2 class="fw-bold text-primary">Create Account</h2>
                            <p class="text-muted">Join our community today</p>
                        </div>
                        
                        <form>
                            <div class="row g-3">
                                <!-- First Name -->
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="firstName" placeholder="John" required>
                                        <label for="firstName"><i class="fas fa-user me-2"></i>First Name</label>
                                    </div>
                                </div>
                                
                                <!-- Last Name -->
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="lastName" placeholder="Doe" required>
                                        <label for="lastName"><i class="fas fa-user me-2"></i>Last Name</label>
                                    </div>
                                </div>
                                
                                <!-- Email -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="email" class="form-control" id="email" placeholder="name@example.com" required>
                                        <label for="email"><i class="fas fa-envelope me-2"></i>Email Address</label>
                                    </div>
                                </div>
                                
                                <!-- Password -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="password" placeholder="Password" required>
                                        <label for="password"><i class="fas fa-lock me-2"></i>Password</label>
                                    </div>
                                    <div class="form-text">Must be 8+ characters with numbers</div>
                                </div>
                                
                                <!-- Confirm Password -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" required>
                                        <label for="confirmPassword"><i class="fas fa-lock me-2"></i>Confirm Password</label>
                                    </div>
                                </div>
                                
                                <!-- Terms Checkbox -->
                                <div class="col-12">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="terms" required>
                                        <label class="form-check-label" for="terms">
                                            I agree to the <a href="#">Terms & Conditions</a>
                                        </label>
                                    </div>
                                </div>
                                
                                <!-- Submit Button -->
                                <div class="col-12 mt-4">
                                    <button class="btn btn-primary btn-register w-100 py-3 fw-bold" type="submit">
                                        <i class="fas fa-user-plus me-2"></i> Register Now
                                    </button>
                                </div>
                                
                                <!-- Login Link -->
                                <div class="col-12 text-center mt-3">
                                    <p class="text-muted">Already have an account? <a href="/login" class="text-decoration-none">Sign In</a></p>
                                </div>
                            </div>
                        </form>
                        
                        <!-- Social Login -->
                        <div class="text-center mt-4">
                            <p class="text-muted mb-3">Or sign up with</p>
                            <div class="d-flex justify-content-center gap-3">
                                <a href="#" class="btn btn-outline-primary rounded-circle p-2">
                                    <i class="fab fa-google"></i>
                                </a>
                                <a href="#" class="btn btn-outline-primary rounded-circle p-2">
                                    <i class="fab fa-facebook-f"></i>
                                </a>
                                <a href="#" class="btn btn-outline-primary rounded-circle p-2">
                                    <i class="fab fa-twitter"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Custom Validation Script -->
    <script>
        document.querySelector('form').addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            if (password !== confirmPassword) {
                e.preventDefault();
                alert('Passwords do not match!');
            }
        });
    </script>
</body>
</html>