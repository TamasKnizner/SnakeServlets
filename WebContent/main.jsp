<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
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
		<div class="jumbotron">
			<h1>MAIN</h1>
		</div>
		<div class="form-inline">
			<div class="form-group">
				<input type="number" class="form-control" id="score" name="score"
					placeholder="Score" max="2000000000" min="0">
			</div>
			<button id="submitButton" class="btn btn-primary">Save</button>
		</div>
		<h2>My results</h2>
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Score</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody id="scoreTable">
					<c:forEach items="${scores}" var="score">
						<tr>
							<td>${score.getUser().getName()}</td>
							<td>${score.getScore()}</td>
							<td>${score.getTimeStamp().toString()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#submitButton').on("click", function(event) {
				$.post("scores", {
					score : $("#score").val()
				}, function(data) {
					$("#scoreTable").html(data);
				});
			});
		});
	</script>
</body>
</html>