package la.bean;

public class ItemBean {

	// フィールド
	private int id;
	private int sellerId;
	private int purchaserId;
	private int categoryId;
	private String name;
	private int price;
	private String sellDay;
	private String purchaseDay;
	private String memo;

	// コンストラクタ
	public ItemBean() {
	}

	public ItemBean(int id, int sellerId, int purchaserId, int categoryId, String name, int price, String sellDay,
			String purchaseDay,
			String memo) {

		this.id = id;
		this.sellerId = sellerId;
		this.purchaserId = purchaserId;
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

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getPurchaseId() {
		return purchaserId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaserId = purchaseId;
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
