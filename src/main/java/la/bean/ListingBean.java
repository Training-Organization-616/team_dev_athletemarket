package la.bean;

public class ListingBean {

	// フィールド
	private int id;
	private int sellerId;
	private String sellerName;
	private int purchaseId;
	private String categoryName;
	private String imageName;
	private String itemName;
	private int price;
	private String sellDay;
	private String purchaseDay;
	private String memo;

	// コンストラクタ
	public ListingBean() {

	}

	public ListingBean(int id, int sellerId, String sellerName, String categoryName, String imageName, String itemName,
			int price, String sellDay,
			String purchaseDay, String memo) {

		this.id = id;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
		this.sellDay = sellDay;
		this.purchaseDay = purchaseDay;
		this.memo = memo;

	}

	// ゲッター・セッター
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String catgoryName) {
		this.categoryName = catgoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSellDay() {
		return sellDay;
	}

	public void setSellDay(String sellDay) {
		this.sellDay = sellDay;
	}

	public String getPurchaseDay() {
		return purchaseDay;
	}

	public void setPurchaseDay(String purchaseDay) {
		this.purchaseDay = purchaseDay;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}