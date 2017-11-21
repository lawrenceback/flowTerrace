<%@ page import="com.jxgis.ffireserver.web.bean.TSFunction" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jxgis.ffireserver.core.util.oConvertUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<style>
	.dropdown, .dropup{text-align: center}
	.nav > li > a i{
		min-width: 16px;
	}
	.nav-second-level li:last-child{
		margin-bottom: 0;
	}
	.headPortrait{
		width: 70px;
		height: 70px;
	}

	.nav-header{
		background-size: cover;
	}
	.nav > li > a{
		transition: all .3s;
		padding: 10px 15px;
	}
	.mini-navbar .nav > li > a{
		padding: 14px 35px 14px 25px;
	}

	.nav.nav-second-level li a{
		padding: 7px 15px 7px 10px;
		padding-left: 35px;
	}

	.nav-second-level li a,.nav > li > a{
		font-weight: 100;
	}

	.navbar-default .nav > li > a:hover span, .navbar-default .nav > li > a:focus span{
		color:#fff;
	}
	#side-menu{
		height: calc(100% - 177px);
		overflow-y: auto;
		overflow-x: hidden;
	}
	body.mini-navbar #side-menu{
		overflow: inherit;
	}
	#side-menu li{
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	.slimScrollDiv > * {
		overflow: auto;
	}
	body.mini-navbar .slimScrollDiv > * {
		overflow: inherit;
	}
	body.mini-navbar ul.collapse{
		border-left: 1px solid rgb(47, 72, 80);
	}
	body.mini-navbar .navbar-default .nav-second-level .nav-label{
		display: inline-block;
	}


</style>
<script>
	window.personal = {
		menu: {
			updatePwd: uuidEasy().replace(/-/g,''),
			info: uuidEasy().replace(/-/g,'')
		}
	}

