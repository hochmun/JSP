package service.user1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommonService;

public class ModifyServiceImpl implements CommonService {
	private static ModifyServiceImpl instance = new ModifyServiceImpl();
	public static ModifyServiceImpl getInstance() {
		return instance;
	}
	private ModifyServiceImpl() {}

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		return "/user1/modify.jsp";
	}

}
