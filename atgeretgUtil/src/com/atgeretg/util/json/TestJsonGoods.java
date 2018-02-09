package com.atgeretg.util.json;

import java.sql.Timestamp;

import com.atgeretg.util.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestJsonGoods {
	// Fields
	@JsonProperty("gd_id")
	private Integer gdId;
	@JsonProperty("gd_code")
	private String gdCode;
	@JsonProperty("gd_date")
	@JsonFormat(pattern = DateUtil.Y_M_D_HMS)
	private Timestamp gdDate;
	private String gdName;
	private Double gdCost;
	private Double gdInCost;
	private Double gdBai;
	private Double gdHong;
	private Double gdHuang;
	private Integer gdPayNum;
	private Integer gdBlNum;
	@JsonFormat(pattern = DateUtil.Y_M_D_HMS)
	private Timestamp gdInDate;
	@JsonFormat(pattern = DateUtil.Y_M_D_HMS)
	private Timestamp gdOutDate;
	private Integer gdState;
	private String gdBsCode;
	private Integer gdUpdateCode;
	private String gdNumber;
	private Integer gdSupplierId;
	private String gdStandard;
	private Integer gdTypeId;
	private Integer gdTypeSubId;
	private Integer pandianNum;
	

	// Constructors

	/** default constructor */
	public TestJsonGoods() {
	}

	/** full constructor */
	public TestJsonGoods(String gdCode, Timestamp gdDate, String gdName, Double gdCost, Double gdInCost, Double gdBai,
			Double gdHong, Double gdHuang, Integer gdPayNum, Integer gdBlNum, Timestamp gdInDate, Timestamp gdOutDate,
			Integer gdState, String gdBsCode, Integer gdUpdateCode, String gdNumber) {
		this.gdCode = gdCode;
		this.gdDate = gdDate;
		this.gdName = gdName;
		this.gdCost = gdCost;
		this.gdInCost = gdInCost;
		this.gdBai = gdBai;
		this.gdHong = gdHong;
		this.gdHuang = gdHuang;
		this.gdPayNum = gdPayNum;
		this.gdBlNum = gdBlNum;
		this.gdInDate = gdInDate;
		this.gdOutDate = gdOutDate;
		this.gdState = gdState;
		this.gdBsCode = gdBsCode;
		this.gdUpdateCode = gdUpdateCode;
		this.gdNumber = gdNumber;
	}

	// Property accessors
	
	

	public Integer getGdId() {
		return this.gdId;
	}

	public Integer getPandianNum() {
		return pandianNum;
	}

	public void setPandianNum(Integer pandianNum) {
		this.pandianNum = pandianNum;
	}

	public Integer getGdTypeId() {
		return gdTypeId;
	}

	public void setGdTypeId(Integer gdTypeId) {
		this.gdTypeId = gdTypeId;
	}

	public Integer getGdTypeSubId() {
		return gdTypeSubId;
	}

	public void setGdTypeSubId(Integer gdTypeSubId) {
		this.gdTypeSubId = gdTypeSubId;
	}

	public String getGdStandard() {
		return gdStandard;
	}

	public void setGdStandard(String gdStandard) {
		this.gdStandard = gdStandard;
	}

	public Integer getGdSupplierId() {
		return gdSupplierId;
	}

	public void setGdSupplierId(Integer gdSupplierId) {
		this.gdSupplierId = gdSupplierId;
	}


	public void setGdId(Integer gdId) {
		this.gdId = gdId;
	}

	public String getGdCode() {
		return this.gdCode;
	}

	public void setGdCode(String gdCode) {
		this.gdCode = gdCode;
	}

	public Timestamp getGdDate() {
		return this.gdDate;
	}

	public void setGdDate(Timestamp gdDate) {
		this.gdDate = gdDate;
	}

	public String getGdName() {
		return this.gdName;
	}

	public void setGdName(String gdName) {
		this.gdName = gdName;
	}

	public Double getGdCost() {
		return this.gdCost;
	}

	public void setGdCost(Double gdCost) {
		this.gdCost = gdCost;
	}

	public Double getGdInCost() {
		return this.gdInCost;
	}

	public void setGdInCost(Double gdInCost) {
		this.gdInCost = gdInCost;
	}

	public Double getGdBai() {
		return this.gdBai;
	}

	public void setGdBai(Double gdBai) {
		this.gdBai = gdBai;
	}

	public Double getGdHong() {
		return this.gdHong;
	}

	public void setGdHong(Double gdHong) {
		this.gdHong = gdHong;
	}

	public Double getGdHuang() {
		return this.gdHuang;
	}

	public void setGdHuang(Double gdHuang) {
		this.gdHuang = gdHuang;
	}

	public Integer getGdPayNum() {
		return this.gdPayNum;
	}

	public void setGdPayNum(Integer gdPayNum) {
		this.gdPayNum = gdPayNum;
	}

	public Integer getGdBlNum() {
		return this.gdBlNum;
	}

	public void setGdBlNum(Integer gdBlNum) {
		this.gdBlNum = gdBlNum;
	}

	public Timestamp getGdInDate() {
		return this.gdInDate;
	}

	public void setGdInDate(Timestamp gdInDate) {
		this.gdInDate = gdInDate;
	}

	public Timestamp getGdOutDate() {
		return this.gdOutDate;
	}

	public void setGdOutDate(Timestamp gdOutDate) {
		this.gdOutDate = gdOutDate;
	}

	public Integer getGdState() {
		return this.gdState;
	}

	public void setGdState(Integer gdState) {
		this.gdState = gdState;
	}

	public String getGdBsCode() {
		return this.gdBsCode;
	}

	public void setGdBsCode(String gdBsCode) {
		this.gdBsCode = gdBsCode;
	}

	public Integer getGdUpdateCode() {
		return this.gdUpdateCode;
	}

	public void setGdUpdateCode(Integer gdUpdateCode) {
		this.gdUpdateCode = gdUpdateCode;
	}

	public String getGdNumber() {
		return this.gdNumber;
	}

	public void setGdNumber(String gdNumber) {
		this.gdNumber = gdNumber;
	}


}
