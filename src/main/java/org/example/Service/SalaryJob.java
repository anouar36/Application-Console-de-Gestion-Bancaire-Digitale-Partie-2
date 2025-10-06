package org.example.Service;

import org.example.Repository.AccountRepository;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SalaryJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        try {
            AccountRepository accountRepository = new AccountRepository();
            CreditService creditService = new CreditService();

            boolean success = accountRepository.addSalary();

            boolean success1 = creditService.deductions();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startScheduler() throws Exception {
        JobDetail job = JobBuilder.newJob(SalaryJob.class)
                .withIdentity("salaryJob", "bankingGroup")
                .build();


        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("salaryTrigger", "bankingGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                .build();



        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();


    }
}