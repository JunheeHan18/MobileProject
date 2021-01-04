package rank.domain;

public class AddRankVO {
	private String idx; // 인덱스
	private String keyword; // 키워드
	private String title; // 상품명
	private String page_num; // 페이지 번호
	private String page_link; // 쇼핑몰페이지링크
	private String add_rank; // 광고순위
	private String search_date; // 조회일
	
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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}

	public String getPage_num() {
		return this.page_num;
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
	
	public void setSearch_date(String search_date) {
		this.search_date = search_date;
	}
	
	public String getSearch_date() {
		return this.search_date;
	}

}
