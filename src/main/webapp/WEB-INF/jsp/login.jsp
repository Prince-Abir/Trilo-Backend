<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
        }
        .login-card {
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            max-width: 500px;
        }
        .form-control:focus {
            border-color: #6c63ff;
            box-shadow: 0 0 0 0.25rem rgba(108, 99, 255, 0.25);
        }
        .btn-login {
            background: #6c63ff;
            border: none;
            transition: all 0.3s;
        }
        .btn-login:hover {
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
        .divider {
            display: flex;
            align-items: center;
            text-align: center;
            margin: 20px 0;
        }
        .divider::before,
        .divider::after {
            content: "";
            flex: 1;
            border-bottom: 1px solid #dee2e6;
        }
        .divider:not(:empty)::before {
            margin-right: 1em;
        }
        .divider:not(:empty)::after {
            margin-left: 1em;
        }
    </style>
</head>
<body class="d-flex align-items-center">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="login-card bg-white mx-auto">
                    <div class="p-4 p-md-5">
                        <div class="text-center mb-4">
                            <h2 class="fw-bold text-primary">Welcome Back</h2>
                            <p class="text-muted">Sign in to your account</p>
                        </div>
                        
                        <form>
                            <!-- Email -->
                            <div class="form-floating mb-3">
                                <input type="email" class="form-control" id="loginEmail" placeholder="name@example.com" required>
                                <label for="loginEmail"><i class="fas fa-envelope me-2"></i>Email Address</label>
                            </div>
                            
                            <!-- Password -->
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="loginPassword" placeholder="Password" required>
                                <label for="loginPassword"><i class="fas fa-lock me-2"></i>Password</label>
                            </div>
                            
                            <!-- Remember Me & Forgot Password -->
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="rememberMe">
                                    <label class="form-check-label" for="rememberMe">Remember me</label>
                                </div>
                                <a href="#" class="text-decoration-none">Forgot password?</a>
                            </div>
                            
                            <!-- Submit Button -->
                            <button class="btn btn-primary btn-login w-100 py-3 fw-bold mb-3" type="submit">
                                <i class="fas fa-sign-in-alt me-2"></i> Sign In
                            </button>
                            
                            <!-- Divider -->
                            <div class="divider text-muted">OR</div>
                            
                            <!-- Social Login -->
                            <div class="d-flex justify-content-center gap-3 mb-4">
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
                            
                            <!-- Registration Link -->
                            <div class="text-center">
                                <p class="text-muted">Don't have an account? <a href="/register" class="text-decoration-none fw-bold">Sign Up</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Form Validation -->
    <script>
        document.querySelector('form').addEventListener('submit', function(e) {
            const email = document.getElementById('loginEmail').value;
            const password = document.getElementById('loginPassword').value;
            
            if (!email || !password) {
                e.preventDefault();
                alert('Please fill in all fields');
            }
        });
    </script>
</body>
</html>