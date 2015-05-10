<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/lanrenzhijia.css" rel="stylesheet">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
	
    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="assets/ico/favicon.png">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top"  >
      <div class="navbar-inner">
      
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">易记事后台管理</a>
          
          <div class="nav-collapse collapse">
              <ul class="nav pull-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" id="username" data-toggle="dropdown">下载 <b class='caret'></b></a>
                <ul class="dropdown-menu">
                  <li class="divider"></li>
				  <li><a href="#" id="alterPwd">修改密码</a></li>
                </ul>
              </li>
            </ul>
           </div><!--/.nav-collapse -->
          
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">后台人员管理</li>
              <li class="active"><a href="#" id="adminInfo">编辑信息</a></li>
              <li class="nav-header">易记事</li>
              <li><a href="#" id="userInfo">用户</a></li>
              <li><a href="#" id="noteInfo">笔记</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
            <h3>管理员</h3>
            <p class="navbar-text pull-right">
               <a class="btn btn-primary theme-login" href="#" id="addAdmin">新增用户</a>
            </p>
          <div id="adminInfoDIV">
            	<table class="table table-hover table-condensed">
  					<thead>
  						<tr>
  							<th>#</th><th>用户名</th><th>姓名</th><th>操作</th>
  						</tr>
  					</thead>
  					<tbody></tbody>
				</table>
			</div>
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->
		<input type="hidden" id="user" value="${username?default('null')}">
		<input type="hidden" id="pid" value="${id?default('')}">
    </div><!--/.fluid-container-->
    
   	 <div class="theme-popover" id="_add">
    	 <div class="theme-poptit">
     	     <a href="javascript:;" title="关闭" class="close">×</a>
     	     <h3>新增用户</h3>
    	 </div>
    	 <div class="theme-popbod dform theme-signin">
            	    <ol>
            	        <li><strong>姓 名：</strong><input class="ipt" type="text" id="addAdmin_realname" placeholder="姓名" size="20" /></li>
           	            <li id="addAdminUsername"><strong>用户名：</strong><input class="ipt" type="text" id="addAdmin_username"  size="20" /></li>
           	            <li id="addAdminPassword"><strong>密 码：</strong><input class="ipt" type="text" id="addAdmin_password" size="20" /></li>
          	           <li><input class="btn btn-primary" type="submit" value=" 确 定 " id="addAdmin_submit" /></li>
        	        </ol>
    	 </div>
	</div>
	
	<div class="theme-popover" id="_alter">
    	 <div class="theme-poptit">
     	     <a href="javascript:;" title="关闭" class="close">×</a>
     	     <h3>修改密码</h3>
    	 </div>
    	 <div class="theme-popbod dform theme-signin">
            	    <ol>
           	            <li><strong>原密码：</strong><input class="ipt" type="text" id="alterAdmin_oldpwd"  size="20" /></li>
           	            <li><strong>新密码：</strong><input class="ipt" type="text" id="alterAdmin_newpwd" size="20" /></li>
          	           <li><input class="btn btn-primary" type="submit" value=" 确 定 " id="alterAdmin_submit" /></li>
        	        </ol>
    	 </div>
	</div>
	<div class="theme-popover-mask"></div>
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="js/jquery.shCircleLoader.js"></script>
    <script src="js/admin.js"></script>
    <script src="assets/js/bootstrap-transition.js"></script>
    <script src="assets/js/bootstrap-alert.js"></script>
    <script src="assets/js/bootstrap-modal.js"></script>
    <script src="assets/js/bootstrap-dropdown.js"></script>
    <script src="assets/js/bootstrap-scrollspy.js"></script>
    <script src="assets/js/bootstrap-tab.js"></script>
    <script src="assets/js/bootstrap-tooltip.js"></script>
    <script src="assets/js/bootstrap-popover.js"></script>
    <script src="assets/js/bootstrap-button.js"></script>
    <script src="assets/js/bootstrap-collapse.js"></script>
    <script src="assets/js/bootstrap-carousel.js"></script>
    <script src="assets/js/bootstrap-typeahead.js"></script>
	 <div style="bottom: 0px;position: fixed;">
	 	<hr class="featurette-divider">
     	 <footer>
       	 <p>By 徐建东&middot; 指导老师 魏晓莉</p>
     	 </footer>
     </div>
     <script>
     	//新增用户
		jQuery(document).ready(function($) {
			$('.theme-login').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('#_add').slideDown(200);
		})
		//修改密码
		$('#alterPwd').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('#_alter').slideDown(200);
		})
		$('.theme-poptit .close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		})
		})
	</script>
  </body>
</html>
