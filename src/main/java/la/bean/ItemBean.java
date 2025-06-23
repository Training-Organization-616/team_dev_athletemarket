package la.bean;

public class ItemBean {

	// フィールド
	private int id;

	//　修正前
	//	private int customerId;

	//修正後
	private int sellerId;
	private int purchaseId;

	private int categoryId;
	private String name;
	private int price;
	private String sellDay;
	private String purchaseDay;
	private String memo;

	// コンストラクタ
	public ItemBean() {
	}

	//修正後
	public ItemBean(int id, int sellerId, int purchaseId, int categoryId, String name, int price, String sellDay,
			String purchaseDay,
			String memo) {

		this.id = id;
		this.sellerId = sellerId;
		this.purchaseId = purchaseId;
		this.categoryId = categoryId;
		this.name = name;
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

	//
	//	public int getCustomerId() {
	//		return customerId;
	//	}
	//
	//	public void setCustomerId(int customerId) {
	//		this.customerId = customerId;
	//	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
