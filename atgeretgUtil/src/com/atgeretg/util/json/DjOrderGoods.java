package com.atgeretg.util.json;

import java.sql.Timestamp;

import com.atgeretg.util.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DjOrderGoods entity
 */

public class DjOrderGoods implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ogId;
	@JsonProperty("og_date")
	@JsonFormat(pattern = DateUtil.Y_M_D_HM)
	private Timestamp ogDate;
	@JsonProperty("og_od_code")
	private String ogOdCode;
	private String ogGdCode;
	@JsonProperty("og_gd_name")
	private String ogGdName;
	private Double ogGdCost;
	private Double ogGdInCost;
	private Double ogGdBai;
	private Double ogGdHong;
	private Double ogGdHuang;
	private Integer ogGdNum;
	private Double ogGdMaxCost;
	private Integer ogUId;
	private Integer ogState;

	// Constructors

	/** default constructor */
	public DjOrderGoods() {
	}

	/** full constructor */
	public DjOrderGoods(Timestamp ogDate, String ogOdCode, String ogGdCode, String ogGdName, Double ogGdCost,
			Double ogGdInCost, Double ogGdBai, Double ogGdHong, Double ogGdHuang, Integer ogGdNum, Double ogGdMaxCost,
			Integer ogUId, Integer ogState) {
		this.ogDate = ogDate;
		this.ogOdCode = ogOdCode;
		this.ogGdCode = ogGdCode;
		this.ogGdName = ogGdName;
		this.ogGdCost = ogGdCost;
		this.ogGdInCost = ogGdInCost;
		this.ogGdBai = ogGdBai;
		this.ogGdHong = ogGdHong;
		this.ogGdHuang = ogGdHuang;
		this.ogGdNum = ogGdNum;
		this.ogGdMaxCost = ogGdMaxCost;
		this.ogUId = ogUId;
		this.ogState = ogState;
	}

	// Property accessors

	public Integer getOgId() {
		return this.ogId;
	}

	public void setOgId(Integer ogId) {
		this.ogId = ogId;
	}

	public Timestamp getOgDate() {
		return this.ogDate;
	}

	public void setOgDate(Timestamp ogDate) {
		this.ogDate = ogDate;
	}

	public String getOgOdCode() {
		return this.ogOdCode;
	}

	public void setOgOdCode(String ogOdCode) {
		this.ogOdCode = ogOdCode;
	}

	public String getOgGdCode() {
		return this.ogGdCode;
	}

	public void setOgGdCode(String ogGdCode) {
		this.ogGdCode = ogGdCode;
	}

	public String getOgGdName() {
		return this.ogGdName;
	}

	public void setOgGdName(String ogGdName) {
		this.ogGdName = ogGdName;
	}

	public Double getOgGdCost() {
		return this.ogGdCost;
	}

	public void setOgGdCost(Double ogGdCost) {
		this.ogGdCost = ogGdCost;
	}

	public Double getOgGdInCost() {
		return this.ogGdInCost;
	}

	public void setOgGdInCost(Double ogGdInCost) {
		this.ogGdInCost = ogGdInCost;
	}

	public Double getOgGdBai() {
		return this.ogGdBai;
	}

	public void setOgGdBai(Double ogGdBai) {
		this.ogGdBai = ogGdBai;
	}

	public Double getOgGdHong() {
		return this.ogGdHong;
	}

	public void setOgGdHong(Double ogGdHong) {
		this.ogGdHong = ogGdHong;
	}

	public Double getOgGdHuang() {
		return this.ogGdHuang;
	}

	public void setOgGdHuang(Double ogGdHuang) {
		this.ogGdHuang = ogGdHuang;
	}

	public Integer getOgGdNum() {
		return this.ogGdNum;
	}

	public void setOgGdNum(Integer ogGdNum) {
		this.ogGdNum = ogGdNum;
	}

	public Double getOgGdMaxCost() {
		return this.ogGdMaxCost;
	}

	public void setOgGdMaxCost(Double ogGdMaxCost) {
		this.ogGdMaxCost = ogGdMaxCost;
	}

	public Integer getOgUId() {
		return this.ogUId;
	}

	public void setOgUId(Integer ogUId) {
		this.ogUId = ogUId;
	}

	public Integer getOgState() {
		return this.ogState;
	}

	public void setOgState(Integer ogState) {
		this.ogState = ogState;
	}

}