package rank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mysql.cj.Session;

import rank.domain.AddRankVO;
import rank.domain.RankVO;
import rank.socket.SocketClient;
import rnak.persistence.RankDAO;

/**
 * Servlet implementation class RankServlet
 */
@WebServlet("/RankServlet")
public class RankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RankServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String cmd = "";
		HttpSession session = request.getSession();
		cmd = request.getParameter("key");
		if (cmd.equals("search")) {
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			view.forward(request, response);
		} else if (cmd.equals("list")) {
			JSONObject rankObj = new JSONObject();
			JSONArray dateArry = new JSONArray();
			JSONArray rankArry = new JSONArray();
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = (ArrayList<RankVO>) session.getAttribute("obj2");
			try {
				int idx=rankVO.size()-1;
				for (int i = 0; i < rankVO.size(); i++) {
					dateArry.put(i, rankVO.get(idx).getSearch_date());
					rankArry.put(i, rankVO.get(idx--).getRank());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rankObj.put("date", dateArry);
				rankObj.put("data", rankArry);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.invalidate();
			String r_data = rankObj.toString();
			//System.out.println(r_data);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(r_data);
		} else if (cmd.equals("doexcel")) {
			request.setAttribute("rank", session.getAttribute("obj"));
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/doExcel.jsp");
			view.forward(request, response);
		}else if (cmd.equals("doexcel_sub")) {
			request.setAttribute("rank", session.getAttribute("obj2"));
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/doExcel.jsp");
			view.forward(request, response);
		} else if (cmd.equals("search_keyword")) { // 키워드 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToKeyword(request.getParameter("value"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			view.forward(request, response);
		} else if (cmd.equals("search_keyword_date")) { // 키워드 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToKeywordDate(request.getParameter("value"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			view.forward(request, response);
		}else if (cmd.equals("search_store")) { // 상점명 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToStore(request.getParameter("seval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("seval"));
			view.forward(request, response);
		}else if (cmd.equals("search_store_date")) { // 상점명 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToStoreDate(request.getParameter("seval"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("seval"));
			view.forward(request, response);
		} else if (cmd.equals("search_url")) { // url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToUrl(request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("thirval"));
			view.forward(request, response);
		}else if (cmd.equals("search_url_date")) { // url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToUrlDate(request.getParameter("thirval"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("thirval"));
			view.forward(request, response);
		} else if (cmd.equals("search_multikp")) { // 키워드, 상점명 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiKP(request.getParameter("value"), request.getParameter("seval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		}else if (cmd.equals("search_multikp_date")) { // 키워드, 상점명 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiKPDate(request.getParameter("value"), request.getParameter("seval"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		} else if (cmd.equals("search_multipu")) { // 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiPU(request.getParameter("seval"), request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		}else if (cmd.equals("search_multipu_date")) { // 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiPUDate(request.getParameter("seval"), request.getParameter("thirval"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		} else if (cmd.equals("search_multiku")) { // 키워드, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiKU(request.getParameter("value"), request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		} else if (cmd.equals("search_multiku_date")) { // 키워드, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiKUDate(request.getParameter("value"), request.getParameter("thirval"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		}else if (cmd.equals("search_multikpu")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiKPU(request.getParameter("value"), request.getParameter("seval"),
					request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		}else if (cmd.equals("search_multikpu_date")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToMultiKPUDate(request.getParameter("value"), request.getParameter("seval"),
					request.getParameter("thirval"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		}else if (cmd.equals("search_all")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToAll();
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", "ALL");
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		}else if (cmd.equals("search_all_date")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToAllDate(request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", "ALL");
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		} else if (cmd.equals("search_code")) { // 상품코드 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankListToCode(request.getParameter("code"),request.getParameter("keyword"));
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj2", rankVO);
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/sub.jsp");
			view.forward(request, response);
		} else if(cmd.equals("crawling_result")) {
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			System.out.println("걸림");
			rankVO = dao.getRankResultToKP(request.getParameter("value"), request.getParameter("seval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		} else if(cmd.equals("crawling_result_second")) {
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankResultToKU(request.getParameter("value"), request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		} else if(cmd.equals("crawling_result_add")) {
			RankDAO dao = new RankDAO();
			ArrayList<AddRankVO> rankVO = new ArrayList<AddRankVO>();
			rankVO = dao.getRankResultToAdd(request.getParameter("seval"), request.getParameter("value"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main_add.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		} else if(cmd.equals("crawling_result_product")) {
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			rankVO = dao.getRankResultToKU(request.getParameter("value"), request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			view.forward(request, response);
		} else if(cmd.equals("delete_rank_all")) {
			RankDAO dao = new RankDAO();
			if(dao.deleteRankListToAll()) {
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
				view.forward(request, response);
			}
		} else if(cmd.equals("delete_add_all")) {
			RankDAO dao = new RankDAO();
			if(dao.deleteAddListToAll()) {
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
				view.forward(request, response);
			}
		} else if(cmd.equals("delete_rank_selected")) {
			RankDAO dao = new RankDAO();
			ArrayList<RankVO> rankVO = new ArrayList<RankVO>();
			if(dao.deleteRankListToSelected(request.getParameter("idx"))) {
				if(request.getParameter("seval")!=null) {
					System.out.println("삭제호출됨");
					rankVO = dao.getRankListToMultiKP(request.getParameter("value"), request.getParameter("seval"));
				}else {
					rankVO = dao.getRankListToMultiKU(request.getParameter("value"), request.getParameter("thirval"));
				}
			}
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			request.setAttribute("thirval", request.getParameter("thirval"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/main.jsp");
				view.forward(request, response);

		}else if(cmd.equals("delete_add_selected")) {
			RankDAO dao = new RankDAO();
			ArrayList<AddRankVO> rankVO = new ArrayList<AddRankVO>();
			if(dao.deleteAddListToSelected(request.getParameter("idx"))) {
				if(request.getParameter("seval")!=null) {
					System.out.println("광고 삭제호출됨");
					rankVO = dao.getRankListToAdd(request.getParameter("seval"), request.getParameter("value"));
				}
			}
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/main_add.jsp");
				view.forward(request, response);

		} else if (cmd.equals("search_add")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<AddRankVO> rankVO = new ArrayList<AddRankVO>();
			rankVO = dao.getRankListToAdd(request.getParameter("seval"), request.getParameter("value"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main_add.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		}else if (cmd.equals("search_add_all")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<AddRankVO> rankVO = new ArrayList<AddRankVO>();
			rankVO = dao.getRankListToAddAll();
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main_add.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		}else if (cmd.equals("search_add_all_date")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<AddRankVO> rankVO = new ArrayList<AddRankVO>();
			rankVO = dao.getRankListToAddDateAll(request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main_add.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		}else if (cmd.equals("search_add_date")) { // 키워드, 상점명, url 기반 검색
			RankDAO dao = new RankDAO();
			ArrayList<AddRankVO> rankVO = new ArrayList<AddRankVO>();
			rankVO = dao.getRankListToAddDate(request.getParameter("seval"), request.getParameter("value"),request.getParameter("date"),request.getParameter("date2"));
			request.setAttribute("rank", rankVO);
			session.setAttribute("obj", rankVO);
			request.setAttribute("count", rankVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/main_add.jsp");
			request.setAttribute("value", request.getParameter("value"));
			request.setAttribute("seval", request.getParameter("seval"));
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
		String cmd = "";
		cmd = request.getParameter("key");
		if (cmd.equals("search")) {
			RequestDispatcher view = request.getRequestDispatcher("main.jsp");
			view.forward(request, response);
		}
	}

}
