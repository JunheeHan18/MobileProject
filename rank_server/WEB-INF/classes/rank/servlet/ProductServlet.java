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
import org.json.JSONException;
import org.json.JSONObject;

import rank.domain.AddRankVO;
import rank.domain.ProductVO;
import rnak.persistence.ProductDAO;
import rnak.persistence.RankDAO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject productObj = new JSONObject();
	JSONArray dataArry = new JSONArray();
	JSONObject dataObj = new JSONObject();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
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

		if (cmd.equals("product_db")) {
			ProductDAO dao = new ProductDAO();
			ArrayList<ProductVO> productVO = new ArrayList<ProductVO>();
			productVO = dao.getProductListToAll();
			session.setAttribute("product", productVO);
			request.setAttribute("product", productVO);
			request.setAttribute("count", productVO.size());
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/product_db.jsp");
			view.forward(request, response);
		} else if (cmd.equals("product_delete")) {
			ProductDAO dao = new ProductDAO();
			if (dao.deleteProduct(request.getParameter("idx"))) {
				ArrayList<ProductVO> productVO = new ArrayList<ProductVO>();
				productVO = dao.getProductListToAll();

				request.setAttribute("product", productVO);
				request.setAttribute("count", productVO.size());
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/product_db.jsp");
				view.forward(request, response);
			}
		}else if (cmd.equals("product_db_doexcel")) {
			request.setAttribute("product",session.getAttribute("product"));
			RequestDispatcher view = request.getRequestDispatcher("/rankpage/doExcel_product.jsp");
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
		String cmd = "";
		HttpSession session = request.getSession();
		cmd = request.getParameter("key");

		if (cmd.equals("update_product")) { // 상품 정보수정
			ProductVO pvo = new ProductVO();
			pvo.setProduct_code(request.getParameter("code"));
			pvo.setProduct_link(request.getParameter("link"));
			pvo.setProduct_code_seller(request.getParameter("code_seller"));
			pvo.setProduct_name(request.getParameter("product_name"));
			pvo.setKeyword_first(request.getParameter("key_1"));
			pvo.setKeyword_second(request.getParameter("key_2"));
			pvo.setKeyword_third(request.getParameter("key_3"));
			pvo.setKeyword_four(request.getParameter("key_4"));
			pvo.setPriority(request.getParameter("priority"));
			System.out.println(request.getParameter("code"));
			ProductDAO dao = new ProductDAO();
			if (dao.updateProduct(pvo)) {
				ArrayList<ProductVO> productVO = new ArrayList<ProductVO>();
				productVO = dao.getProductListToAll();
				request.setAttribute("product", productVO);
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/restat.jsp");
				view.forward(request, response);
			}
		} else if (cmd.equals("delete_product")) { // 상품정보 삭제
			ProductDAO dao = new ProductDAO();
			if (dao.deleteProduct(request.getParameter("idx"))) {
				ArrayList<ProductVO> productVO = new ArrayList<ProductVO>();
				productVO = dao.getProductListToAll();
				request.setAttribute("product", productVO);
				request.setAttribute("count", productVO.size());
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/product_db.jsp");
				view.forward(request, response);
			}
		} else if (cmd.equals("insert_product")) { // 상품 추가
			ProductVO pvo = new ProductVO();
			pvo.setProduct_code(request.getParameter("code"));
			pvo.setProduct_link(request.getParameter("link"));
			pvo.setProduct_code_seller(request.getParameter("code_seller"));
			pvo.setProduct_name(request.getParameter("product_name"));
			pvo.setKeyword_first(request.getParameter("key_1"));
			pvo.setKeyword_second(request.getParameter("key_2"));
			pvo.setKeyword_third(request.getParameter("key_3"));
			pvo.setKeyword_four(request.getParameter("key_4"));
			pvo.setPriority(request.getParameter("priority"));
			ProductDAO dao = new ProductDAO();
			if (dao.addProduct(pvo)) {
				ArrayList<ProductVO> productVO = new ArrayList<ProductVO>();
				productVO = dao.getProductListToAll();
				request.setAttribute("product", productVO);
				System.out.println("여기 실행됨");
				RequestDispatcher view = request.getRequestDispatcher("/rankpage/restat.jsp");
				view.forward(request, response);
			}
		}
	}

}
