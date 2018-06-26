package org.stock.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author wangheng
 *
 */
public class AppUtil {

	public static final String GC_BPA_LOADER = "GCBPALoader";
	public static final String GC_BPA_2_PR = "GCBPA2PR";
	public static final String GC_STATE_SAMPLE = "GCStateSample";

	public static final String GC_STATE_ESTIMATE = "GCStateEstimate";
	public static final String GC_RELIABILITY_INDEX = "GCReliabilityIndex";

		
	public static ExecutorService  pool = Executors.newCachedThreadPool();
	
	public static Process execute(String appPath, AppLogger appLogger, String... args) {
		StringBuffer cmd = new StringBuffer();
		//cmd.append(appPath);

		List<String> params = new ArrayList<>();
		params.add(appPath);
		params.addAll(Arrays.asList(args));
		for (String param : params) {
			cmd.append(" "+param);
		}
		
		final AppLogger logger = (appLogger != null ? appLogger : new AppLogger() {
			
			@Override
			public void info(String log) {
				System.out.println(log);
			}
			
			@Override
			public void error(String log) {
				System.err.println(log);
			}
		});
		
		
		logger.info(cmd.toString());
		
		
		try {
			
			ProcessBuilder pb =
				      new ProcessBuilder(params).inheritIO();
			
			Process process = pb.start();
			//Process process = Runtime.getRuntime().exec(cmd.toString());
			
			

			InputStream is = process.getInputStream();
			print(is, (message)->{logger.info(message);});
			
			InputStream eis = process.getErrorStream();
			print(eis, (message)->{logger.error(message);});
			
			return process;
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return null;
	}
	

	
	/**
	 * 
	 * @param appPath
	 * @param T
	 */
	public static Process execute(String appPath, String... args) {
		return execute(appPath, null, args);
	}

	public interface LoggerPrinter {
		void print(String message);
	}
	public static void print(InputStream is, LoggerPrinter p) throws IOException {
		BufferedReader bri = new BufferedReader(new InputStreamReader(is, "gbk"));
		
		String message = new String("<exec:app> ");
		while ((message = bri.readLine()) != null) {
			p.print(message);
		}

		bri.close();	
	}
	
	public static void print(InputStream is) throws IOException {
		print(is, null);	
	}
	
//	public static AppExecuteBuilder execBuilder(String appPath) {
//		return new AppExecuteBuilder(appPath);
//	}
	
	public static void main(String [] args) {
		boolean rt = isRunning("mysqld");
		System.out.println(rt);
	}
	public static boolean isRunning(String appName) {
		String line;
		String pidInfo ="";

		Process p;
		try {
			p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = input.readLine()) != null) {
			    pidInfo+=line; 
			}

			input.close();

			if(pidInfo.contains(appName.endsWith(".exe") ? appName : appName+".exe"))
			{
			    return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return false;
	}



}
