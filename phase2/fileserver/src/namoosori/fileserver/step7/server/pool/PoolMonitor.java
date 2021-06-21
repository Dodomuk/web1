/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.pool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PoolMonitor extends Thread {
	//
	private static final int CHECK_COUNT = 6; 
	
	private HandlerPool parentPool; 
	private Map<MonitorTarget, Integer[]> targetMap; 
	
	private PoolMonitor(HandlerPool parentPool) {
		//
		this.parentPool = parentPool; 
		this.targetMap = new HashMap<MonitorTarget, Integer[]>(); 
		for(MonitorTarget target : parentPool.getMonitorTargets()) {
			targetMap.put(target, new Integer[CHECK_COUNT]); 
		}
	}
	
	public static PoolMonitor newInstance(HandlerPool parentPool) {
		// 
		return new PoolMonitor(parentPool); 
	}
	
	public void run() {
		// 
		int monitoringSequence = 0; 
		
		while(true) {
			// 
			sleepInSeconds(5);

			checkIdleTime(monitoringSequence); 
			monitoringSequence++;

			if (monitoringSequence == CHECK_COUNT) {
				monitoringSequence = 0; 
				int totalAverageQueueSize = asignAverageIdleTime();
				// 강제로 EventRouter를 증가시키려면, 아래 로직 실행 
				// parentPool.reportMonitoringResult(25); 
				parentPool.reportMonitoringResult(totalAverageQueueSize); 
			} 
		}
	}
	
	private void checkIdleTime(int monitorCount) {
		// 
		System.out.println("Checking idle time...");
 
		Iterator<MonitorTarget> targetIter = targetMap.keySet().iterator(); 
		while(targetIter.hasNext()) {
			MonitorTarget target = targetIter.next(); 
			Integer[] values = targetMap.get(target); 
			values[monitorCount] = target.getQueueSize(); 
		}
	}
	
	private int asignAverageIdleTime() {
		// 
		int totalAverageQueueSize = 0; 
		Iterator<MonitorTarget> targetIter = targetMap.keySet().iterator(); 
		while(targetIter.hasNext()) {
			MonitorTarget target = targetIter.next(); 
			Integer[] queueSizes = targetMap.get(target); 
			
			int total = 0; 
			for(Integer queueSize : queueSizes) {
				total += queueSize; 
			}
			int averageQueueSize = total/queueSizes.length; 
			totalAverageQueueSize += averageQueueSize; 
		}
		return (totalAverageQueueSize/targetMap.size()); 
	}
	
	private void sleepInSeconds(int seconds) {
		// 
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	}
}