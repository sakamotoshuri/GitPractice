package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Kadai_1;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class Kadai_1_DAO {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerKadai_1(Kadai_1 kadai_1) {
		String sql = "INSERT INTO kadai15_1 VALUES(?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		
		// ランダムなソルトの取得(今回は32桁で実装)
		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(kadai_1.getPassword(), salt);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, kadai_1.getName());
			pstmt.setInt(2, kadai_1.getAge());
			pstmt.setString(3, kadai_1.getGender());
			pstmt.setString(4, kadai_1.getPhone_number());
			pstmt.setString(5, kadai_1.getMail());
			pstmt.setString(6, hashedPw);
			pstmt.setString(7, salt);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	// 学生のデータを全件取得する
		public static List<Kadai_1> selectAllKadai_1() {
			
			// 返却用変数
			List<Kadai_1> result = new ArrayList<>();
			String sql = "SELECT * FROM kadai15_1";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				try (ResultSet rs = pstmt.executeQuery()){
					while(rs.next()) {
						String name = rs.getString("name");
						int age = rs.getInt("age");
						String gender = rs.getString("gender");
						String phone_number = rs.getString("phone_number");
						String mail = rs.getString("mail");
						Kadai_1 kadai_1 = new Kadai_1(name, age, gender, phone_number, mail, null, null, null);
						
						result.add(kadai_1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			// Listを返却する。0件の場合は空のListが返却される。
			return result;
		}
		//削除
		public static int DeleteKadai_1(String mail) {
			
			String sql = "DELETE FROM kadai15_1 WHERE mail = ?";
			int result = 0;
			try (
					Connection con = getConnection();	// DB接続
					PreparedStatement pstmt = con.prepareStatement(sql);			// 構文解析
					){
				
				pstmt.setString(1,mail );
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} finally {
				System.out.println(result + "件削除しました。");
			}
			return  result;
		}	
		// メールアドレスを元にソルトを取得
		public static String getSalt(String mail) {
			String sql = "SELECT salt FROM kadai15_1 WHERE mail = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String salt = rs.getString("salt");
						return salt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// ログイン処理
		public static Kadai_1 login(String mail, String hashedPw) {
			String sql = "SELECT * FROM kadai15_1 WHERE mail = ? AND password = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);
				pstmt.setString(2, hashedPw);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String name = rs.getString("name");
						int age = rs.getInt("age");
						String gender = rs.getString("gender");
						String phone_number = rs.getString("phone_number");
						String salt = rs.getString("salt");
						
						return new Kadai_1(name, age, gender, phone_number, mail, salt, null, null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		
}