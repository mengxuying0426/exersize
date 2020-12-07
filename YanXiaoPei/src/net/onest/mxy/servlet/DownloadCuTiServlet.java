package net.onest.mxy.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import net.onest.mxy.beans.BankInfo;
import net.onest.mxy.beans.QuestionBank;
import net.onest.mxy.beans.WBankInfo;
import net.onest.mxy.beans.WrongSet;
import net.onest.mxy.utils.DbUtil;

/**
 * Servlet implementation class DownloadCuTiServlet
 */
@WebServlet("/DownloadCuTiServlet")
public class DownloadCuTiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadCuTiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//构造对象
		WBankInfo wBankInfo = new WBankInfo();
		List<WrongSet> wrongSets = new ArrayList<>();
		//添加数据
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
			con = DbUtil.getCon();
			pstm = con.prepareStatement("select * from wrongset where username = ?");
			pstm.setString(1, "cxk");
			rs = pstm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String kemu = rs.getString("kemu");
				int kemusta = rs.getInt("kemusta");
				WrongSet wrongSet = new WrongSet(id,username,kemu,kemusta);
				wrongSets.add(wrongSet);
			}
			wBankInfo.setWrongSets(wrongSets);
			//把对象转换为json串
			String json = convertToJson(wBankInfo);
			//JSON串返回给客户端
			OutputStream outputStream = response.getOutputStream();
			System.out.println(json+"下错题库里");
			outputStream.write(json.getBytes(Charset.forName("utf-8")));
			outputStream.flush();
			outputStream.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 对象转换为json串
	 * @param wBankInfo
	 * @return
	 */
	private String convertToJson(WBankInfo wBankInfo) {
		String json = null;
		//从内向外构造json串
		//获取wBankInfo对象的集合属性
		List<WrongSet> wrongSets = wBankInfo.getWrongSets();
		JSONArray jArray = new JSONArray();
		for(WrongSet wrongSet:wrongSets) {
			JSONObject JBank = new JSONObject();
			JBank.put("id", wrongSet.getId());
			JBank.put("username", wrongSet.getUsername());
			JBank.put("kemu", wrongSet.getKemu());
			JBank.put("kemusta", wrongSet.getKemusta());
			//把当前JSONObject添加到JSONArray中
			jArray.put(JBank);
		}
		//创建外层JSONObject对象
		JSONObject JBank = new JSONObject();
		JBank.put("wbank", jArray);
		json = JBank.toString();
		return json;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
