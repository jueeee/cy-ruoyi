package com.cy.ruoyi.common.core.util.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
@Data
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	private long total;

	/**
	 *  当前页
	 */
	private long pageNum;
	/**
	 * 列表数据
	 */
	private List<?> rows;

	/**
	 *  共页码
	 */
	private long totalPage;

	/**
	 * 每页记录数
	 */
	private long pageSize;
	
	/**
	 *  结果封装，不分页
	 * @param rows        列表数据
	 * @param total  	总记录数
	 */
	public PageUtils(List<?> rows, int total) {
		this.rows = rows;
		this.total = total;
		this.pageNum = 1;
	}

	/**
	 * 分页
	 */
	public PageUtils(IPage<?> page) {
		this.rows = page.getRecords();
		this.total = page.getTotal();
		this.pageNum = page.getCurrent();
	}

}
