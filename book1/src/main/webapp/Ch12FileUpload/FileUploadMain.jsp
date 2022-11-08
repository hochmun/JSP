<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FileUpload</title>
	</head>
	<body>
		<h3>파일 업로드</h3>
		<span style="color: red;">${ errorMessage }</span>
		<form name="fileForm" method="post" enctype="multipart/form-data"
			action="UploadProcess.jsp" onsubmit="return validateForm(this);">
			
		</form>
	</body>
</html>