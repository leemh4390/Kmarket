package kr.co.kmarket.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.vo.VisitVO;

@Component
@EnableScheduling
public class VisitorCountScheduler  {
	
	private int weeklyVisitorCount = 0;
	
	private int nowVisitorCount;
	
	@Autowired
	private AdminDAO dao;
	
    public void setNowVisitorCount(int nowVisitorCount) {
        this.nowVisitorCount = nowVisitorCount;
    }
	
    // 현재 방문자 수 count
	@Scheduled(cron = "${myapp.schedule.cron.1hour}")
	public void updateNowVisitorCount1Hour() {
		dao.updateVisitorCount1Hour(nowVisitorCount);

	}
	
	// 어제 방문자 수 count
	@Scheduled(cron = "${myapp.schedule.cron.1Day}")
	public void updateVisitorCountScheduler1Day() {
		dao.updateVisitorCount1DaySave();
		dao.updateVisitorCount1Week();
		dao.updateVisitorCountTotal();
		dao.updateVisitorCount1DayInit();
	}
	
	// 주간 방문자수
	@Scheduled(cron = "${myapp.schedule.cron.Monday}") // 월요일마다 0시 0분에 실행
    public void resetVisitorCount() {
    	dao.updateVisitorCount1WeekInit();
    	dao.updateVisitorCount1Month();
    }
}
