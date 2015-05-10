<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重置密码</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script  src="js/jquery-1.11.2.min.js"></script>
<script  src="js/resetPasswd.js"></script>
<style type="text/css">
#main {position: absolute;width:400px;height:200px;left:50%;top:50%; 
margin-left:-200px;margin-top:-100px} 
</style>
</head>

<body>

<input type="hidden" name="json" id="json" value='${result}' />
<div class="container-fluid" id="ok">
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li>
					<span class="divider">提示</span>
				</li>
			</ul>
			<div class="hero-unit">
				<h3 class="text-center">
					密码重置成功，请尽快修改密码！
					<p>
					<div class="control-group info">
 							 <div class="controls">
   							 	新密码为：<input type="text" id="passwd">
  							 </div>
							</div>
				</p>
				</h3>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid" id="no">
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li>
					<span class="divider">提示</span>
				</li>
			</ul>
			<div class="hero-unit">
				<h3 class="text-center">
				<em>
					链接已过期，密码重置失败！
				</em>
				</h3>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid" id="main">
	<div class="row-fluid">
		<div class="span12">
			<p class="text-info text-center lead">
				<img alt="140x140" src="img/a.png" class="img-rounded" />
			</p>
			<p class="text-info text-center lead">
				<em>易记事</em>
			</p>
		</div>
	</div>
</div>
</body>
</html>