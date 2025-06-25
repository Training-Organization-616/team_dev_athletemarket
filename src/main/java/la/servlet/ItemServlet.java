package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.bean.ListingBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 日本語を正しく取得するため
		request.setCharacterEncoding("UTF-8");
		// actionを取得
		String action = request.getParameter("action");

		try {

			// セッション取得
			HttpSession session = request.getSession(true);

			// セッションスコープから値を取り出す
			CustomerBean bean = (CustomerBean) session.getAttribute("loginUser");

			// パラメータなし(全件取得)
			if (action == null || action.length() == 0) {

				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// 最新の情報を取得
				List<ListingBean> list = dao.findAllItems();

				// 検索結果なし
				if (list == null || list.size() == 0) {
					request.setAttribute("status", "出品状態の商品が存在しません");
					gotoPage(request, response, "/showItems.jsp");

				} else {

					request.setAttribute("list", list);
					gotoPage(request, response, "/showItems.jsp");

				}

				// 商品登録画面に遷移
			} else if (action.equals("listing")) {

				//ログインしているかの判断
				if (bean == null) {
					request.setAttribute("message", "ログインしてください");
					gotoPage(request, response, "/login.jsp");
				}

				// 初期選択用
				gotoPage(request, response, "/registItem.jsp");

				// 出品処理をDAOで実行
			} else if (action.equals("confirm")) {

				// カテゴリーID
				int categoryId = Integer.parseInt(request.getParameter("category_id"));
				// 商品名
				String name = request.getParameter("name").strip();
				// 価格
				int price = Integer.parseInt(request.getParameter("price"));
				// メモ
				String memo = request.getParameter("memo");

				//名前に空白（スペース）のみが入力された場合の処理
				// name = name.strip();
				if (name == null || name.length() == 0) {

					request.setAttribute("failure", "未入力の情報があります");
					// 選択したカテゴリー保持する
					request.setAttribute("category_id", categoryId);
					gotoPage(request, response, "registItem.jsp");

				} else {

					// DAOインスタンス生成
					ItemDAO dao = new ItemDAO();

					// 商品登録処理(会員IDは、セッション情報から)
					dao.registItem(bean.getId(), categoryId, name, price, memo);

					// 最新の情報を取得
					request.setAttribute("list", dao.findAllItems());
					gotoPage(request, response, "/showItems.jsp");

				}

				// 商品の詳細を表示
			} else if (action.equals("detail")) {

				// 商品IDを取得
				int id = Integer.parseInt(request.getParameter("id"));

				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// 商品一覧から商品IDを取得
				request.setAttribute("bean", dao.findById(id));
				gotoPage(request, response, "/itemDetail.jsp");

				// 商品の購入処理
			} else if (action.equals("purchase")) {

				//ログインしているかの判断
				if (bean == null) {
					request.setAttribute("message", "ログインしてください");
					gotoPage(request, response, "/login.jsp");
				}

				// 商品IDを取得
				int id = Integer.parseInt(request.getParameter("id"));

				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// 購入処理(商品IDと購入者ID)
				dao.purchaseItem(bean.getId(), id);

				// 最新の情報を取得
				request.setAttribute("list", dao.findAllItems());
				gotoPage(request, response, "/showItems.jsp");

				// マイページ表示（自分の出品商品を確認）
			} else if (action.equals("mypage")) {

				//ログインしているかの判断
				if (bean == null) {
					request.setAttribute("message", "ログインしてください");
					gotoPage(request, response, "/login.jsp");
				}

				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// beanから会員IDを取得して検索
				List<ListingBean> list = dao.findByCustomerId(bean.getId());

				// 結果がnull
				if (list == null || list.size() == 0) {

					// 最新の情報を取得
					request.setAttribute("status", "出品状態の商品が存在しません");
					gotoPage(request, response, "/mypage.jsp");

				} else {

					// 最新の情報を取得
					request.setAttribute("list", list);
					gotoPage(request, response, "/mypage.jsp");

				}

				// 二次開発追加分（購入履歴）
			} else if (action.equals("history")) {

				ItemDAO dao = new ItemDAO();

				// beanから会員IDを取得して検索
				List<ListingBean> list = dao.findPurchaseHistory(bean.getId());

				// 検索結果なし
				if (list == null || list.size() == 0) {

					// 最新の情報を取得
					request.setAttribute("status", "購入履歴が存在しません");
					gotoPage(request, response, "/purchaseHistory.jsp");

				} else {

					// 最新の情報を取得
					request.setAttribute("list", list);
					gotoPage(request, response, "/purchaseHistory.jsp");
				}

				// 並び替え処理（商品一覧）
			} else if (action.equals("sort")) {

				String key = request.getParameter("key");

				ItemDAO dao_sort = new ItemDAO();

				// リクエストスコープに設定
				request.setAttribute("key", key);
				// request.setAttribute("list", dao_sort.sortItems(key));

				// 最新の情報を取得
				List<ListingBean> list = dao_sort.sortItems(key);

				// 検索結果なし
				if (list == null || list.size() == 0) {

					request.setAttribute("status", "出品状態の商品が存在しません");
					gotoPage(request, response, "/showItems.jsp");

				} else {

					request.setAttribute("list", list);
					gotoPage(request, response, "/showItems.jsp");

				}

				// 並び替え処理(マイページ)
			} else if (action.equals("mypageSort")) {

				String key = request.getParameter("key");

				ItemDAO dao_sort = new ItemDAO();

				// リクエストスコープに設定
				request.setAttribute("key", key);
				//request.setAttribute("list", dao_sort.mypageSortItems(bean.getId(), key));

				// 最新の情報を取得
				List<ListingBean> list = dao_sort.mypageSortItems(bean.getId(), key);

				// 検索結果なし
				if (list == null || list.size() == 0) {

					request.setAttribute("status", "出品状態の商品が存在しません");
					gotoPage(request, response, "/mypage.jsp");

				} else {

					request.setAttribute("list", list);
					gotoPage(request, response, "/mypage.jsp");

				}

				//変更ボタンを押下
			} else if (action.equals("edit")) {

				// 商品IDを取得
				int id = Integer.parseInt(request.getParameter("id"));

				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// 商品一覧から商品IDを取得
				request.setAttribute("bean", dao.findById(id));
				gotoPage(request, response, "/editItem.jsp");

				//商品の変更
			} else if (action.equals("editConfirm")) {

				// 商品ID
				int itemId = Integer.parseInt(request.getParameter("itemId"));

				// カテゴリーID
				int categoryId = Integer.parseInt(request.getParameter("category_id"));
				// 商品名(スペース削除)
				String name = request.getParameter("name").strip();
				// 価格
				int price = Integer.parseInt(request.getParameter("price"));
				// メモ
				String memo = request.getParameter("memo");

				if (name == null || name.length() == 0) {

					request.setAttribute("failure", "未入力の情報があります");
					// 選択した情報を保持する
					ItemDAO dao = new ItemDAO();
					request.setAttribute("bean", dao.findById(itemId));
					gotoPage(request, response, "editItem.jsp");

				} else {

					// DAOインスタンス生成
					ItemDAO dao = new ItemDAO();

					// 商品登録処理(会員IDは、セッション情報から)
					dao.updateItem(itemId, categoryId, name, price, memo);

					//newstatusにメッセージをセット。
					request.setAttribute("itemstatus", "商品を変更しました");

					// beanから会員IDを取得して検索
					List<ListingBean> list = dao.findByCustomerId(bean.getId());

					// 検索結果なし
					if (list == null || list.size() == 0) {

						// 最新の情報を取得
						request.setAttribute("status", "出品状態の商品が存在しません");
						gotoPage(request, response, "/mypage.jsp");

					} else {

						// 最新の情報を取得
						request.setAttribute("list", list);
						gotoPage(request, response, "/mypage.jsp");

					}
				}

				// 商品削除
			} else if (action.equals("itemDelete")) {

				int id = Integer.parseInt(request.getParameter("id"));
				// DAOインスタンス生成
				ItemDAO dao = new ItemDAO();

				// 商品削除処理
				dao.deleteItem(id);

				// beanから会員IDを取得して検索
				List<ListingBean> list = dao.findByCustomerId(bean.getId());

				// 結果なし
				if (list == null || list.size() == 0) {

					// 最新の情報を取得
					request.setAttribute("status", "出品状態の商品が存在しません");
					gotoPage(request, response, "/mypage.jsp");

				} else {

					// 最新の情報を取得
					request.setAttribute("list", list);
					gotoPage(request, response, "/mypage.jsp");
				}

			} else {

				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");

			}
		} catch (DAOException e) {

			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
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
