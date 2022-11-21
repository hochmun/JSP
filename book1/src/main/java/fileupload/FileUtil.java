package fileupload;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
/*
 * 내용 : 파일 업로드용 유틸리티 클래스
 * 날짜 : 2022/11/20
 * 이름 : 심규영
 */
public class FileUtil {
	// 파일 업로드( multipart /form-data 요청) 처리
	public static MultipartRequest uploadFile(HttpServletRequest req,
			String saveDirectory, int maxPostSize) {
		try {
			// 파일 업로드
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
		} catch (Exception e) {
			// 업로드 실패
			e.printStackTrace();
			return null;
		}
	}
}
