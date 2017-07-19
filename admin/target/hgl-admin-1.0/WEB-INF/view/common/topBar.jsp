<%@page pageEncoding="UTF-8"%>
<header class="main-header">
  <a href="javascript:;" class="logo">
   惠给利后台管理系统
  </a>
  <nav class="navbar navbar-static-top" role="navigation">
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <li>
        	<a href="javascript:;">当前用户 :${session_key.name }</a>   
        </li>
        <li>
        	<a href="${ctx}/adminuser/doInitUpdatePassword">修改密码</a>
        </li>
         <li>
        	<a href="${ctx }/login/logout">安全退出</a>
        </li>
      </ul>
    </div>
  </nav>
</header>