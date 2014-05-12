package com.example.kemuseum.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.kemuseum.model.Capaian;
import com.example.kemuseum.model.Museum;
import com.example.kemuseum.model.Wishlist;
import com.example.kemuseum.utils.WishlistManager;

public class ControllerWishlist {
	private WishlistManager wishlistManager;	
	
	public ControllerWishlist() {
		wishlistManager = wishlistManager.getWishlistManager();		
	}
	
	public List<Wishlist> getWishlist(){
		return wishlistManager.getDaftarKeinginan();
	}
	
}
