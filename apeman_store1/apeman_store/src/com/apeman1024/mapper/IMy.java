package com.apeman1024.mapper;

import com.apeman1024.entity.Card;
import com.apeman1024.entity.Indent;

public interface IMy {

	/**
	 * 判断用户注册的猿人卡号是否已经存在
	 * @param card
	 * @return
	 */
	public Card pd(Card card);
	/**
	 * 查询猿人卡中的余额
	 * @param card
	 * @return
	 */
	public Double getBalance(Card card);

	/**
	 * 添加一条订单信息
	 * @param ind
	 * @return
	 */
	public Integer addIndent(Indent ind);

	public Integer updateCom(Indent ind);
	
	/**
	 * 用户付款后，减少猿人卡中的钱
	 * @param card
	 * @return
	 */
	public Integer expen(Card card);

	/**
	 * 判断当前订单列表中是否已经存在相应的用户
	 * @param guid
	 * @return
	 */
	public Boolean pd1(String guid);

}
