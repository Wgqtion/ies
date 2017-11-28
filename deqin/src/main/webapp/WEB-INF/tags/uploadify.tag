<%@tag import="org.apache.shiro.SecurityUtils"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="queueID" type="java.lang.String"%>
<%@ attribute name="buttonText" type="java.lang.String"%>
<%@ attribute name="buttonImage" type="java.lang.String"%>
<%@ attribute name="buttonClass" type="java.lang.String"%>
<%@ attribute name="width" type="java.lang.Integer"%>
<%@ attribute name="height" type="java.lang.Integer"%>
<%@ attribute name="removeCompleted" type="java.lang.Boolean"%>
<%@ attribute name="removeTimeout" type="java.lang.Integer"%>
<%@ attribute name="uploadLimit" type="java.lang.Integer"%>
<%@ attribute name="fileSizeLimit" type="java.lang.String"%>
<%@ attribute name="fileTypeExts" type="java.lang.String"%>
<%@ attribute name="fileTypeDesc" type="java.lang.String"%>
<%@ attribute name="multi" type="java.lang.Boolean"%>
<%@ attribute name="onUploadSuccess" type="java.lang.String"%>
<%@ attribute name="onUploadComplete" type="java.lang.String"%>

<input <c:if test="${not empty id}">id="${id}"</c:if> type="file" name="${name}"
	uploaderOption="{
			swf:'${ctx}/static/js/uploadify/scripts/uploadify.swf',
			uploader:'${ctx}/attach/up;jsessionid=<%=SecurityUtils.getSubject().getSession().getId()%>',
			formData:{jsessionid:'<%=SecurityUtils.getSubject().getSession().getId()%>'},
		<c:if test="${not empty queueID}">
			queueID:'${queueID}',</c:if>
		<c:if test="${not empty buttonText}">
			buttonText:'${buttonText}',</c:if>
		<c:if test="${not empty buttonImage}">
			buttonImage:'${buttonImage}',</c:if>
		<c:if test="${not empty buttonClass}">
			buttonClass:'${buttonClass}',</c:if>
		<c:if test="${not empty width}">			
			width:${width},</c:if>
		<c:if test="${not empty height}">
			height:${height},</c:if> 
			auto:true, 
			method:'post', 
		<c:if test="${not empty removeCompleted}">
			removeCompleted :${removeCompleted},</c:if>
		<c:if test="${not empty removeTimeout}">
			removeTimeout   : ${removeTimeout},</c:if>
		<c:if test="${not empty uploadLimit}">
			uploadLimit :${uploadLimit},</c:if>
		<c:if test="${not empty fileSizeLimit}">
			fileSizeLimit :'${fileSizeLimit}',</c:if>
		<c:if test="${not empty fileTypeExts}">
			fileTypeExts:'${fileTypeExts}',</c:if>
		<c:if test="${not empty fileTypeDesc}">
			fileTypeDesc:'${fileTypeDesc}', </c:if>
		<c:if test="${not empty multi}">
			multi:${multi},</c:if>
		<c:if test="${not empty onUploadSuccess}">
			onUploadSuccess:${onUploadSuccess},</c:if>
		<c:if test="${not empty onUploadComplete}">
			onUploadComplete:${onUploadComplete},</c:if> 
			fileObjName:'vfile'
		}" />