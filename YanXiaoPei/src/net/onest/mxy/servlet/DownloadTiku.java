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

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.json.JSONArray;
import org.json.JSONObject;

import net.onest.mxy.beans.BankInfo;
import net.onest.mxy.beans.QuestionBank;
import net.onest.mxy.utils.DbUtil;

/**
 * Servlet implementation class DownloadTiku
 */
@WebServlet("/DownloadTiku")
public class DownloadTiku extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DownloadTiku() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//构造对象
		BankInfo bankInfo = new BankInfo();
		List<QuestionBank> questionBanks = new ArrayList<>();
		//添加数据
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = DbUtil.getCon();
			pstm = con.prepareStatement("select * from questionbank where username = ?");
			pstm.setString(1, "cxk");
			rs = pstm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String kemu = rs.getString("kemu");
				int tinum = rs.getInt("tinum");
				int kemusta = rs.getInt("kemusta");
				QuestionBank questionBank = new QuestionBank(id,"cxk",kemu,tinum,kemusta);
				questionBanks.add(questionBank);
			}
			bankInfo.setQuestionBanks(questionBanks);
			//把对象转换为json串
			String json = convertToJson(bankInfo);
			//JSON串返回给客户端
			OutputStream outputStream = response.getOutputStream();
			System.out.println(json);
			outputStream.write(json.getBytes(Charset.forName("utf-8")));
			outputStream.flush();
			outputStream.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String convertToJson(BankInfo bankInfo) {
		String json = null;
		//从内向外构造json串
		//获取BankInfo对象的集合属性
		List<QuestionBank> questionBanks = bankInfo.getQuestionBanks();
		JSONArray jArray = new JSONArray();
		for(QuestionBank questionBank:questionBanks) {
			JSONObject JBank = new JSONObject();
			JBank.put("id", questionBank.getId());
			JBank.put("username",questionBank.getUsername());
			JBank.put("kemu", questionBank.getKemu());
			JBank.put("tinum", questionBank.getTinum());
			JBank.put("kemusta", questionBank.getKemuSta());
			//把当前JSONObject添加到JSONArray中
			jArray.put(JBank);
		}
		//创建外层JSONObject对象
		JSONObject JBank = new JSONObject();
		JBank.put("bank", jArray);
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
