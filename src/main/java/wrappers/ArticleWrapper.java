package wrappers;

import java.math.BigDecimal;

import entities.core.Article;

public class ArticleWrapper extends ProductWrapper {

	private int stock;

	private BigDecimal wholesalePrice;

	public ArticleWrapper() {

	}

	public ArticleWrapper(Article article) {

		super(article.getId(), article.getReference(), article.getDescription(), article.getRetailPrice());
		this.stock = article.getStock();
		this.wholesalePrice = article.getWholesalePrice();
	}

	public ArticleWrapper(long id, String reference, String description, BigDecimal retailPrice, int stock,
			BigDecimal wholesalePrice) {
		super(id, reference, description, retailPrice);
		this.stock = stock;
		this.wholesalePrice = wholesalePrice;

	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

}
