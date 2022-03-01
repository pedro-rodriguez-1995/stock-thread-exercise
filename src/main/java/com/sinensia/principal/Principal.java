package com.sinensia.principal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sinensia.classes.Stock;
import com.sinensia.logic.StockChecker;

public class Principal {
	private static final int numThreads = 7;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);

		try (InputStreamReader input = new InputStreamReader(
				Principal.class.getClass().getResourceAsStream("/config.properties"))) {

			Properties properties = new Properties();
			properties.load(input);

			Set<String> keys = properties.stringPropertyNames();
			List<Stock> stocks = new ArrayList<Stock>();
			for (String key : keys) {

				String[] values = properties.getProperty(key).split(",");
				Future<Stock> stock = executor.submit(new StockChecker(values[1], values[0]));

				stocks.add(stock.get());

			}

			stocks.forEach(stock -> System.out.println(stock.toString()));

		} catch (IOException e) {

			System.out.println("File not found");

		} catch (InterruptedException | ExecutionException e) {

			System.out.println("Thread error: " + e);

		}

	}

}
