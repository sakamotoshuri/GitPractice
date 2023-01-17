package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Kadai_1_DAO;
import dto.Kadai_1;
import util.GenerateHashedPw;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Kadai_1_LoginServlet")
public class Kadai_1_LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kadai_1_LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		
		String mail = request.getParameter("mail");
		String pw = request.getParameter("pw");
		
		// 入力されたIDをもとにソルトを取得する。
		String salt = Kadai_1_DAO.getSalt(mail);
		
		// 取得したソルトがnullの場合は対象のユーザがいないので、Errorでログイン画面に戻す
		if(salt == null) {
			String view = "WEB-INF/view/kadai_1_login.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}
		
		// 取得したソルトを使って入力したPWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(pw, salt);
		
		System.out.println("ログインチェックのソルト" + salt);
		System.out.println("ログインチェックのハッシュPw" + hashedPw);
		
		// 入力されたID、ハッシュしたPWに一致するユーザを検索する
		Kadai_1 kadai_1 = Kadai_1_DAO.login(mail, hashedPw);
		
		// 一致するユーザがいなければ、ログイン失敗
		if(kadai_1 == null) {
			String view = "WEB-INF/view/kadai_1_login.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			String view = "WEB-INF/view/sample-menu.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
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
