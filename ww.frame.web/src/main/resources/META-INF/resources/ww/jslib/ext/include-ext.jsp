<%
	SysProperty prop = SysProperty.getInstance();
	pageContext.setAttribute("ext.theme", prop.getValue("ww.frame.ext.theme"));
	pageContext.setAttribute("ext.debug", prop.getValue("ww.frame.ext.debug", ""));
%>
<script type="text/javascript" src="${ctx}/jslib/ext/include-ext.js?theme=${ext.theme}&${ext.debug}&__v=${__v}"></script>