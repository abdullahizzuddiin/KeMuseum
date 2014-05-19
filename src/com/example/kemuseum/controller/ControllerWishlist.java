package com.example.kemuseum.controller;

import java.util.List;

import org.json.JSONObject;

import com.example.kemuseum.model.Capaian;
import com.example.kemuseum.model.Wishlist;
import com.example.kemuseum.utils.JSONParser;
import com.example.kemuseum.utils.WishlistManager;
import com.rogerlemmonapps.captcha.TextCaptcha;
import com.rogerlemmonapps.captcha.TextCaptcha.TextOptions;

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
	
	public TextCaptcha getCaptcha(){
		return new TextCaptcha(500, 200, 5, TextOptions.UPPERCASE_ONLY); 
	}
	
	public String ambilJSON (Wishlist w) {
		return JSONParser.toJSON(w);
	}
}
