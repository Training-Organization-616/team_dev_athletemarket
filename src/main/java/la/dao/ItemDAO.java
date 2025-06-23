package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ListingBean;

public class ItemDAO {

	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:team_dev_athletemarket";
	private String user = "student";
	private String pass = "himitu";

	public ItemDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	// 全ての商品を取得(出品状態のみ)
	public List<ListingBean> findAllItems() throws DAOException {

		// SQL文の作成
		String sql = "SELECT * FROM listing WHERE purchase_day IS NULL";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// SQLの実行
			ResultSet rs = st.executeQuery();
			List<ListingBean> list = new ArrayList<ListingBean>();

			// 結果の取得
			while (rs.next()) {

				ListingBean bean = new ListingBean();
				bean.setId(rs.getInt("id"));
				bean.setSellerId(rs.getInt("seller_id"));
				bean.setSellerName(rs.getString("seller_name"));
				bean.setCategoryName(rs.getString("category_name"));
				bean.setImageName(rs.getString("image_name"));
				bean.setItemName(rs.getString("item_name"));
				bean.setPrice(rs.getInt("price"));
				bean.setMemo(rs.getString("memo"));
				list.add(bean);

			}
			// 商品一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("出品商品の取得に失敗しました。");
		}

	}

	// 自分の出品商品全てを出力
	public List<ListingBean> findByCustomerId(int customerId) throws DAOException {

		// SQL文の作成
		String sql = "SELECT * FROM listing WHERE seller_id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// エスケープ
			st.setInt(1, customerId);

			// SQLの実行
			ResultSet rs = st.executeQuery();
			List<ListingBean> list = new ArrayList<ListingBean>();

			// 結果の取得
			while (rs.next()) {

				ListingBean bean = new ListingBean();
				bean.setId(rs.getInt("id"));
				bean.setSellerId(rs.getInt("seller_id"));
				bean.setSellerName(rs.getString("seller_name"));
				bean.setCategoryName(rs.getString("category_name"));
				bean.setImageName(rs.getString("image_name"));
				bean.setItemName(rs.getString("item_name"));
				bean.setPrice(rs.getInt("price"));
				bean.setPurchaseDay(rs.getString("purchase_day"));
				bean.setMemo(rs.getString("memo"));
				list.add(bean);

			}
			// 商品一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("出品商品の取得に失敗しました。");
		}

	}

	// 購入履歴(直近5件)
	public List<ListingBean> findPurchaseHistory(int customerId) throws DAOException {

		// SQL文の作成(購入日の降順で上位5件)
		String sql = "SELECT id, image_name, item_name, category_name, seller_name, price, purchase_day FROM listing WHERE purchase_day IS NOT NULL AND purchaser_id = ?\n"
				+ "ORDER BY purchase_day DESC LIMIT 5";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, customerId);

			// SQLの実行
			ResultSet rs = st.executeQuery();
			List<ListingBean> list = new ArrayList<ListingBean>();

			// 結果の取得
			while (rs.next()) {

				ListingBean bean = new ListingBean();
				bean.setId(rs.getInt("id"));
				bean.setImageName(rs.getString("image_name"));
				bean.setItemName(rs.getString("item_name"));
				bean.setCategoryName(rs.getString("category_name"));
				bean.setSellerName(rs.getString("seller_name"));
				bean.setPrice(rs.getInt("price"));
				bean.setPurchaseDay(rs.getString("purchase_day"));
				list.add(bean);

			}
			// 商品一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("出品商品の取得に失敗しました。");
		}

	}

	// 商品詳細の取得
	public ListingBean findById(int id) throws DAOException {

		// SQL文の作成
		String sql = "SELECT * FROM listing WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// エスケープ
			st.setInt(1, id);

			// SQLの実行
			ResultSet rs = st.executeQuery();
			ListingBean bean = new ListingBean();

			// 結果の取得
			if (rs.next()) {

				bean.setId(rs.getInt("id"));
				bean.setSellerId(rs.getInt("seller_id"));
				bean.setSellerName(rs.getString("seller_name"));
				bean.setCategoryName(rs.getString("category_name"));
				bean.setImageName(rs.getString("image_name"));
				bean.setItemName(rs.getString("item_name"));
				bean.setPrice(rs.getInt("price"));
				bean.setPurchaseDay(rs.getString("purchase_day"));
				bean.setMemo(rs.getString("memo"));

			}
			// 商品一覧をListとして返す
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("商品詳細情報の取得に失敗しました。");
		}

	}

	// 商品の登録
	public int registItem(int sellerId, int categoryId, String name, int price, String memo)
			throws DAOException {

		// SQL文の作成(メモなしならそのまま)
		String sql = "INSERT INTO items(seller_id, category_id, name, price) VALUES(?, ?, ?, ?)";

		// メモあり
		if (memo.length() != 0) {
			sql = "INSERT INTO items(seller_id, category_id, name, price, memo) VALUES(?, ?, ?, ?, ?)";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// エスケープ
			st.setInt(1, sellerId);
			st.setInt(2, categoryId);
			st.setString(3, name);
			st.setInt(4, price);

			// メモありならエスケープ
			if (memo.length() != 0) {
				st.setString(5, memo);
			}

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("商品登録処理の操作に失敗しました。");
		}
	}

	// 商品の購入処理
	public int purchaseItem(int customerId, int itemId) throws DAOException {

		// SQL文の作成
		String sql = "UPDATE items SET purchaser_id = ?, purchase_day = CURRENT_DATE WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// エスケープ
			st.setInt(1, customerId);
			st.setInt(2, itemId);

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("購入処理の操作に失敗しました。");
		}

	}

	//退会、強制退会後の商品削除処理の追加
	public int deleteUserItems(int id) throws DAOException {
		// SQL文の作成
		String sql = "delete from items where seller_id= ? ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// 商品名と値段の指定
			st.setInt(1, id);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

}
