package com.sinensia.logic;

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

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sinensia.classes.Stock;
import com.sinensia.principal.Principal;
@Configuration
@EnableAsync
@EnableScheduling
@Component
public class StockCheckerLauncher {

	@Scheduled(fixedRate = 5000)
	public void launch() throws IOException, InterruptedException, ExecutionException {

		try (InputStreamReader input = new InputStreamReader(
				Principal.class.getClass().getResourceAsStream("/config.properties"))) {

			Properties properties = new Properties();
			properties.load(input);

			Set<String> keys = properties.stringPropertyNames();
			ExecutorService executor = Executors.newFixedThreadPool(keys.size());
			List<Stock> stocks = new ArrayList<Stock>();
			for (String key : keys) {

				String[] values = properties.getProperty(key).split(",");
				Future<Stock> stock = executor.submit(new StockChecker(values[1], values[0]));

				stocks.add(stock.get());

			}

			stocks.forEach(stock -> System.out.println(stock.toString()));

		} catch (IOException e) {
			System.out.println("error1");
			throw e;

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("error2");
			throw e;

		}
	}
}
