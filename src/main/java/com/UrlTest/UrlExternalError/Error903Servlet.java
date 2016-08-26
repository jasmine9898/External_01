package com.UrlTest.UrlExternalError;

import com.UrlTest.UrlUtil;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 2016/8/19.
 */
public class Error903Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer stringBuffer=new StringBuffer();
        String httpserverPort="8070";
        String urlString = req.getScheme() + "://" + req.getServerName() + ":" + httpserverPort + req.getContextPath() + "/httpserver";
        URL url =new URL(urlString);
        URLConnection connection=url.openConnection();
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charsert", "UTF-8");
        connection.setConnectTimeout(300);
        connection.setReadTimeout(300);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("content-type", "application/octet-stream;charset=UTF-8");
        //URLConnection connection=UrlUtil.createConnection(urlString);
        stringBuffer.append("<html><body><h2>UrlConnection External Error</h2>");
        try{
            connection.connect();
            stringBuffer.append("<br><br>");
            InputStream is = null;
            try {
                is = connection.getInputStream();
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(is,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String readLine = null;
                while ((readLine = br.readLine()) != null) {
                    stringBuffer.append(readLine);
                }
                is.close();
                br.close();
                stringBuffer.append("<br>");

            } catch (IOException e) {
                e.printStackTrace();
            }
       }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (connection != null) {
                connection = null;
                //connection.disconnect();
            }
        }
        stringBuffer.append("---903-SocketTimeoutException---");
        stringBuffer.append("</body></html>");
        resp.getWriter().write(stringBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req,resp);
    }
}
