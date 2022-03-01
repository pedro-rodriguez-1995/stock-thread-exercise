package com.sinensia.classes;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class Stock {
	
	private LocalDate date;
	private Instant hour;
	private BigDecimal closingprice;
	private String ticker;
	
	public Stock() {
		super();
	}

	public Stock(LocalDate date, Instant hour, BigDecimal closingprice,String ticker) {

		this.date = date;
		this.hour = hour;
		this.closingprice = closingprice;
		this.ticker = ticker;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Instant getHour() {
		return hour;
	}

	public void setHour(Instant hour) {
		this.hour = hour;
	}

	public BigDecimal getClosingprice() {
		return closingprice;
	}

	public void setClosingprice(BigDecimal closingprice) {
		this.closingprice = closingprice;
	}
	

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Override
	public String toString() {
		return "Stock "+ticker+" Date: " + date + ", Hour: " + hour.atZone(ZoneOffset.systemDefault()).getHour() + ":"
				+ hour.atZone(ZoneOffset.systemDefault()).getMinute() + ", Closing Price: " + closingprice;
	}

}
