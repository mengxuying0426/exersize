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

import com.example.entitys2.ChoiceInfo;
import com.example.entitys2.SingleChoice;
import com.example.utils2.DbUtil;

/**
 * Servlet implementation class DownloadChoiceServlet
 */
@WebServlet("/DownloadChoiceServlet")
public class DownloadChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadChoiceServlet() {
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
			ChoiceInfo choiceInfo = new ChoiceInfo();
	        List<SingleChoice> singleChoices = new ArrayList<>();
	        Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			System.out.println(str);
			
			try {
				con = DbUtil.getCon();
				pstm = con.prepareStatement("select * from singlechoice where username = ? and kemu = ?");
				pstm.setString(1, "cxk");
				pstm.setString(2, str);
				rs = pstm.executeQuery();
				while(rs.next()) {
					String username = rs.getString("username");
					String kemu = rs.getString("kemu");
					int tiid = rs.getInt("tiid");
					String stem = rs.getString("stem");
					String opta = rs.getString("opta");
					String optb = rs.getString("optb");
					String optc = rs.getString("optc");
					String optd = rs.getString("optd");
					String correct = rs.getString("correct");
					int keynum = rs.getInt("keynum");
					String analysis = rs.getString("analysis");
					int motista = rs.getInt("motista");
					int iscollect = rs.getInt("iscollect");
					int iscuoti = rs.getInt("iscuoti");
					SingleChoice singleChoice = new SingleChoice(username,kemu,tiid,stem,opta,optb,optc,optd,correct,keynum,analysis,motista);
					singleChoices.add(singleChoice);
				}
				choiceInfo.setSingleChoices(singleChoices);
				//把对象转换为json串
				String json = convertToJson(choiceInfo);
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
	/**
	 * 将对象转换为json串
	 * @param choiceInfo
	 * @return
	 */
	private String convertToJson(ChoiceInfo choiceInfo) {
		String json = null;
		//从内向外构造json串
		//获取ChoiceInfo对象的集合属性
		List<SingleChoice> singleChoices = choiceInfo.getSingleChoices();
		JSONArray jArray = new JSONArray();
		for(SingleChoice singleChoice:singleChoices) {
			JSONObject JChoice = new JSONObject();
			JChoice.put("username", singleChoice.getUsername());
			JChoice.put("kemu", singleChoice.getKemu());
			JChoice.put("tiid", singleChoice.getTiid());
			JChoice.put("stem", singleChoice.getStem());
			JChoice.put("opta", singleChoice.getOptionA());
			JChoice.put("optb", singleChoice.getOptionB());
			JChoice.put("optc", singleChoice.getOptionC());
			JChoice.put("optd", singleChoice.getOptionD());
			JChoice.put("correct",singleChoice.getCorrect());
			JChoice.put("keynum", singleChoice.getKeyNum());
			JChoice.put("analysis", singleChoice.getAnalysis());
			JChoice.put("motista", singleChoice.getMotista());
			JChoice.put("iscollect", singleChoice.getIscollect());
			JChoice.put("iscuoti", singleChoice.getIscuoti());
			jArray.put(JChoice);
		}
		JSONObject JChoice = new JSONObject();
		JChoice.put("choices", jArray);
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
