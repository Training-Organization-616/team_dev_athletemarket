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
import la.bean.ListingBean;
import la.dao.AdminDAO;
import la.dao.DAOException;
import la.dao.ItemDAO;

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
			// パラメータなし(全件取得) //6月24日
			if (action == null || action.length() == 0) {
				// input_customerまたはパラメータなしの場合は顧客情報入力ページを表示 
				// customerInfo.jspにフォーム
				//追加１ItemDAOに変更して、進めるように
				AdminDAO dao = new AdminDAO();
				//ItemDAO dao = new ItemDAO();
				//	List<CustomerBean> list = dao.findAllCustomer();
				//追加１itemsDAO用の全ての商品を取得(出品状態のみ)をしている。
				List<CustomerBean> list = dao.findAllCustomer();
				if (list == null || list.size() == 0) {
					request.setAttribute("message", "該当データが存在しません");
					//追加
					//gotoPage(request, response, "/admin.jsp");
					gotoPage(request, response, "/adminitems.jsp");
				} else {
					//ここが間違い
					//request.setAttribute("customerlist", list);
					request.setAttribute("customerlist", list);
					//gotoPage(request, response, "/admin.jsp");
					gotoPage(request, response, "/admin.jsp");
				}

			} else if (action.equals("delete")) {
				String id = request.getParameter("id");
				AdminDAO dao = new AdminDAO();
				List<CustomerBean> list = dao.findByIdAndName(id, "");
				CustomerBean bean = list.get(0);
				dao.registBlackCustomer(bean.getId(), bean.getEmail());
				dao.deleteCustomer(Integer.parseInt(id));
				List<CustomerBean> list2 = dao.findAllCustomer();
				if (list2 == null || list2.size() == 0) {
					request.setAttribute("message", "該当データが存在しません");
					gotoPage(request, response, "/admin.jsp");
				} else {
					request.setAttribute("customerlist", list2);
					gotoPage(request, response, "/admin.jsp");
				}
			} //追加①始
				//管理者用の会員一覧画面にて、商品一覧リンクをクリックした際に、商品一覧画面が表示される。
			else if (action.equals("adminItems")) {
				//				String id = request.getParameter("id");
				//				AdminDAO dao = new AdminDAO();
				//				List<CustomerBean> list = dao.findByIdAndName(id, "");
				//				CustomerBean bean = list.get(0);
				//				dao.registBlackCustomer(bean.getId(), bean.getEmail());
				//				dao.deleteCustomer(Integer.parseInt(id));
				//				List<CustomerBean> list2 = dao.findAllCustomer();

				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// 最新の情報を取得
				List<ListingBean> list = dao.findAllItemsByAdmin();

				// 検索結果なし
				if (list == null || list.size() == 0) {
					request.setAttribute("status", "出品状態の商品がありません");
					gotoPage(request, response, "/adminitems.jsp");

				} else {

					request.setAttribute("list", list);
					gotoPage(request, response, "/adminitems.jsp");

				}
				//				if (list2 == null || list2.size() == 0) {
				//					request.setAttribute("message", "該当データが存在しません");
				//					gotoPage(request, response, "/admin.jsp");
				//				} else {
				//					request.setAttribute("customerlist", list2);
				//					gotoPage(request, response, "/admin.jsp");
				//				}
			} //追加①終
				//追加②始
				//AdminDAOで商品情報を削除する。
			else if (action.equals("adminItemDelete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				AdminDAO dao = new AdminDAO();
				// 商品削除処理
				dao.deleteItem(id);
				//List<CustomerBean> list = dao.findByIdAndName(id, "");
				//				List<CustomerBean> list = dao.findByIdAndName(id, "");
				//				List<ListingBean> list = dao.findAllCustomer(id, "");
				//今回は検索しないから、削除
				//List<ListingBean> list = dao.findAllCustomer(id, "");
				//商品の削除処理はAdminServletだと無限ループ
				//				gotoPage(request, response, "/AdminServlet");

				//(追加２)再び全件表示をさせて出力させる。
				ItemDAO dao1 = new ItemDAO();

				// 最新の情報を取得
				List<ListingBean> list = dao1.findAllItemsByAdmin();

				// 検索結果なし
				if (list == null || list.size() == 0) {
					request.setAttribute("status", "出品状態の商品がありません");
					gotoPage(request, response, "/adminitems.jsp");

				} else {

					request.setAttribute("list", list);
					gotoPage(request, response, "/adminitems.jsp");

				}
			}

			//				CustomerBean bean = list.get(0);
			//				dao.registBlackCustomer(bean.getId(), bean.getEmail());
			//				dao.deleteCustomer(Integer.parseInt(id));
			//				List<CustomerBean> list2 = dao.findAllCustomer();
			//				if (list2 == null || list2.size() == 0) {
			//					request.setAttribute("message", "該当データが存在しません");
			//					gotoPage(request, response, "/admin.jsp");
			//				} else {
			//					request.setAttribute("customerlist", list2);
			//					gotoPage(request, response, "/admin.jsp");
			//				}
			//			}
			//追加②終

			else if (action.equals("search")) {
				try {
					String id = request.getParameter("userId");
					id = id.strip();
					String name = request.getParameter("name");
					name = name.strip();
					AdminDAO dao = new AdminDAO();
					List<CustomerBean> list = dao.findByIdAndName(id, name);
					if (list == null || list.size() == 0) {
						request.setAttribute("message", "該当データが存在しません");
						gotoPage(request, response, "/admin.jsp");
					} else {
						request.setAttribute("customerlist", list);
						gotoPage(request, response, "/admin.jsp");
					}
				} catch (NumberFormatException e) {
					AdminDAO dao = new AdminDAO();
					List<CustomerBean> list = dao.findAllCustomer();
					request.setAttribute("customerlist", list);
					request.setAttribute("message", "無効な文字列です");
					gotoPage(request, response, "/admin.jsp");
				}

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