</script>
<nav class="navbar-default navbar-static-side" role="navigation" style="margin-bottom: 25px;">
	<div class="nav-close"><i class="fa fa-times-circle"></i></div>
	<div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
		<div class="sidebar-collapse" style="width: auto; height: 100%;">

			<div class="nav-header" style="background-color: inherit;">
				<div class="dropdown profile-element">
                    <span>
                        <img alt="image" class="img-circle headPortrait" src="${pageContext.request.contextPath}/${user.headPortrait}">
                    </span>
					<a data-toggle="dropdown" class="dropdown-toggle" id="dropdown-toggle">
						<span class="clear">
							<span class="block m-t-xs"><strong class="font-bold">${user.name}</strong></span>
							<span class="text-muted text-xs block"><l id="pac"></l> ${roles[0].roleName}<b class="caret"></b></span>
						</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li>
							<a class="J_menuItem" data-index="0" onclick="addTabHplus('修改密码', '${pageContext.request.contextPath}/userController.do?goHref&id=${user.id}&href=system/user/user-update-password', personal.menu.updatePwd);">修改密码</a>
						</li>

						<li>
							<a class="J_menuItem" data-index="0" onclick="addTabHplus('个人信息', '${pageContext.request.contextPath}/userController.do?getUserInfo', personal.menu.info);">个人信息</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="/if//loginController/logout">安全退出</a>
						</li>
					</ul>

				</div>

				<!-- 当左侧折叠时显示 start -->
				<div class="logo-element">FFS</div>
				<!-- 当左侧折叠时显示 end -->

			</div>
			<ul class="nav" id="side-menu">



				<%
					Map<Integer, List<TSFunction>> listMap = (Map<Integer, List<TSFunction>>) request.getSession().getAttribute("menuMap");


					if(listMap == null || listMap.size()==0 || !listMap.containsKey(0)){

						out.write("不具有任何权限，请找管理员分配权限");
						out.write("</ul></div></div></nav>");//DOM 结束语
						return;

					}

					int curIndex = 0;

					StringBuffer menuString = new StringBuffer();
					List<TSFunction> list = listMap.get(curIndex);


					for (int i=0;i<list.size(); i++) {
						TSFunction function = list.get(i);


						menuString.append("<li>");

						if(function.getFunctionUrl() != null && !function.getFunctionUrl().trim().equals("") ){


							menuString.append("<a class='J_menuItem' href='/"+function.getFunctionUrl()+"'>");
//							menuString.append("<svg class=\"icon\" aria-hidden=\"true\"><use xlink:href=\"#"+ function.getIcon().getIconClass() +"\"></use></svg>");
							menuString.append("<i class=\"" + function.getIcon().getIconClass() + "\"></i>");
							menuString.append("<span class='nav-label'>" + function.getFunctionName()+"</span>");
							menuString.append("</a>");

						} else {
							menuString.append("<a href='#'>");
//							menuString.append("<svg class=\"icon\" aria-hidden=\"true\"><use xlink:href=\"#"+ function.getIcon().getIconClass() +"\"></use></svg>");
							menuString.append("<i class=\"" + function.getIcon().getIconClass() + "\"></i>");
							menuString.append("<span class='nav-label'>"+function.getFunctionName()+"</span>");
							menuString.append("<span class='fa arrow'></span>");



							menuString.append("</a>");
						}

						menuString.append("<ul class='nav nav-second-level' >");

						menuString.append(getHplusSubMenu(function, function.getFunctionLevel()+1, listMap));

						menuString.append("</ul>");

						menuString.append("</li>");


					}

					out.write( menuString.toString() );


				%>

				<%!


					String getHplusSubMenu(TSFunction parent, int level, Map<Integer, List<TSFunction>> map){
						StringBuffer menuString = new StringBuffer();
						List<TSFunction> list = map.get(level);
						if (oConvertUtils.isNotEmpty(list)){
							for (TSFunction function : list) {
								if (function.getFunctionParent().getId().equals(parent.getId())){
									try{
										menuString.append(getLeafOfHplusTree(function,map));
									} catch (Exception e){

									}
								}
							}
						}

						return menuString.toString();
					}
				%>

				<%!
					String getLeafOfHplusTree(TSFunction function,Map<Integer, List<TSFunction>> map) {
						StringBuffer menuString = new StringBuffer();

						if(!function.hasSubFunction(map)){

							menuString.append("<li>");
							menuString.append("<a class='J_menuItem' href='/"+function.getFunctionUrl()+"'>");
//							menuString.append("<svg class=\"icon\" aria-hidden=\"true\"><use xlink:href=\"#"+ function.getIcon().getIconClass() +"\"></use></svg>");
							menuString.append("<i class=\"" + function.getIcon().getIconClass() + "\"></i>");
							menuString.append("<span class='nav-label'>" + function.getFunctionName()+"</span>");
							menuString.append("</a>");
							menuString.append("</li>");

						} else {

							//当还有子菜单时
							menuString.append("<li>");

							menuString.append("<a class='J_menuItem'>");
//							menuString.append("<svg class=\"icon\" aria-hidden=\"true\"><use xlink:href=\"#"+ function.getIcon().getIconClass() +"\"></use></svg>");
							menuString.append("<i class=\"" + function.getIcon().getIconClass() + "\"></i>");
							menuString.append("<span class='nav-label'>" + function.getFunctionName()+"</span>");
							menuString.append("<span class='fa arrow'></span>");
							menuString.append("</a>");

							menuString.append("<ul class='nav nav-third-level collapse' aria-expanded='false' style='height: 0px;'>");

							menuString.append(getHplusSubMenu(function, function.getFunctionLevel()+1, map));

							menuString.append("</ul>");

							menuString.append("</li>");
						}
						return menuString.toString();
					}
				%>


				<%--   菜单模板勿删   --%>
				<%--<li>
					<a href="#">
						<i class="fa fa-home"></i> <span class="nav-label">主页</span> <span class="fa arrow"></span>
					</a>

					<ul class="nav nav-second-level collapse" aria-expanded="false" style="height: 0px;">
						<li>
							<a class="J_menuItem" href="index_v1.html" data-index="0">主页示例一</a>
						</li>
						<li>
							<a class="J_menuItem" href="index_v2.html" data-index="5">主页示例二</a>
						</li>
						<li>
							<a class="J_menuItem" href="index_v3.html" data-index="6">主页示例三</a>
						</li>
						<li>
							<a class="J_menuItem" >00000000000<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level collapse" aria-expanded="false" style="height: 0px;">
								<li>
									<a class="J_menuItem" href="index_v1.html" data-index="0">111</a>
								</li>
								<li>
									<a class="J_menuItem" href="index_v2.html" data-index="5">2222</a>
								</li>
								<li>
									<a class="J_menuItem" href="index_v3.html" data-index="6">33333</a>
								</li>
								<li>
									<a class="J_menuItem" href="index_v4.html" data-index="7">44444</a>
								</li>
								<li>
									<a href="index_v5.html" target="_blank">主页示例五</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="index_v5.html" target="_blank">主页示例五</a>
						</li>
					</ul>

				</li>--%>


			</ul>
		</div>
	</div>
</nav>