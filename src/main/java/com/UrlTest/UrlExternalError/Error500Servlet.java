package com.UrlTest.UrlExternalError;

import com.UrlTest.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by admin on 2016/8/19.
 */
public class Error500Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer stringBuffer=new StringBuffer();
        String urlString = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/500";
        HttpURLConnection connection=UrlUtil.createConnection(urlString);
        stringBuffer.append("<html><body><h2>UrlConnection External Error</h2>");
        try{
            connection.connect();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        stringBuffer.append("---500 HTTP Error---");
        stringBuffer.append("</body></html>");
        resp.getWriter().write(stringBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req,resp);
    }
}
