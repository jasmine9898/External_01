package com.UrlTest.UrlExternalError;

import com.UrlTest.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

/**
 * Created by admin on 2016/8/19.
 */
public class Error908Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer stringBuffer=new StringBuffer();
        String urlString = "https://192.168.2.106/info.php";
        URLConnection connection=UrlUtil.createConnection(urlString);
        stringBuffer.append("<html><body><h2>UrlConnection External Error</h2>");
        try{
            connection.connect();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (connection != null) {
                connection = null;
                //connection.disconnect();
            }
        }
        stringBuffer.append("---908-SSLHandshakeException---");
        stringBuffer.append("</body></html>");
        resp.getWriter().write(stringBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req,resp);
    }
}
