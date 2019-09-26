<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:template>
	<jsp:body>
		<div class="btn-group btn-group-justified selections">
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-default selections" value="line">Line</button>
			  </div>
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-default selections" value="bar">Bar</button>
			  </div>
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-default selections" value="radar">Radar</button>
			  </div>
			      <div class="btn-group" role="group">
			    <button type="button" class="btn btn-default selections"
								value="polarArea">Polar Area</button>
			  </div>
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-default selections" value="pie">Pie</button>
			  </div>
			  <div class="btn-group" role="group">
			    <button type="button" class="btn btn-default selections" value="doughnut">Doughnut</button>
			  </div>
    </div>
	</jsp:body>
</t:template>