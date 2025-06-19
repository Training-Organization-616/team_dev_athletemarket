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

	public void registUser(String name, String address, String tel, String birthDay, String email, String password)
			throws DAOException {

		String sql = "INSERT INTO customers (name, address, tel, birth_day, email, password, user_type) VALUES (?, ?, ?, ?, ?, ?, 2)";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {

			// パラメータをセット
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setDate(4, java.sql.Date.valueOf(birthDay));
			st.setString(5, email);
			st.setString(6, password);

			// クエリを実行
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("顧客情報の登録に失敗しました。");
		}

	}

	public void withdrawalUser(int id) throws DAOException {
		String sql = "DELETE FROM customers WHERE id = ?";
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {

			st.setInt(1, id);

			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DAOException("退会処理に失敗しました。指定されたメールアドレスが存在しません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("退会処理中にエラーが発生しました。");
		}
	}

	public void updateUser(int id, String name, String address, String tel, String birthDay, String email,
			String password) throws DAOException {
		String sql = "UPDATE customers SET name = ?, address = ?, tel = ?, birth_day = ?, email = ?, password = ? WHERE id = ?";
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {

			// パラメータを設定
			st.setString(1, name); // ユーザー名
			st.setString(2, address); // 住所
			st.setString(3, tel); // 電話番号
			st.setDate(4, java.sql.Date.valueOf(birthDay)); // 生年月日
			st.setString(5, email); // メールアドレス
			st.setString(6, password); // パスワード
			st.setInt(7, id); // ユーザーID（WHERE 条件）

			// クエリを実行
			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DAOException("ユーザー情報の更新に失敗しました。指定されたIDが存在しません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ユーザー情報の更新処理中にエラーが発生しました。");
		}
	}
}
