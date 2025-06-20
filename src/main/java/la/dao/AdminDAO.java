package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CustomerBean;

public class AdminDAO {
	private String url = "jdbc:postgresql:team_dev_athletemarket";
	private String user = "student";
	private String pass = "himitu";

	public AdminDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public int registBlackCustomer(int customerId, String email) throws DAOException {

		// 注文情報のOrderedテーブルへの追加
		String sql = "INSERT INTO black_customers(id,email) VALUES(?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			//			st.setInt(1, id);
			st.setInt(1, customerId);
			st.setString(2, email);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public List<CustomerBean> findAllCustomer() throws DAOException {
		// SQL文の作成
		String sql = " select * from customers where user_type = 2 order by id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<CustomerBean> list = new ArrayList<CustomerBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				Date birthDay1 = rs.getDate("birth_day");
				String birthDay = birthDay1.toString();
				String email = rs.getString("email");
				String password = rs.getString("password");
				Date startDay1 = rs.getDate("start_day");
				String startDay = startDay1.toString();
				int userType = rs.getInt("user_type");

				//取得したカテゴリー情報をCategoryBeanのコンストラクタを使用してオブジェクトを生成
				CustomerBean bean = new CustomerBean(id, name, address, tel, birthDay, email, password, startDay,
						userType);
				//生成したオブジェクトをlistに追加（このlistは呼び出し元のサーブレットに返却する）
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public int deleteCustomer(int id) throws DAOException {
		// SQL文の作成
		String sql = "delete from customers where id= ? ";

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

	public List<CustomerBean> findByIdAndName(String id, String name)
			throws DAOException {
		// SQL文の作成
		String sql = "select * from customers WHERE 1 = 1 ";
		// 条件の追加
		if (name != null && name.length() != 0) {
			sql += "AND name like ? ";
		}
		if (id != null && id.length() != 0) {
			sql += "AND id = ? ";
		}
		sql += " and user_type = 2 order by id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダの設定
			int i = 0; // カウンタ変数
			if (name != null && name.length() != 0) {
				i++;
				st.setString(i, "%" + name + "%");
			}
			if (id != null && id.length() != 0) {
				i++;
				st.setInt(i, Integer.parseInt(id));
			}

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<CustomerBean> list = new ArrayList<CustomerBean>();
				while (rs.next()) {
					int id2 = rs.getInt("id");
					String name2 = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					Date birthDay1 = rs.getDate("birth_day");
					String birthDay = birthDay1.toString();
					String email = rs.getString("email");
					String password = rs.getString("password");
					Date startDay1 = rs.getDate("start_day");
					String startDay = startDay1.toString();
					int userType = rs.getInt("user_type");

					//取得したカテゴリー情報をCategoryBeanのコンストラクタを使用してオブジェクトを生成
					CustomerBean bean = new CustomerBean(id2, name2, address, tel, birthDay, email, password, startDay,
							userType);
					//生成したオブジェクトをlistに追加（このlistは呼び出し元のサーブレットに返却する）
					list.add(bean);
				}
				// 商品一覧をListとして返す
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
