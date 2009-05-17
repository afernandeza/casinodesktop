package gamesmanager.db.sync;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;
import org.tiling.scheduling.examples.iterators.DailyIterator;

public class SyncScheduler {

    private final Scheduler scheduler = new Scheduler();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd MMM yyyy HH:mm:ss.SSS");
    private final int hourOfDay, minute, second;

    public SyncScheduler(int hourOfDay, int minute, int second) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
    }

    public void start() {
        scheduler.schedule(new SchedulerTask() {
            public void run() {
                runTask();
            }

            private void runTask() {
                System.out.println("Starting scheduled sync at "
                        + dateFormat.format(new Date()));
                SynchronizerThread st = new SynchronizerThread();
                st.execute();
            }
        }, new DailyIterator(hourOfDay, minute, second));
    }
}