<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<head>
<style type="text/css">

element.style {
    left: 257px;
    top: 135px;
    width: 800px;
    z-index: 74;
}
</style>
</head>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/user/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="sys.user.create"></vsc:token>
		<input type="hidden" name="callbackType" value="closeCurrent"> 
		<input type="hidden" name="navTabId" value="sys_user">
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button"  onclick="window.location.href='${ctx}/sys/user/export?type=1'" >导出PDF</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button"  onclick="window.location.href='${ctx}/sys/user/export?type=2'" >导出EXCEL</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button"  onclick="window.location.href='${ctx}/sys/user/export?type=3'" >导出WORD</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>


