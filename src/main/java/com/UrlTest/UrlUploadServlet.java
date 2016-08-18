package com.UrlTest;

import com.test.UploadServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by admin on 2016/8/16.
 */
public class UrlUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><body><h2>UrlConnection Upload File</h2>");
        String urlpre = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
        String urlString = urlpre + "/writeToFile";


        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        String realPath = req.getSession().getServletContext().getRealPath("/");
        factory.setRepository(new File(realPath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items = null;
        String fileName = "";
        StringBuilder stringBuilder = null;
        BufferedReader reader = null;
        stringBuilder = new StringBuilder();

        if (req.getHeader("content-type") != null) {
            try {
                items = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            FileItem item = items.get(0);
            fileName = item.getName();
            reader = new BufferedReader(new BufferedReader(new InputStreamReader(item.getInputStream())));

        } else {
            String defaultFileName = req.getSession().getServletContext().getRealPath("/WEB-INF/classes/uploadDefaultFile.txt");
            File defaultfile = new File(defaultFileName);
            reader = new BufferedReader(new BufferedReader(new FileReader(defaultfile)));
            fileName = "uploadDefaultFile.txt";
        }
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }

        String filecontent = stringBuilder.toString();
        urlString += "?fileName=" + URLEncoder.encode(fileName, "UTF-8");
        HttpURLConnection connection = null;
        try {
            connection = UrlUtil.createConnection(urlString);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            os.write(filecontent.getBytes("UTF-8"));
            os.flush();
            //获取返回内容
            int statusCode = connection.getResponseCode();
            stringBuffer.append("responseCode:" + statusCode + "<br>");
            if (statusCode == 200) {
                stringBuffer.append(UrlUtil.getConnectionInputString(connection));
            }
            stringBuffer.append(UrlUtil.getConnectionHeader(connection));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
