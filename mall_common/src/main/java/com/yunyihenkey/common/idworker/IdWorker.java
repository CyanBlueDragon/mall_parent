package com.yunyihenkey.common.idworker;

/**
 * 
 * @desc id生成器
 */
public interface IdWorker {

	// ==============================Fields===========================================
	// /** 开始时间截 (2015-01-01) */
	// public static final long twepoch = 1420041600000L;
	/** 开始时间截 (2018-01-01) */
	public static final long twepoch = 1514736000000L;

	/** 机器id所占的位数 */
	public static final long workerIdBits = 5L;

	/** 数据标识id所占的位数 */
	public static final long datacenterIdBits = 5L;

	/** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
	public static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

	/** 支持的最大数据标识id，结果是31 */
	public static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	/** 序列在id中占的位数 */
	public static final long sequenceBits = 12L;

	/** 机器ID向左移12位 */
	public static final long workerIdShift = sequenceBits;

	/** 数据标识id向左移17位(12+5) */
	public static final long datacenterIdShift = sequenceBits + workerIdBits;

	/** 时间截向左移22位(5+5+12) */
	public static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	/** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
	public static final long sequenceMask = -1L ^ (-1L << sequenceBits);

	public long nextId();

}