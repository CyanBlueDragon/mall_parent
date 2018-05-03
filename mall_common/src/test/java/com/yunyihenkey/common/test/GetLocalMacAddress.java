package com.yunyihenkey.common.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class GetLocalMacAddress {
	// public static void main(String[] args) throws UnknownHostException {
	// System.out.println(InetAddress.getLocalHost().getHostAddress());
	// }
	public static void main(String[] args) throws Exception {
		InetAddress ia = InetAddress.getLocalHost();
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		String macStr = DatatypeConverter.printHexBinary(mac);

		System.out.println("本机MAC地址未：" + macStr);

		System.out.println(new Random().nextLong());

		long a = 214748364700988L;
		int b = (int) a;
		System.out.println(b);
	}

}
