/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.InstructorDB;
import DAO.PartDB;
import DAO.PartFilesDB;
import Model.Instructor;
import Model.PartFiles;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ad
 */
@MultipartConfig
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//        maxFileSize = 1024 * 1024 * 1000, // 1GB
//        maxRequestSize = 1024 * 1024 * 1000) // 1GB
public class UploadFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
//        HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//                Part filePart = request.getPart("file");//Textbox value of name file.
//                String fileName_test = filePart.getSubmittedFileName();
        HttpSession session = request.getSession();
        Model.Part partname = null;
        Model.PartFiles partFiles = null;
        String url = "/teacher";

        User user = (User) session.getAttribute("User");

        String message = "";
//        String requirement = request.getParameter("requirement");
        if (user == null) {
            url = "/sign-in";
        } else if (user.getRole().getRoleId() != 2) {
            url = "/Views/Pages/Home/home.jsp";
        } else {
//            try {
//            int partid = Integer.parseInt((String)request.getParameter("partid"));
            int partid = Integer.parseInt(session.getAttribute("partid").toString());
            partname = PartDB.GetPrtByPartId(partid);
            int reset = 0;
            while (reset == 0 )
            {
                partFiles =  PartFilesDB.getPartFilesByPart(partname);
                if(partFiles != null)
                {
                    try{
                        if(!PartFilesDB.deletePartFiles(partname))
                            message = "Lỗi xóa thông tin file";
                    }
                    catch(Exception ex){
                    }
                }
                else{
                    reset = 1;
                }
            }
            
            

            if (partname == null) {
                message = "Không có phần bài học";
                url = "/teacher";
//            } else if (request.getParts() == null) {
//                request.setAttribute("message", "Chưa chọn File!!!");
//                request.setAttribute("part", part);
//                getServletContext().getRequestDispatcher(url).forward(request, response);

            } else {
                
                 /*javax.servlet.http.Part doc = request.getPart("document");                 
                
                  String docPath=""; 
                    if (doc != null ) {
                        String fileNameDoc = extractFileName(doc);
                        //để ko bị lỗi null với file
                        try {
                            String applicationPath = getServletContext().getRealPath("");
                            String uploadPath = applicationPath + File.separator + "TestUpload";
                            System.out.println("applicationPath:" + applicationPath);
                            File fileUploadDirectory = new File(uploadPath);
                            if (!fileUploadDirectory.exists()) {
                                fileUploadDirectory.mkdirs();
                            }

                            String savePath = uploadPath + File.separator + fileNameDoc;
                            
//                        message = "aaa" + savePath;
                            String sRootPath = new File(savePath).getAbsolutePath();
                          
                           
                            doc.write(savePath + File.separator);

//                        File fileSaveDir1 = new File(savePath);
                            //path of the image
                           docPath= fileNameDoc;
                           session.setAttribute("fileNameDoc", docPath);
                            partname.setDocument(docPath);
                       
                            if (!PartDB.updatePart(partname)) {
                                message = "Lưu tài liệu không thành công";
                            }
                        } catch (Exception ex) {
                           
                        }  
                       
                     
                    }*/
                    javax.servlet.http.Part video = request.getPart("video");                 
                
                    String videoPart=""; 
                    if (video != null ) {
                        String fileName = extractFileName(video);
                        //để ko bị lỗi null với file
                        try {
                            String applicationPath = getServletContext().getRealPath("");
                            String uploadPath = applicationPath + File.separator + "TestUpload";
                            System.out.println("applicationPath:" + applicationPath);
                            File fileUploadDirectory = new File(uploadPath);
                            if (!fileUploadDirectory.exists()) {
                                fileUploadDirectory.mkdirs();
                            }

                            String savePath = uploadPath + File.separator + fileName;
                            
//                        message = "aaa" + savePath;
                            String sRootPath = new File(savePath).getAbsolutePath();
                          
                           
                            video.write(savePath + File.separator);

//                        File fileSaveDir1 = new File(savePath);
                            //path of the image
                           videoPart=  fileName;
                           session.setAttribute("fileNameVideo", videoPart);
                            partname.setVideo(videoPart);
                       
                            if (!PartDB.updatePart(partname)) {
                                message = "Lưu tài liệu video không thành công";
                            }
                             String uploadFile = request.getServletContext().getRealPath("") + File.separator + "TestUpload";//....WebApplication3\build\web\TestUpload
                            for (Part p : request.getParts()) {
    //                            try{
                                  String filePart = extractFileName(p);
                                  filePart = new File(filePart).getName();
                                  session.setAttribute("document", filePart); 
                                  if(!filePart.equals(videoPart)){
                                  p.write(this.getFolderUpload(uploadFile).getAbsolutePath() + File.separator + filePart);
                                  PartFiles partfiles = new PartFiles(partname, filePart);
                                  if(!PartFilesDB.insertPartFiles(partfiles))
                                  {
                                      message = "Lỗi thêm";
                                  }
                                  }
//                            }
//                            catch (Exception ex) {
//                                message = "Thêm file bài học không thành công.";
//                        }
                        }
                             url = "/Display_Course_Introduction_Teacher?courseid="+partname.getCourse().getCourseId();
                        } catch (Exception ex) {

                        }
                     
                    }
                    
                    
                 
//                String uploadFile = request.getServletContext().getRealPath("") + File.separator + "TestUpload";//....WebApplication3\build\web\TestUpload
//               
////                try {
//
//                    for (Part p : request.getParts()) {
//
//                        String fileName = extractFileName(p);
//                        // refines the fileName in case it is an absolute path
//                        fileName = new File(fileName).getName();
//                        session.setAttribute("fileName", fileName);
//                        String pt = this.getFolderUpload(uploadFile).getAbsolutePath() + File.separator + fileName;
//
//                        p.write(pt); 
//                        
////                        part.setDocument(docPath);
//                        part.setVideo(fileName);
//                        if (!PartDB.updatePart(part)) {
//                            message = "Lưu tài liệu không thành công";
//                        }
//                        countFile++;
//                    }
//                
////                } catch (Exception ex) {
////                    message = "Chưa chọn file bài học.";
////
////                }
//                request.setAttribute("countFile", countFile);

            }
//            }
//            catch(Exception ex)
//            {
//                message="Lưu thất bại!";
//            }

        }
        if (message.equals("")) {
            message = "Lưu thành công";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";

    }

    public File getFolderUpload(String UploadFile) {
        File folderUpload = new File(UploadFile);
        //File folderUpload = new File("E:\\Lâp Trình WEB\\TestUpload" + "/Uploads" );//File(System.getProperty("user.dir") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private void white(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
