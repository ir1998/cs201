public class SumNoParallel {
	public static void main(String[] args) {
		long maxNumber = 1_000_000_000;
		long before = System.currentTimeMillis();
		Sum sum = new Sum(maxNumber);
		long num = sum.compute();
		long after = System.currentTimeMillis();
		System.out.println("time without parallelism = " + (after - before));
		System.out.println("SUM(0.." + maxNumber + ") = " + num);
	}

	static class Sum {
		private long maxNum;

		Sum(long maxNum) {
			this.maxNum = maxNum;
		}

		protected Long compute() {
			long sum = 0;
			for (int i = 0; i <= maxNum; i++) {
				sum += i;
			}
			return sum;
		}
	}
}