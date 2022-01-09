<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        

    </head>
<body>

<div id="demo"></div>
<header class="container-fluid">
    <div class="container">
        <div class="logo"><h1>Tikl</h1></div>

        <div class="search">
            <input type="text" placeholder="Tim kiem..."><span class="btn" id="search-btn">Search</span>
        </div>
        <div class="accountcontroller">
            <div class="hoverdisplay">
                <div class="btn ">sign in</div>
                <form method="POST" action="/login-handle" id="loginform">
                    <h1>Sign in</h1>
                    <div class="form-group">
                        <label for="username">Username: </label><input type="text" name="username" id="l-username">
                    </div>
                    <div class="form-group">
                        <label for="pw">password: </label><input type="password" name="pw" id="l-pw">
                    </div>
                    <button type="button" class="btn btn-primary">Sign in</button>
                </form>
            </div>
            
            <div class="hoverdisplay">
                <div class="btn ">registration</div>
                <form method="POST" action="/login-handle" id="registrationform">
                    <h1>Registration</h1>
                    <div class="form-group">
                        <label for="username">Username: </label><input type="text" name="username" id="r-username">
                    </div>
                    <div class="form-group">
                        <label for="pw">Password: </label><input type="password" name="pw" id="r-pw">
                    </div>
                    <div class="form-group">
                        <label for="email">Email: </label><input type="text" name="email" id="r-email">
                    </div>
                    
                    <button type="button" class="btn btn-primary">Sign in</button>
                </form>
            </div>
        </div>
    </div>
</header>

<body>

</body>






</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/javascript2.js"></script>


</html>
