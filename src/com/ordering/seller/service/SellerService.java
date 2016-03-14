package com.ordering.seller.service;

import java.util.List;

import com.ordering.seller.po.Seller;

public interface SellerService {
	public int registSeller(Seller seller);
	public void active(String code);
	public Seller findSellerByEmailCode(String code);
	public Seller findSellerByEmail(String email);
	public int login(Seller seller);
	public Seller findSellerBySid(String sid);
	public void updateAccpetState(String sid, String b);
	public List<Seller> queryAllSeller();
}
