package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.DAOException;
import la.dao.UserDAO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action == null || action.length() == 0) {
			gotoPage(request, response, "/registUser.jsp");
		} else if (action.equals("add")) {
			try {
				String name = request.getParameter("name");
				name = (name != null) ? name.strip() : "";

				String address = request.getParameter("address");
				address = (address != null) ? address.strip() : "";

				String tel = request.getParameter("tel");
				tel = (tel != null) ? tel.strip() : "";

				String birth_day = request.getParameter("birth_day");
				birth_day = (birth_day != null) ? birth_day.strip() : "";

				String email = request.getParameter("email");
				email = (email != null) ? email.strip() : "";

				String password = request.getParameter("password");
				password = (password != null) ? password.strip() : "";

				String passwordCheck = request.getParameter("passwordCheck");
				passwordCheck = (passwordCheck != null) ? passwordCheck.strip() : "";

				if (name == null || name.isEmpty()
						|| address == null || address.isEmpty()
						|| tel == null || tel.isEmpty()
						|| birth_day == null || birth_day.isEmpty()
						|| email == null || email.isEmpty()
						|| password == null || password.isEmpty()
						|| passwordCheck == null || passwordCheck.isEmpty()) {
					request.setAttribute("message", "未入力の情報があります");
					gotoPage(request, response, "/registUser.jsp");
					// エラーメッセージを出力して新規登録画面に戻る
					return;
				}

				if (!password.equals(passwordCheck)) {
					request.setAttribute("message", "パスワードが一致しません");
					gotoPage(request, response, "/registUser.jsp");
					return;
				}

				UserDAO dao = new UserDAO();

				if (dao.findByEmailInBlackCusomers(email)) {
					request.setAttribute("message", "ブラックリストに登録されています");
					gotoPage(request, response, "/registUser.jsp");
					return;
				}

				if (dao.findByEmailAndPasseword(email, password)) {
					request.setAttribute("message", "このメールアドレスは既に登録されています");
					gotoPage(request, response, "/registUser.jsp");
					return;
				}

				request.setAttribute("name", name);
				request.setAttribute("address", address);
				request.setAttribute("tel", tel);
				request.setAttribute("birth_day", birth_day);
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				gotoPage(request, response, "/registUserCheck.jsp");
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。DAO");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} else if (action.equals("addUser")) {
			try {
				// 確認ページから送信された情報を取得
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String birth_day = request.getParameter("birth_day");
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				UserDAO dao = new UserDAO();
				dao.registUser(name, address, tel, birth_day, email, password);

				// ログイン画面へリダイレクト
				gotoPage(request, response, "/regist.jsp");
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "登録処理中に内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
