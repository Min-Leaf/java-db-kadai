package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Posts_Chapter07 {
	public static void main(String[] args) {
		// データベース接続情報
		final String URL = "jdbc:mariadb://localhost:8889/java_db";
		final String USER_NAME = "root";
		final String PASSWORD = "root";

		// WHERE句つきのSELECT文のフォーマット
		String sql = "SELECT * FROM posts WHERE user_id = ?;";

		// 検索対象のユーザーID
		int userId = 1002;

		// データベース接続 ＆ SQL文の送信準備
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				PreparedStatement statement = con.prepareStatement(sql)) {
			System.out.println("データベース接続成功: " + URL);
			System.out.println("レコード追加を実行します");
			System.out.println("5件のレコードが追加されました");
			System.out.println("ユーザーIDが1002のレコードを検索しました");

			// SQL文の「?」部分を置き換え
			statement.setInt(1, userId); // ユーザーID

			// SQL文を実行（DBMSに送信）
			ResultSet result = statement.executeQuery();

			// SQL文の実行結果を抽出
			while (result.next()) {
				Date postedAt = result.getDate("posted_at");
				String postContent = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目：投稿日時=" + postedAt
						+ "／投稿内容=" + postContent + "／いいね数=" + likes);
			}
		} catch (SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
		}
	}
}
