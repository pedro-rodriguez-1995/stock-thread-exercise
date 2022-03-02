package com.sinensia.principal;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.sinensia.logic.StockCheckerLauncher;

public class Principal {
	

	public static void main(String[] args) {
		

		try {

			StockCheckerLauncher.launch();

		} catch (IOException e) {

			System.out.println("File not found");

		} catch (InterruptedException | ExecutionException e) {

			System.out.println("Thread error: " + e);

		}

	}

}
