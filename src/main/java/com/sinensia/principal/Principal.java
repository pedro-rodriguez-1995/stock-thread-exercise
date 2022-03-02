package com.sinensia.principal;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sinensia.logic.StockCheckerLauncher;

public class Principal {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
		annotationContext.scan("com.sinensia.logic");
		annotationContext.refresh();

		StockCheckerLauncher stockcheckerlauncher = annotationContext.getBean(StockCheckerLauncher.class);
		try {
			stockcheckerlauncher.launch();
			waitMethod();
		} catch (IOException e) {
			System.out.println("File not found: "+e);
			e.printStackTrace();
		} catch (InterruptedException  | ExecutionException e) {
			System.out.println("Thread error: "+e);
			e.printStackTrace();
		} finally {
			annotationContext.close();
		}

	}

	public synchronized static void waitMethod() {
		while (true) {
		}
	}
}
