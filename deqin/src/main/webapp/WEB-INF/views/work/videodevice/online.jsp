<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="center"><object type='application/x-vlc-plugin'
							pluginspage="http://www.videolan.org/" id='vlc' events='false'
							width="720" height="410">
							<param name='mrl'
								value='rtsp://10.10.59.138/axis-media/media.amp' />
							<param name='volume' value='50' />
							<param name='autoplay' value='true' />
							<param name='loop' value='false' />
							<param name='fullscreen' value='false' />
							<param name='controls' value='false' />
						</object>
					 </td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div>
			</li>
		</ul>
	</div>

</div>