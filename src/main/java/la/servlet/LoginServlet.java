//JM80の内容
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
import la.dao.CustomerDAO;

/**
 * LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// actionパラメータの取得
			String action = request.getParameter("action");

			// セッションスコープにログインしている顧客情報を保存する ?　session.setAttribute("customer", bean);　でcustomerBeanを「保存
			//ログイン時にデータ保存
			HttpSession session = request.getSession();

			if (action == null || action.length() == 0) {
				// 1：パラメータなしの場合はログイン画面の表示
				gotoPage(request, response, "/login.jsp");

			} else if (action.equals("login")) {
				// 2.actionパラメータがlogin(action=login)の場合はログイン処理(Httpメソッド:post)
				//jspのname="email","password"の情報を持ってくる

				String email = request.getParameter("email");
				String password = request.getParameter("password");

				// 変更メールアドレスとパスワードで検索(DAO情報を呼び出す)
				CustomerDAO dao = new CustomerDAO();
				//				//追加(customer)
				//				CustomerBean customer = dao.findByEmailAndPassword(email, password);
				//				//
				CustomerBean bean = dao.findByEmailAndPassword(email, password);

				//追加３----

				//追加３----

				//追加２----
				//データベースの値とDaoに存在するsql(customers)に一致すれば管理者ページor一般会員ページに遷移
				if (bean != null) {
					//データが入っているbean内の"user_type"において、管理者用のログインをuser_type==1、一般会員用のログインをuser_type==2に設定したい。
					//管理者用のログインをuser_type==1

					// ログインした顧客情報をセッションに保存
					session.setAttribute("loginUser", bean);

					// 管理者
					if (bean.getUserType() == 1) {
						//session.setAttribute("loginUser", bean);
						response.sendRedirect("AdminServlet");

						// 一般ユーザー
					} else if (bean.getUserType() == 2) {
						//session.setAttribute("loginUser", bean);
						response.sendRedirect("ItemServlet");
					}

				} else if (bean == null) {
					//次に進む.
				}
				//追加２-----

				//ログインにおいて、顧客情報のメールアドレスとパスワードのいずれかがエラーのとき(追加）
				//追加 isEmpty():空文字
				//if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
				if (email.isEmpty() || password.isEmpty()) {
					request.setAttribute("message", "ユーザー名またはパスワードが入力されていない。");
					gotoPage(request, response, "/login.jsp");
				}
				///追加---------------

				//				// メールアドレスとパスワードで検索(DAO情報を呼び出す)
				//				CustomerDAO dao = new CustomerDAO();
				//				CustomerBean bean = dao.findByEmailAndPassword(email, password);

				// 顧客情報が存在しなかった場合はエラー
				if (bean == null) {
					// ログインページへ
					request.setAttribute("message", "入力された情報が会員情報と一致しない");
					gotoPage(request, response, "/login.jsp");
					return;
				}

				//				// セッションスコープにログインしている顧客情報を保存する ?　session.setAttribute("customer", bean);　でcustomerBeanを「保存
				//				//ログイン時にデータ保存
				//				HttpSession session = request.getSession();
				//				session.setAttribute("customer", bean);

			} else if (action.equals("logout")) {
				// 3.actionパラメータがlogoutの場合はログアウト処理

				// セッションスコープから値を取り出す
				CustomerBean bean = (CustomerBean) session.getAttribute("loginUser");

				// 管理者
				if (bean.getUserType() == 1) {

					// セッションスコープの顧客情報を破棄する session:ブラウザとサーバの間でデータを保持する
					//セッションに保存されている顧客情報("customer")を消去する（ログアウト時)
					session.removeAttribute("loginUser");

					//セッション情報消去
					session.invalidate();

					// ログイン画面を表示
					response.sendRedirect("LoginServlet");

					// 一般ユーザー
				} else if (bean.getUserType() == 2) {

					// セッションスコープの顧客情報を破棄する session:ブラウザとサーバの間でデータを保持する
					//セッションに保存されている顧客情報("customer")を消去する（ログアウト時)
					session.removeAttribute("loginUser");

					//セッション情報削除
					session.invalidate();

					// 商品一覧を表示
					response.sendRedirect("ItemServlet");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	/**
	 * 2)doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 画面遷移用
	 */
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}