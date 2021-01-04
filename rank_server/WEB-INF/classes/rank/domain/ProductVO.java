package rank.domain;

public class ProductVO {
   private String idx; //구분
   private String priority; //우선순위
   private String product_code; // 상품코드
   private String product_link; // 제품링크
   private String product_code_seller; // 상품 판매자 코드
   private String product_name; // 상품명
   private String keyword_first; //키워드1
   private String keyword_first_rank; //키워드1 순위
   private String keyword_second; //키워드2
   private String keyword_second_rank;
   private String keyword_third; //키워드3
   private String keyword_third_rank;
   private String keyword_four; //키워드4
   private String keyword_four_rank;
   
   
   
   public void setIdx(String idx) {
      this.idx = idx;
   }

   public String getIdx() {
      return this.idx;
   }
   
   public void setProduct_code(String product_code) {
      this.product_code=product_code;
   }
   
   public String getProduct_code() {
      return this.product_code;
   }
   
   public void setProduct_link(String product_link) {
      this.product_link=product_link;
   }
   
   public String getProduct_link() {
      return this.product_link;
   }
   
   public void setProduct_code_seller(String product_code_seller) {
      this.product_code_seller=product_code_seller;
   }
   
   public String getProduct_code_seller() {
      return this.product_code_seller;
   }
   
   public void setProduct_name(String product_name) {
      this.product_name = product_name;
   }
   
   public String getProduct_name() {
      return this.product_name;
   }
   
   public void setKeyword_first(String keyword_first) {
      this.keyword_first=keyword_first;
   }
   
   public String getKeyword_first() {
      return this.keyword_first;
   }
   
   public void setKeyword_second(String keyword_second) {
      this.keyword_second=keyword_second;
   }
   
   public String getKeyword_second() {
      return this.keyword_second;
   }
   
   public void setKeyword_third(String keyword_third) {
      this.keyword_third=keyword_third;
   }
   
   public String getKeyword_third() {
      return this.keyword_third;
   }
   
   public void setKeyword_four(String keyword_four) {
      this.keyword_four=keyword_four;
   }
   
   public String getKeyword_four() {
      return this.keyword_four;
   }
   
   public void setKeyword_first_rank(String keyword_first_rank) {
      this.keyword_first_rank=keyword_first_rank;
   }
   
   public String getKeyword_first_rank() {
      return this.keyword_first_rank;
   }
   
   public void setKeyword_second_rank(String keyword_second_rank) {
      this.keyword_second_rank=keyword_second_rank;
   }
   
   public String getKeyword_second_rank() {
      return this.keyword_second_rank;
   }
   
   public void setKeyword_third_rank(String keyword_third_rank) {
      this.keyword_third_rank=keyword_third_rank;
   }
   
   public String getKeyword_third_rank() {
      return this.keyword_third_rank;
   }
   
   public void setKeyword_four_rank(String keyword_four_rank) {
      this.keyword_four_rank=keyword_four_rank;
   }
   
   public String getKeyword_four_rank() {
      return this.keyword_four_rank;
   }
   
   public void setPriority(String priority) {
      this.priority=priority;
   }
   
   public String getPriority() {
      return this.priority;
   }

}