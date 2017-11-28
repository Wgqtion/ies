<%@page import="com.vsc.util.CoreUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
{"statusCode":"${statusCode}","message":"${message}","name":"${vm.name}", "createTime":"<<fmt:formatDate value="${vm.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>",
"urlPath":"${vm.urlPath}","fileType":"${vm.fileType}",
"fileSize":"${vm.fileSize}","downloadUrl":"${vm.downloadPath}",
"fileKey":"${vm.fileKey}","id":"${vm.id}",
    "navTabId":"${param.navTabId}",
	<c:if test="${not empty callbackData}">
	"callbackData":${callbackData},
	</c:if>
	"rel":"${param.rel}",
	"callbackType":"${param.callbackType}",
	"callbackFun":"${param.callbackFun}",
	"forwardUrl":"${param.forwardUrl}"
}

