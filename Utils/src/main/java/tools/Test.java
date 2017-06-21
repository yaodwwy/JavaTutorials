package tools;


public class Test {
	public static void main(String[] args) throws Exception {
		Runtime run = Runtime.getRuntime();
		Process exec = run.exec("calc.exe");
		Thread.sleep(3000);
		exec.destroy();
	}
}
