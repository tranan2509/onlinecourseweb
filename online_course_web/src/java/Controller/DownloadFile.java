/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ad
 */
@WebServlet(name = "DownloadFile", urlPatterns = {"/DownloadFile"})
public class DownloadFile extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String name = (String)request.getParameter("fileNameDoc");
            String name2 = (String)session.getAttribute("fileNameVideo");
            String path = getServletContext().getRealPath("/"+"TestUpload"+File.separator+name);
            String path2 = getServletContext().getRealPath("/"+"TestUpload"+File.separator+name2);
            String kind = request.getParameter("kindfile");
            System.out.println(kind);
            if(kind.equals("doc")){
                File dwFile = new File(path);
                if(dwFile.exists()){
                    response.setContentType("application/octet-stream");
                    response.setContentLength((int) dwFile.length());

                    String hkey = "Content-Disposition";
                    String hvalue = String.format("attachment; fileName =\"%s\"", dwFile.getName());
                    response.setHeader(hkey, hvalue);
                    FileInputStream in = new FileInputStream(dwFile);
                    int i;
                    while((i=in.read())!= -1){
                        out.write(i);
                    }
                    in.close();
                    out.close();
                }
                else{
                    out.print("Không tồn tại file cần download");
                }
            }
            else if(kind.equals("video")){
            
            File dwFile2 = new File(path2);
            if(dwFile2.exists()){
                response.setContentType("application/octet-stream");
                response.setContentLength((int) dwFile2.length());
                
                String hkey = "Content-Disposition";
                String hvalue = String.format("attachment; fileName =\"%s\"", dwFile2.getName());
                response.setHeader(hkey, hvalue);
                FileInputStream in = new FileInputStream(dwFile2);
                int i;
                while((i=in.read())!= -1){
                    out.write(i);
                }
                in.close();
                out.close();
            }
            else{
                out.print("Không tồn tại video cần download");
            }
                        
        }
            else{
                 out.print("Không tồn tại cần download");
            }
    }}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
