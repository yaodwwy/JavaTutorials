package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StreamGrabber extends Thread {

	private final InputStream stream;

	private final List<String> holder;

	public StreamGrabber(InputStream stream) {

		this(stream, null);

	}

	public StreamGrabber(InputStream stream, List<String> holder) {

		this.stream = stream;

		this.holder = holder;

	}

	@Override
	public void run() {

		try {

			BufferedReader br = new BufferedReader(

			new InputStreamReader(stream));

			String line = null;

			while ((line = br.readLine()) != null) {

				if (holder != null)

					holder.add(line);

			}

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}

	}

	public static Process performCommand(String command) {

		try {

			return Runtime.getRuntime().exec(command);

		} catch (IOException e) {

			e.printStackTrace();

			return null;

		}

	}

	public static void main(String[] args) throws InterruptedException {

		performCommand("calc.exe");
		// notepa.exe关闭记事本进程
		Thread.sleep(2000);
		String command = "taskkill /f /im Calculator.exe";
		Process proc = performCommand(command);
		List<String> outputs = new ArrayList<String>();
		new StreamGrabber(proc.getInputStream(), outputs).start();

	}

}