package com.jyoti.loginmodule.models;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

public class Cart {
	  public final List<Item> lineItems;

	 // @SerializedName("buyer")
	  private final String buyerName;

	  private final String creditCard;

	  public Cart(List<Item> lineItems, String buyerName, String creditCard) {
	    this.lineItems = lineItems;
	    this.buyerName = buyerName;
	    this.creditCard = creditCard;
	  }

	  public List<Item> getLineItems() {
	    return lineItems;
	  }

	  public String getBuyerName() {
	    return buyerName;
	  }

	  public String getCreditCard() {
	    return creditCard;
	  }

	  @Override
	  public String toString() {
	    StringBuilder itemsText = new StringBuilder();
	    boolean first = true;
	    if (lineItems != null) {
	      try {
	        Class<?> fieldType = Cart.class.getField("lineItems").getType();
	        System.out.println("LineItems CLASS: " + getSimpleTypeName(fieldType));
	      } catch (SecurityException e) {
	      } catch (NoSuchFieldException e) {
	      }
	      for (Item item : lineItems) {
	        if (first) {
	          first = false;
	        } else {
	          itemsText.append("; ");
	        }
	        itemsText.append(item);
	      }
	    }
	    return "[BUYER: " + buyerName + "; CC: " + creditCard + "; "
	    + "LINE_ITEMS: " + itemsText.toString() + "]";
	  }

	  @SuppressWarnings("unchecked")
	  public static String getSimpleTypeName(Type type) {
	    if (type == null) {
	      return "null";
	    }
	    if (type instanceof Class) {
	      return ((Class)type).getSimpleName();
	    } else if (type instanceof ParameterizedType) {
	      ParameterizedType pType = (ParameterizedType) type;
	      StringBuilder sb = new StringBuilder(getSimpleTypeName(pType.getRawType()));
	      sb.append('<');
	      boolean first = true;
	      for (Type argumentType : pType.getActualTypeArguments()) {
	        if (first) {
	          first = false;
	        } else {
	          sb.append(',');
	        }
	        sb.append(getSimpleTypeName(argumentType));
	      }
	      sb.append('>');
	      return sb.toString();
	    } else if (type instanceof WildcardType) {
	      return "?";
	    }
	    return type.toString();
	  }

	}
