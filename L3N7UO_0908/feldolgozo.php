<!DOCTYPE HTML >

<html lang = "hu">

	<head>
		<title>Feldolgozó php</title>
		<meta charset="UTF-8">
	</head>
	
	<body style="background:#99ff33">
	
		<font style="font-size:16pt">

			<h2 style="text-align:center">HTML5 Űrlap</h2>
					
				<table style="width:300px; border:1px solid black;margin-left:auto;margin-right:auto; margin-top:20px; padding:10px; background:#a6ff4d">
					<tr>
						<td>
							<?php
								echo ("<strong>Név: </strong>" . $_POST['nev'] . "<br><br>");
								echo ("<strong>Pin kód: </strong>" . $_POST['kod'] . "<br><br>");
								echo ("<strong>Gyümölcs: </strong>" . $_POST['gyumolcs'] . "<br><br>");
								echo ("<strong>Életkor: </strong>" . $_POST['eletkor'] . "<br><br>");
								echo ("<strong>Lábméret: </strong>" . $_POST['labmeret'] . "<br><br>");
								echo ("<strong>Önbizalom: </strong>" . $_POST['onbizalom'] . "<br><br>");
							?>
						</td>
					</tr>
					<tr>
						<td>
								<a href="urlap_hd.html">vissza az űrlap kitöltésére...</a>
						</td>
					</tr>
				</table>
				
		</font>
	</body>
	
</html>