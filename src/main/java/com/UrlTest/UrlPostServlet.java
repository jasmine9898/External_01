package com.UrlTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/12.
 */
public class UrlPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        String urlString = null;
        stringBuffer.append("<html><body><h2>UrlConnection 调用</h2>");
        if (req.getParameter("urlConnection_url") == null) {
            urlString = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/test";
        } else {
            urlString = (String) req.getParameter("urlConnection_url");
            if (!urlString.contains("http")) {
                String urlpre = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
                urlString = urlpre + "/" + urlString;
            }
        }
        HttpURLConnection connection = null;
        try {
            connection = UrlUtil.createConnection(urlString);
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String nowtime = dateFormat.format(now);

            OutputStream os = connection.getOutputStream();
            os.write(nowtime.getBytes("UTF-8"));
            os.flush();

            int statusCode = connection.getResponseCode();
            stringBuffer.append("responseCode:" + statusCode + "<br>");
            if (statusCode == 200) {
                stringBuffer.append(UrlUtil.getConnectionInputString(connection));
            }
            stringBuffer.append(UrlUtil.getConnectionHeader(connection));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        stringBuffer.append("</body></html>");
        resp.getWriter().write(stringBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
