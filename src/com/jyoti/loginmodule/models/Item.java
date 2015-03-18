package com.jyoti.loginmodule.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable{
	private static final long serialVersionUID = 767635507730919937L;
	  @JsonProperty("itemId")
	  private  long itemId;
	  @JsonProperty("itemName")
	  private  String itemName;
	  @JsonProperty("itemDescription")
	  private  String itemDescription;
	  @JsonProperty("image")
	  private  String image;
	  @JsonProperty("quantityAvailable")
	  private  int quantityAvailable;
	  @JsonProperty("price")
	  private final long price;

	//private final String category;
	  // private final String currencyCode;
	  public Item(long id ,String name,String description,String image , int quantity, long price) {
			//this.category = "";
			this.itemId = id;
		    this.itemName = name;
		    this.image = image;
		    this.itemDescription = description;
		    this.quantityAvailable = quantity;
		    this.price = price;
		   }
		  
	  public long getId() {
		return itemId;
	}

	public String getDescription() {
		return itemDescription;
	}

	public String getImage() {
		return image;
	}

	public long getPrice() {
		return price;
	}


	
	 
	  public String getName() {
	    return itemName;
	  }

	  public int getQuantityAvailable() {
	    return quantityAvailable;
	  }

	 
	  public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	  @Override
	  public String toString() {
	    return String.format("(id: %d, name: %s, desc: %s, quantity:%d ,price:%d, image:%s)",
	        itemId,itemName,itemDescription,quantityAvailable,price,image );
	  }
	}
	
