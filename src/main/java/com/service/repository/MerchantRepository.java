package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.service.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer>{
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM merchant u WHERE u.merchant_id = ?1", nativeQuery = true)
	public void deleteMerchant (Integer merchantId);

}
