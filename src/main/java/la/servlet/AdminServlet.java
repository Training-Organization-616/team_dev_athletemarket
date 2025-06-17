package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CustomerBean;
import la.dao.AdminDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		try {
			// パラメータの解析
			String action = request.getParameter("action");
			if (action == null || action.length() == 0) {
				// input_customerまたはパラメータなしの場合は顧客情報入力ページを表示 
				// customerInfo.jspにフォーム
				AdminDAO dao = new AdminDAO();
				List<CustomerBean> list = dao.findAllCustomer();
				request.setAttribute("customerlist", list);
				gotoPage(request, response, "/admin.jsp");
			} else if (action.equals("delete")) {
				String id = request.getParameter("id");
				AdminDAO dao = new AdminDAO();
				dao.deleteCustomer(Integer.parseInt(id));
				List<CustomerBean> list = dao.findAllCustomer();
				request.setAttribute("customerlist", list);
				gotoPage(request, response, "/admin.jsp");
			} else if (action.equals("search")) {
				String id = request.getParameter("userId");
				String name = request.getParameter("name");
				AdminDAO dao = new AdminDAO();
				List<CustomerBean> list = dao.findByIdAndName(id, name);
				request.setAttribute("customerlist", list);
				gotoPage(request, response, "/admin.jsp");

			} else {
				// actionの値が不正
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
