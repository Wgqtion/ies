<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="hiddenName" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenValue" type="java.lang.Object" %>
<%@ attribute name="uploadifyFileId" type="java.lang.String" required="true"%>
<%@ attribute name="attach" type="com.vsc.business.core.entity.sys.upload.Attach" %>
<%@ attribute name="fileTypeExts" type="java.lang.String" %> 
<%@ attribute name="remarkText" type="java.lang.String" %> 
<%@ attribute name="validate" type="java.lang.String" %> 

<c:set var="v_fileTypeExts" value="*.*"></c:set>
<c:if test="${not empty fileTypeExts}">
 <c:set var="v_fileTypeExts" value="${fileTypeExts}"></c:set>
</c:if>
<div class="fileUpload">
	<input type="hidden" id="${uploadifyFileId}_hiddenId" name="${hiddenName}" value="${hiddenValue}" <c:if test="${not empty validate}">validate="${validate}"</c:if>/>
	<div class="fileView">
		<c:if test="${not empty attach}">
	    <a id="${uploadifyFileId}_fileId" target="_blank" href="${ctx}/${attach.downloadPath}">${attach.name}</a>
	    </c:if>
	    <c:if test="${empty attach}">
	    <a id="${uploadifyFileId}_fileId" target="_blank" href="javascript:void(0)"></a>
	    </c:if>
	</div>
	<div class="b_bar">
		<div class="b_upload">
			<vsc:uploadify name="${uploadifyFileId}_name" onUploadSuccess="uploadifyFileComplete" id="${uploadifyFileId}" queueID="${uploadifyFileId}_queueID"
				fileSizeLimit="1MB" fileTypeExts="${v_fileTypeExts}"></vsc:uploadify>
		</div>
		<div class="b_del">
			<div class="button">
				<div class="buttonContent">
					<button type="button" onclick="javascript:uploadifyFileCompleteDelete('${uploadifyFileId}_hiddenId','${uploadifyFileId}_fileId')">删除</button>
				</div>
			</div>
		</div>
	</div>
	<div class="remarks">
		<span><c:if test="${empty remarkText}">请选择${v_fileTypeExts}类型文件</c:if>
		${remarkText}
		</span>
	</div>
	<div class="b_queue" id="${uploadifyFileId}_queueID"></div>	
</div>