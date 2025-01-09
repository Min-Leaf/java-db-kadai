package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
	public static void main(String[] args) {
		// データベース接続情報
		final String URL = "jdbc:mariadb://localhost:8889/java_db";
		final String USER_NAME = "root";
		final String PASSWORD = "root";

		// CREATE TABLE文のフォーマット
		String sql = """
				CREATE TABLE employees (
				  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
				  name VARCHAR(60) NOT NULL,
				  mail VARCHAR(255) NOT NULL,
				              age INT(11),
				              address VARCHAR(255)
				);
				""";

		// データベース接続 ＆ SQL文の送信準備
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				Statement statement = con.createStatement()) {

			// SQL文を実行（DBMSに送信）
			int rowCnt = statement.executeUpdate(sql);
			Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("データベース接続成功: " + URL);
			System.out.println("テーブルを作成:rowCnt=" + rowCnt);
		} catch (SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
		}
	}
}