import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumParallel {
	public static void main(String[] args) {
		long minNumber = 0;
		long maxNumber = 1_000_000_000;
		long before = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		int numProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println("Available processes(including hyperthreaded): "
				+ numProcessors);
		long numElements = maxNumber / numProcessors;
		Sum sum = new Sum(minNumber, maxNumber, numElements);
		pool.execute(sum);
		pool.shutdown();
		while (pool.getActiveThreadCount() > 0) {
			Thread.yield();
		}
		long num = sum.getSum();
		long after = System.currentTimeMillis();
		System.out.println("time with parallelism = " + (after - before));
		System.out.println("SUM(" + minNumber + ".." + maxNumber + ") = " + num);
	}

	static class Sum extends RecursiveTask<Long> {
		public static final long serialVersionUID = 1;
		private long maxNum;
		private long minNum;
		private long numElements;
		private long sum;

		Sum(long minNum, long maxNum, long numElements) {
			this.sum = 0;
			this.minNum = minNum;
			this.maxNum = maxNum;
			this.numElements = numElements;
		}

		public long getSum() {
			return this.sum;
		}

		protected Long compute() {
			if ((maxNum - minNum) < numElements) {
				for (long i = minNum; i <= maxNum; i++) {
					sum += i;
				}
				return sum;
			}
			Sum s1 = new Sum(minNum, minNum + (maxNum - minNum) / 2, numElements);
			Sum s2 = new Sum(minNum + ((maxNum - minNum) / 2) + 1, maxNum, numElements);
			s1.fork();
			s2.fork();
			sum = s1.join() + s2.join();
			return sum;
		}
	}
}