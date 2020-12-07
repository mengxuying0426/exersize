package net.onest.mxy.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = DbUtil.getCon();
			pstm = con.prepareStatement("select * from user where userid = ?");
			pstm.setInt(1, 1);
			rs = pstm.executeQuery();
			while(rs.next()) {
				System.out.println("id=1的用户信息"+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
