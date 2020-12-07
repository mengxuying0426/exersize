package com.example.servlet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.utils2.DbUtil;

/**
 * Servlet implementation class CollectOperation
 */
@WebServlet("/CollectOperation")
public class CollectOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��Ϣ
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
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int result = 0;
			
			//��ӵ��ղ�
			try {
				con = DbUtil.getCon();
				if(i==1) {
					pstm = con.prepareStatement("select * from collection where username = ? and tiid = ?");
					pstm.setString(1,"cxk");
					pstm.setInt(2, tiid);
					rs = pstm.executeQuery();
					if(rs.next()) {
						System.out.println("�Ѵ����ղؼ���");
					}else {
						pstm = con.prepareStatement("insert into collection values(null,?,?,?)");
						pstm.setString(1, "cxk");
						pstm.setInt(2, tiid);
						pstm.setInt(3,tista);
						result = pstm.executeUpdate();
						if(result>0) {
							System.out.println("��ӳɹ�"+tiid);
						}else {
							System.out.println("���ʧ��"+tiid);
						}
					}
				}else {
					//�Ƴ��ղ�
					pstm = con.prepareStatement("delete from collection where username=? and tiid=?");
					pstm.setString(1, "cxk");
					pstm.setInt(2, tiid);
					result = pstm.executeUpdate();
					if(result>0) {
						System.out.println("ɾ���ɹ�"+tiid);
					}else {
						System.out.println("ɾ��ʧ��"+tiid);
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
