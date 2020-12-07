package com.example.servlet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.entitys2.WTiInfo;
import com.example.entitys2.WrongTi;
import com.example.utils2.DbUtil;

/**
 * Servlet implementation class DownloadsCuoChoiceServlet
 */
@WebServlet("/DownloadsCuoChoiceServlet")
public class DownloadsCuoChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadsCuoChoiceServlet() {
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
			//构造对象
			WTiInfo wTiInfo = new WTiInfo();
	        List<WrongTi> wrongTis = new ArrayList<>();
	        Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			System.out.println(str);
			
			try {
				con = DbUtil.getCon();
				pstm = con.prepareStatement("select * from wrongti1 where username = ? and kemu = ?");
				pstm.setString(1, "cxk");
				pstm.setString(2, str);
				rs = pstm.executeQuery();
				while(rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					int tiid = rs.getInt("tiid");
					int tista = rs.getInt("tista");
					String kemu = rs.getString("kemu");
					String stem = rs.getString("stem");
					String opta = rs.getString("opta");
					String optb = rs.getString("optb");
					String optc = rs.getString("optc");
					String optd = rs.getString("optd");
					String correct = rs.getString("correct");
					String analysis = rs.getString("analysis");
					int keynum = rs.getInt("keynum");
					WrongTi wrongTi = new WrongTi(id,username,tiid,tista,kemu,stem,opta,optb,optc,optd,correct,analysis,keynum);
					wrongTis.add(wrongTi);
				}
				wTiInfo.setWrongTis(wrongTis);
				
				//把对象转换为json串
				String json = convertToJson(wTiInfo);
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
	}

	private String convertToJson(WTiInfo wTiInfo) {
		String json = null;
		//从内向外构造json串
		//获取WTiInfo对象的集合属性
		List<WrongTi> wrongTis = wTiInfo.getWrongTis();
		JSONArray jArray = new JSONArray();
		for(WrongTi wrongTi:wrongTis) {
			JSONObject JChoice = new JSONObject();
			JChoice.put("id", wrongTi.getId());
			JChoice.put("username", wrongTi.getUsername());
			JChoice.put("kemu", wrongTi.getKemu());
			JChoice.put("tiid", wrongTi.getTiid());
			JChoice.put("tista", wrongTi.getTista());
			JChoice.put("stem", wrongTi.getStem());
			JChoice.put("opta", wrongTi.getOpta());
			JChoice.put("optb", wrongTi.getOptb());
			JChoice.put("optc", wrongTi.getOptc());
			JChoice.put("optd", wrongTi.getOptd());
			JChoice.put("correct",wrongTi.getCorrect());
			JChoice.put("keynum", wrongTi.getKeynum());
			JChoice.put("analysis", wrongTi.getAnalysis());
			jArray.put(JChoice);
		}
		JSONObject JChoice = new JSONObject();
		JChoice.put("wrongtis", jArray);
		json = JChoice.toString();
		System.out.println(json);
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
