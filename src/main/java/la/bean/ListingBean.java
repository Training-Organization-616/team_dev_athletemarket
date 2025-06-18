package la.bean;

public class ListingBean {

	// フィールド
	private int id;
	private int customerId;
	private String customerName;
	private String categoryName;
	private String itemName;
	private int price;
	private String purchaseDay;
	private String memo;

	// コンストラクタ
	public ListingBean() {

	}

	public ListingBean(int id, int customerId, String customerName, String categoryName, String itemName, int price,
			String purchaseDay, String memo) {

		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
