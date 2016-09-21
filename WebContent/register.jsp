<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"
	integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<style type="text/css">body { padding-top: 70px; }</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/snake/leaderboard">Leaderboard</a></li>
				<li><a href="/snake/logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<form class="form-horizontal" action="/snake/register" method="POST">
			<h2 class="form-signin-heading">Please register!</h2>
			<div class="form-group">
				<div class="col-md-8">
					<input type="text" class="form-control" id="name" name="name"
						placeholder="Name" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="Email" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Password" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8">
					<button type="submit" class="btn btn-success">Register</button>
					<a href="/snake/login" class="btn btn-primary">Login</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>