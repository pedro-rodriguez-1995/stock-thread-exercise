package com.sinensia.logic;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

import com.sinensia.classes.Stock;
import com.sinensia.utils.CsvDownload;
import com.sinensia.utils.CsvRead;

public class StockChecker implements Callable<Stock> {
	private String link;
	private String filename;

	public StockChecker(String link, String filename) {

		this.link = link;
		this.filename = filename;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public Stock call() throws Exception {

		Stock stock = new Stock();
		CsvDownload.download(link, filename);
		System.out.println("Thread: " + Thread.currentThread().getName() + " has downloaded: " + filename);
		List<String[]> stra = CsvRead.read(filename);
		stra.remove(0);
		for (String[] strings : stra) {

			stock.setDate(LocalDate.parse(strings[0]));
			stock.setClosingprice(new BigDecimal(strings[4]));
			stock.setHour(Instant.now());
			stock.setTicker(filename.split("\\.")[0]);
		}

		System.out.println("Thread: " + Thread.currentThread().getName() + " has created a stock");

		return stock;
	}

}
