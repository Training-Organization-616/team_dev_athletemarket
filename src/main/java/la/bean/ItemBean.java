package la.bean;

public class ItemBean {

	// フィールド
	private int id;
	private int customerId;
	private int categoryId;
	private String name;
	private int price;
	private String sellDay;
	private String purchaseDay;
	private String memo;

	// コンストラクタ
	public ItemBean() {
	}

	public ItemBean(int id, int customerId, int categoryId, String name, int price, String sellDay, String purchaseDay,
			String memo) {

		this.id = id;
		this.customerId = customerId;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
