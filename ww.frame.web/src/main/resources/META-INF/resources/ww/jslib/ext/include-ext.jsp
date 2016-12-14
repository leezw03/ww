<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="ww.core.spring.SysProperty"%>
<%
	pageContext.setAttribute("ext.theme", prop.getValue("ww.frame.ext.theme"));
	pageContext.setAttribute("ext.debug", prop.getValue("ww.frame.ext.debug", ""));
%>
<script type="text/javascript" src="${ctx}/ww/jslib/ext/include-ext.js?theme=${ext.theme}&${ext.debug}&__v=${__v}"></script>