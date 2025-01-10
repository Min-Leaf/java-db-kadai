package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {
	public static void main(String[] args) {
		// データベース接続情報
		final String URL = "jdbc:mariadb://localhost:8889/java_db";
		final String USER_NAME = "root";
		final String PASSWORD = "root";

		// Step3. scoresテーブル内の点数データを更新する
		String sql_update = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id=5";

		// ORDER BY句つきのSELECT文のフォーマット
		String sql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";

		// データベース接続 ＆ SQL文の送信準備
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				Statement statement = con.createStatement()) {
			System.out.println("データベース接続成功：" + URL);

			//SQL文を実行（DBMSに送信）                                                
			System.out.println("レコード更新を実行します");
			int rowCnt = statement.executeUpdate(sql_update);
			System.out.println(rowCnt + "件のレコードが更新されました");
			// SQL文を実行（DBMSに送信）
			ResultSet result = statement.executeQuery(sql);
			System.out.println("数学・英語の点数が高い順に並べ替えました");

			// SQL文の実行結果を抽出
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int score_math = result.getInt("score_math");
				int score_english = result.getInt("score_english");

				System.out.println(result.getRow() + "件目：生徒ID=" + id
						+ "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english);
			}
		} catch (SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
		} catch (Exception e) {
			System.out.println("予期せぬエラーが発生しました：" + e.getMessage());
		}
	}
}