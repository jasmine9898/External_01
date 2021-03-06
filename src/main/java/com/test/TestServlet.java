package com.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 2016/8/12.
 */
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int sleettime= (int)(1+Math.random()*(300-10+1));
        try {
            Thread.sleep(sleettime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><body><h2>This is TestServlet</h2>");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        stringBuffer.append("sleeptime is " +sleettime+
                ".received: "+stringBuilder);
        stringBuffer.append("</body></html>");
        resp.getWriter().write(stringBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
