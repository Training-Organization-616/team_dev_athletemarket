package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.dao.DAOException;
import la.dao.UserDAO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		if (action == null || action.length() == 0) {
			gotoPage(request, response, "/registUser.jsp");
		} else if (action.equals("add")) {
			// 新規登録入力

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
				// 空白チェック

				if (name == null || name.isEmpty()
						|| address == null || address.isEmpty()
						|| tel == null || tel.isEmpty()
						|| birth_day == null || birth_day.isEmpty()
						|| email == null || email.isEmpty()
						|| password == null || password.isEmpty()
						|| passwordCheck == null || passwordCheck.isEmpty()) {
					// 未入力チェック
					request.setAttribute("message", "未入力の情報があります");
					gotoPage(request, response, "/registUser.jsp");
					// エラーメッセージを出力して新規登録画面に戻る
					return;
				}

				if (!password.equals(passwordCheck)) {
					// パスワードと確認用パスワード一致チェック
					request.setAttribute("message", "パスワードが一致しません");
					gotoPage(request, response, "/registUser.jsp");
					return;
				}

				UserDAO dao = new UserDAO();

				if (dao.findByEmailInBlackCusomers(email)) {
					// ブラックリストチェック
					request.setAttribute("message", "ブラックリストに登録されています");
					gotoPage(request, response, "/registUser.jsp");
					return;
				}

				if (dao.findByEmailAndPasseword(email, password)) {
					// メールアドレス重複チェック
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
				// 新規登録確認画面にリダイレクト
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。DAO");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} else if (action.equals("addUser")) {
			// 新規登録情報送信

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
				gotoPage(request, response, "/login.jsp");
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "登録処理中に内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} else if (action.equals("update")) {
			// 会員情報画面

			if (session == null) {
				// セッションがない、またはログイン情報がない場合はログイン画面へリダイレクト
				request.setAttribute("message", "ログインしてください");
				gotoPage(request, response, "/login.jsp");
				return;
			}

			// セッションからユーザー情報を取得してリクエストスコープに設定
			String name = (String) session.getAttribute("name");
			String address = (String) session.getAttribute("address");
			String tel = (String) session.getAttribute("tel");
			String birthDay = (String) session.getAttribute("birthDay");
			String email = (String) session.getAttribute("email");
			String start_day = (String) session.getAttribute("start_day");

			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("tel", tel);
			request.setAttribute("birthDay", birthDay);
			request.setAttribute("email", email);
			request.setAttribute("start_day", start_day);

			gotoPage(request, response, "/customerInformation.jsp");
			// 会員情報画面へリダイレクト

		} else if (action.equals("updatePage")) {

			// セッションからユーザー情報を取得してリクエストスコープに設定
			String name = (String) session.getAttribute("name");
			String address = (String) session.getAttribute("address");
			String tel = (String) session.getAttribute("tel");
			String birthDay = (String) session.getAttribute("birthDay");
			String email = (String) session.getAttribute("email");

			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("tel", tel);
			request.setAttribute("birthDay", birthDay);
			request.setAttribute("email", email);

			gotoPage(request, response, "/customerChange.jsp");
			// 会員情報変更画面にリダイレクト

		} else if (action.equals("updateCheck")) {

			try {
				// フォームから変更データを取得
				CustomerBean bean = (CustomerBean) session.getAttribute("loginUser");
				String name = request.getParameter("name1");
				name = (name != null) ? name.strip() : "";

				String address = request.getParameter("address1");
				address = (address != null) ? address.strip() : "";

				String tel = request.getParameter("tel1");
				tel = (tel != null) ? tel.strip() : "";

				String birth_day = request.getParameter("birth_day1");
				birth_day = (birth_day != null) ? birth_day.strip() : "";

				String email = request.getParameter("email1");
				email = (email != null) ? email.strip() : "";

				String password = request.getParameter("password1");
				password = (password != null) ? password.strip() : "";

				String passwordCheck = request.getParameter("passwordCheck1");
				passwordCheck = (passwordCheck != null) ? passwordCheck.strip() : "";
				// 空白チェック

				if (name == null || name.isEmpty()
						|| address == null || address.isEmpty()
						|| tel == null || tel.isEmpty()
						|| birth_day == null || birth_day.isEmpty()
						|| email == null || email.isEmpty()
						|| password == null || password.isEmpty()
						|| passwordCheck == null || passwordCheck.isEmpty()) {
					// 未入力チェック
					request.setAttribute("message", "未入力の情報があります");
					gotoPage(request, response, "/customerChange.jsp");
					// エラーメッセージを出力して新規登録画面に戻る
					return;
				}

				if (!password.equals(passwordCheck)) {
					// パスワードと確認用パスワード一致チェック
					request.setAttribute("message", "パスワードが一致しません");
					gotoPage(request, response, "/customerChange.jsp");
					return;
				}

				// DAOを使ってデータベースの情報を更新
				UserDAO dao = new UserDAO();
				dao.updateUser(bean.getId(), name, address, tel, birth_day, email, password);

				bean.setName(name);

				// セッション情報を更新
				session.setAttribute("name", name);
				session.setAttribute("address", address);
				session.setAttribute("tel", tel);
				session.setAttribute("birth_day", birth_day);
				session.setAttribute("email", email);
				session.setAttribute("password", password);

				gotoPage(request, response, "/showItems.jsp");
				// 商品一覧表示画面にリダイレクト
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "情報の更新中にエラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} else if (action.equals("withdrawal")) {
			// 退会画面

			// セッションからユーザー情報を取得してリクエストスコープに設定
			String name = (String) session.getAttribute("name");
			String address = (String) session.getAttribute("address");
			String tel = (String) session.getAttribute("tel");
			String birthDay = (String) session.getAttribute("birthDay");
			String email = (String) session.getAttribute("email");
			String start_day = (String) session.getAttribute("start_day");

			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("tel", tel);
			request.setAttribute("birthDay", birthDay);
			request.setAttribute("email", email);
			request.setAttribute("start_day", start_day);

			gotoPage(request, response, "/withdrawal.jsp");
			// 会員情報確認画面の表示（退会用）

		} else if (action.equals("withdrawalUser")) {

			try {
				// セッションからユーザーID取得
				CustomerBean bean = (CustomerBean) session.getAttribute("loginUser");

				// DAOを利用して退会処理を実行
				UserDAO dao = new UserDAO();
				dao.withdrawalUser(bean.getId());

				// セッションを破棄
				session.invalidate();

				// 商品一覧表示
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ItemServlet?action=");
				dispatcher.forward(request, response);

				// 商品一覧表示画面にリダイレクト
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "退会処理中にエラーが発生しました。");
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
