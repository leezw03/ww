<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="ww.core.spring.SysProperty"%>

<%@include file="/ww/commons/taglibs.jsp" %>
<!-- Sets initial viewport load and disables zooming  -->
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

<!-- Makes your prototype chrome-less once bookmarked to your phone's home screen -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<%
	response.addHeader("Content-Type", "text/html; charset=utf-8");
	response.addHeader("pragma", "no-cache");
	response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	response.addHeader("expires", "Mon, 26 Jul 1997 05:00:00 GMT");
	response.addHeader("X-UA-Compatible", "IE=edge, chrome=1");
	
	SysProperty prop = SysProperty.getInstance();
	pageContext.setAttribute("__env", prop.getValue("env"));
	pageContext.setAttribute("__v", prop.getValue("version"));
%>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Mon, 26 Jul 1997 05:00:00 GMT">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
