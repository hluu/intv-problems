package org.learning.common;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;

public class SystemInfo {

	private Runtime runtime = Runtime.getRuntime();

	public static void main(String[] args) {
		SystemInfo sysInfo = new SystemInfo();
		System.out.println(sysInfo.info());
		
		printUsage();
	}

	public String info() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.OsInfo());
		sb.append(this.MemInfo());
		sb.append(this.DiskInfo());
		return sb.toString();
	}

	public String OSname() {
		return System.getProperty("os.name");
	}

	public String OSversion() {
		return System.getProperty("os.version");
	}

	public String OsArch() {
		return System.getProperty("os.arch");
	}

	public long totalMem() {
		return Runtime.getRuntime().totalMemory();
	}

	public long usedMem() {
		return Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
	}

	public String MemInfo() {
		NumberFormat format = NumberFormat.getInstance();
		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		sb.append("Free memory: ");
		sb.append(format.format(freeMemory / 1024));
		sb.append("\n<br/>");
		sb.append("Allocated memory: ");
		sb.append(format.format(allocatedMemory / 1024));
		sb.append("\n<br/>");
		sb.append("Max memory: ");
		sb.append(format.format(maxMemory / 1024));
		sb.append("\n<br/>");
		sb.append("Total free memory: ");
		sb.append(format
				.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
		sb.append("\n<br/>");
		return sb.toString();

	}

	public String OsInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("OS: ");
		sb.append(this.OSname());
		sb.append("\n<br/>");
		sb.append("Version: ");
		sb.append(this.OSversion());
		sb.append("\n<br/>");
		sb.append(": ");
		sb.append(this.OsArch());
		sb.append("\n<br/>");
		sb.append("Available processors (cores): ");
		sb.append(runtime.availableProcessors());
		sb.append("\n<br/>");
		return sb.toString();
	}

	public String DiskInfo() {
		/* Get a list of all filesystem roots on this system */
		File[] roots = File.listRoots();
		StringBuilder sb = new StringBuilder();

		/* For each filesystem root, print some info */
		for (File root : roots) {
			sb.append("File system root: ");
			sb.append(root.getAbsolutePath());
			sb.append("\n<br/>");
			sb.append("Total space (bytes): ");
			sb.append(root.getTotalSpace());
			sb.append("\n<br/>");
			sb.append("Free space (bytes): ");
			sb.append(root.getFreeSpace());
			sb.append("\n<br/>");
			sb.append("Usable space (bytes): ");
			sb.append(root.getUsableSpace());
			sb.append("\n<br/>");
		}
		return sb.toString();
	}
	
	private static void printUsage() {
		  OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("get") 
		        && Modifier.isPublic(method.getModifiers())) {
		            Object value;
		        try {
		            value = method.invoke(operatingSystemMXBean);
		        } catch (Exception e) {
		            value = e;
		        } // try
		        System.out.println(method.getName() + " = " + value);
		    } // if
		  } // for
		}
}