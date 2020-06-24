package com.apeman1024.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.apeman1024.entity.Ping;

public interface IOpinion {
	/**
	 * 获取意见反馈列表
	 * @param ro
	 * @return
	 */
	public List<Ping> select(RowBounds ro);
	/**
	 * 添加一条评论
	 * @param ping
	 * @return
	 */
	public Integer addPing(Ping ping);
	/**
	 * 统计评论列表中评论数
	 * @return
	 */
	public Integer count();

}
