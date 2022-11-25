package kr.co.Jboard2.controller.list;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Jboard2.Service.file.FileService;
import kr.co.Jboard2.vo.FileVO;

@WebServlet("/download.do")
public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FileService service = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileVO vo = service.selectFileData(req.getParameter("no"));
		service.updateDownloadCount(vo.getFno());
		
		String fileName = vo.getOriName();
			fileName = URLEncoder.encode(fileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+fileName+";");
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		String savePath = req.getServletContext().getRealPath("/file");
		File file = new File(savePath + "/" + vo.getNewName());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		while(true) {
			int data = bis.read();
			
			if(data == -1) {
				break;
			}
			
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}
}
