package client;

import java.io.Serializable;

import sharedobjects.AddNumbersTask;

public class AddNumbersCalculation implements AddNumbersTask, Serializable {
	public static final long serialVersionUID = 1;
	private long minNum = 0;
	private long maxNum = 0;

	public AddNumbersCalculation(long minNum, long maxNum) {
		this.minNum = minNum;
		this.maxNum = maxNum;
	}

	public long getMinimum() {
		return minNum;
	}

	public long getMaximum() {
		return maxNum;
	}

	public long getSum() {
		long sum = 0;
		for (long i = minNum; i <= maxNum; i++) {
			sum += i;
		}
		return sum;
	}

}