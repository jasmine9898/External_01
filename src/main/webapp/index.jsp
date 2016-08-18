<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String contextPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<html>
<head>
    <title>AgentTest_External_01_IndexPage</title>
    <script>
        function checkUrl() {
            if (document.getElementById('urlConnection_url').value.length == 0) {
                document.getElementById('check').innerHTML = '请输入调用的url！';
                return false;
            } else {
                return true;
            }
        }
        function checkUploadFile() {
            if (document.getElementById('urlUpload_file').value == "") {
                document.getElementById('check').innerHTML = '请选择上传的文件！';
                return false;
            } else {
                return true;
            }
        }
        function checkDownloadFile() {
            if (document.getElementById('urlConnectionDownload_url').value == "") {
                document.getElementById('check').innerHTML = '请输入需下载文件的url！';
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
<h2>External_01</h2>
<form name="url_form" onsubmit="return checkUrl();" method="post" action="urlGet">
    <p>urlConnection url：
        <input type="text" id="urlConnection_url" name="urlConnection_url" size="40"
               onfocus="document.getElementById('check').innerHTML = '';">
        <input type="submit" value="get connect" >

    </p>
</form>
<form name="upload_form" onsubmit="return checkUploadFile();" action="urlUpload" method="POST"
      enctype="multipart/form-data">
    <p>upload file：
        <input type="file" id="urlUpload_file" name="urlUpload_file"
               onclick="document.getElementById('check').innerHTML = '';">
        <input type="submit" value="upload"/>
    </p>
</form>
<form name="download_form" onsubmit="return checkDownloadFile();" action="urlDownload" method="POST">
    <p>download file:
        <input type="text" id="urlConnectionDownload_url" name="urlConnectionDownload_url" size="40">
        <input type="submit" value="download"/>
    </p>
</form>
<a id="check" style="color:#FF0000"></a>

<p>get 请求：<a href="<%=contextPath%>/urlGet"><%=contextPath%>/urlGet</a></p>
<p>post请求：<a href="<%=contextPath%>/urlPost"><%=contextPath%>/urlPost</a></p>
<p>上传文件：<a href="<%=contextPath%>/urlUpload"><%=contextPath%>/urlUpload</a></p>
<p>下载文件：<a href="<%=contextPath%>/urlDownload"><%=contextPath%>/urlDownload</a></p>
<p>表单提交：<a href="<%=contextPath%>/urlParam"><%=contextPath%>/urlParam</a></p>
<p>900 MalformedURLException：<a href="<%=contextPath%>/urlerr900"><%=contextPath%>/urlerr900</a></p>
<p>901 UnknownHostException：<a href="<%=contextPath%>/urlerr901"><%=contextPath%>/urlerr901</a></p>
<p>902 ConnectException：<a href="<%=contextPath%>/urlerr902"><%=contextPath%>/urlerr902</a></p>
<p>903 SocketTimeoutException：<a href="<%=contextPath%>/urlerr903"><%=contextPath%>/urlerr903</a></p>
<p>908 SSLHandshakeException：<a href="<%=contextPath%>/urlerr908"><%=contextPath%>/urlerr908</a></p>
<p>404 HTTP Error：<a href="<%=contextPath%>/urlerr404"><%=contextPath%>/urlerr404</a></p>
<p>500 HTTP Error：<a href="<%=contextPath%>/urlerr500"><%=contextPath%>/urlerr500</a></p>


</body>
</html>
