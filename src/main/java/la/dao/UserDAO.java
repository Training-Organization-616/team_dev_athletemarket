package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:team_dev_athletemarket";
	private String user = "student";
	private String pass = "himitu";

	public UserDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public boolean findByEmailInBlackCusomers(String email) throws DAOException {

		String sql = "SELECT COUNT(*) FROM black_customers WHERE email = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
					// ブラックリストにメールアドレスが存在する場合はtrueを返す
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ブラックリスト確認に失敗しました。");
		}
		return false; // 存在しない場合はfalseを返す
	}

	public boolean findByEmailAndPasseword(String email, String password) throws DAOException {

		String sql = "SELECT COUNT(*) FROM customers WHERE email = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
					// メールアドレスが存在する場合はtrueを返す
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("メールアドレス確認に失敗しました。");
		}
		return false; // 存在しない場合はfalseを返す
	}
}
