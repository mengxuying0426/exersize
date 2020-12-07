package com.example.servlet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.utils2.DbUtil;


/**
 * Servlet implementation class WrongOperation
 */
@WebServlet("/WrongOperation")
public class WrongOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrongOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取信息
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in, "utf-8"));
		String str = reader.readLine();
		if(null == str) {
					
		}else {
			System.out.println(str);
			String[] arr = str.split("&");
			int i = Integer.parseInt(arr[0]);
			int tiid = Integer.parseInt(arr[1]);
			int tista = Integer.parseInt(arr[2]);
			String kemu = arr[3];
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int result = 0;
			
			//添加到错题集
			try {
				con = DbUtil.getCon();
				if(i==1) {
					pstm = con.prepareStatement("select * from wrongti1 where username = ? and tiid = ?");
					pstm.setString(1,"cxk");
					pstm.setInt(2, tiid);
					rs = pstm.executeQuery();
					if(rs.next()) {
						System.out.println("已存在错题集中");
					}else {
						pstm = con.prepareStatement("insert into wrongti1 values(null,?,?,?,?,?,?,?,?,?,?,?,?)");
						pstm.setString(1, "cxk");
						pstm.setInt(2, tiid);
						pstm.setInt(3, tista);
						pstm.setString(4, kemu);
						pstm.setString(5, arr[4]);
						pstm.setString(6, arr[5]);
						pstm.setString(7, arr[6]);
						pstm.setString(8, arr[7]);
						pstm.setString(9,arr[8]);
						pstm.setString(10, arr[9]);
						pstm.setString(11, arr[10]);
						pstm.setInt(12, Integer.parseInt(arr[11]));
						result = pstm.executeUpdate();
						if(result>0) {
							System.out.println("添加成功"+tiid);
						}else {
							System.out.println("添加失败"+tiid);
						}
						pstm = con.prepareStatement("select * from wrongset where username = ? and kemu = ?");
						pstm.setString(1,"cxk");
						pstm.setString(2,kemu);
						rs = pstm.executeQuery();
						if(rs.next()) {
							System.out.println("已存在错题库中");
						}else {
							pstm = con.prepareStatement("insert into wrongset values(null,?,?,?)");
							pstm.setString(1, "cxk");
							pstm.setString(2, kemu);
							pstm.setInt(3, tista);
							result = pstm.executeUpdate();
							if(result>0) {
								System.out.println("添加错题库成功"+kemu);
							}else {
								System.out.println("添加错题库失败"+kemu);
							}
							
						}
						
						
						
						
					}
				}
				else {
					//移除错题
					pstm = con.prepareStatement("delete from wrongti1 where username=? and tiid=?");
					pstm.setString(1, "cxk");
					pstm.setInt(2, tiid);
					result = pstm.executeUpdate();
					if(result>0) {
						System.out.println("删除成功"+tiid);
					}else {
						System.out.println("删除失败"+tiid);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
