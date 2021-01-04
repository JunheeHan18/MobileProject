package rank.domain;

public class RankVO {
	private String idx; // 인덱스
	private String keyword; // 키워드
	private String store; // 상점명
	private String title; // 상품명
	private String review; // 리뷰수
	private String purchase; // 구매건수
	private String zzim; // 찜수
	private String regist_date; // 등록일
	private String search_date; // 검색기준일
	private String rank; // 순위
	private String product_code; // 상품코드
	private String page_num; // 페이지 번호
	private String page_idx; // 페이지내 상품순위
	private String cheap_bool; // 쇼핑몰최저가여부
	private String product_link; // 제품링크
	private String page_link; // 쇼핑몰페이지링크
	private String add_rank; // 광고순위
	private String image_link;

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getIdx() {
		return this.idx;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getStore() {
		return this.store;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReview() {
		return this.review;
	}

	public void setZzim(String zzim) {
		this.zzim = zzim;
	}

	public String getZzim() {
		return this.zzim;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getPurchase() {
		return this.purchase;
	}

	public void setRegist_date(String regist_date) {
		this.regist_date = regist_date;
	}

	public String getRegist_date() {
		return this.regist_date;
	}

	public void setSearch_date(String search_date) {
		this.search_date = search_date;
	}

	public String getSearch_date() {
		return this.search_date;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRank() {
		return this.rank;
	}
	/*
	 * 상품코드 product_code / 페이지 page_num / 인덱스 page_idx / 쇼핑몰최저가여부 cheap_bool /제품링크
	 * product_link/ 페이지링크 page_link / 광고순위 add_rank
	 */

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_code() {
		return this.product_code;
	}

	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}

	public String getPage_num() {
		return this.page_num;
	}

	public void setPage_idx(String page_idx) {
		this.page_idx = page_idx;
	}

	public String getPage_idx() {
		return this.page_idx;
	}

	public void setCheap_bool(String cheap_bool) {
		this.cheap_bool = cheap_bool;
	}

	public String getCheap_bool() {
		return this.cheap_bool;
	}

	/*
	 * 상품코드 product_code / 페이지 page_num / 인덱스 page_idx / 쇼핑몰최저가여부 cheap_bool /제품링크
	 * product_link/ 페이지링크 page_link / 광고순위 add_rank
	 */

	public void setProcduct_link(String product_link) {
		this.product_link = product_link;
	}

	public String getProduct_link() {
		return this.product_link;
	}

	public void setPage_link(String page_link) {
		this.page_link = page_link;
	}

	public String getPage_link() {
		return this.page_link;
	}

	public void setAdd_rank(String add_rank) {
		this.add_rank = add_rank;
	}

	public String getAdd_rank() {
		return this.add_rank;
	}
	
	public void setImage_link(String image_link) {
		this.image_link=image_link;
	}
	
	public String getImage_link() {
		return this.image_link;
	}

}
