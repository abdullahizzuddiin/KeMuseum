package com.example.kemuseum.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.example.kemuseum.model.Capaian;
import com.example.kemuseum.model.Wishlist;
import com.example.kemuseum.utils.JSONParser;
import com.example.kemuseum.utils.WishlistManager;

public class ControllerWishlist {
	private WishlistManager wishlistManager;	
	
	public ControllerWishlist() {
		wishlistManager = wishlistManager.getWishlistManager();		
	}
	
	public List<Wishlist> getWishlist(){
		return wishlistManager.getDaftarKeinginan();
	}
	
	public void tambahWishlist (Wishlist w) {
		wishlistManager.tambahWishlist(w);
	}
	
	public String ambilJSON (Wishlist w) {
		return JSONParser.toJSON(w);
	}
}
