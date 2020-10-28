//package servlets;
//
//import sun.misc.IOUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.UUID;
//
//@MultipartConfig
//public class DirUploadServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String uploadDir = getServletConfig().getInitParameter("uploadDir");
//        Part file = request.getPart("file");
//
//        IOUtils.copyLarge(
//                file.getInputStream(),
//                new FileOutputStream(uploadDir +
//                        File.separator +
//                        UUID.randomUUID().toString() +
//                        "-" +
//                        file.getSubmittedFileName()
//                )
//        );
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("action", "/upload/dir");
//        request.getRequestDispatcher("/upload.ftl").forward(request, response);
//    }
//}
