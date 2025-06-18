package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import la.bean.CustomerBean;

public class CustomerDAO {
	// URL、ユーザ名、パスワードの準備(今回のデータベースの入力事項を確認する)
	private String url = "jdbc:postgresql:team_dev_athletemarket";
	private String user = "student";
	private String pass = "himitu";

	public CustomerDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	/**
	 * メールアドレスとパスワードの組み合わせ検索
	 */
	public CustomerBean findByEmailAndPassword(String email, String password) throws DAOException {
		// SQL文の作成
		//6月17直し１
		//String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
		String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダの設定
			st.setString(1, email);
			st.setString(2, password);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					//6月17直し１
					//	String birthDay = rs.getString("birthDay"); 注意）
					Date birth_day0 = rs.getDate("birth_day");
					String birth_day = birth_day0.toString();

					//6月17------------------
					email = rs.getString("email");
					password = rs.getString("password");
					Date start_day0 = rs.getDate("start_day");
					String start_day = start_day0.toString();
					int userType = rs.getInt("user_type");
					CustomerBean bean = new CustomerBean(id, name, address, tel, birth_day, email, password, start_day,
							userType);
					return bean;
				} else {
					return null; // 主キーに該当するレコードなし
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
