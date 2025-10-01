package org.example.Validation;

import org.example.Repository.AccountRepository;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;

public class SalaryJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("🕐 بدء تنفيذ Job في: " + new Date());

        try {
            // استدعاء المنطق الفعلي لإضافة الرواتب
            AccountRepository accountRepository = new AccountRepository();
            boolean success = accountRepository.addSalary();

            if (success) {
                System.out.println("✅ تم تنفيذ Job بنجاح");
            } else {
                System.out.println("❌ فشل تنفيذ Job");
            }
        } catch (Exception e) {
            System.err.println("❌ خطأ في تنفيذ Job: " + e.getMessage());
            throw new JobExecutionException(e);
        }
    }

    public static void startScheduler() throws Exception {
        JobDetail job = JobBuilder.newJob(SalaryJob.class)
                .withIdentity("salaryJob", "bankingGroup")
                .build();

        // الـ Trigger: كيخدم في أول يوم من كل شهر الساعة 00:00
        // Cron: "0 0 0 1 * ?" = ثانية 0، دقيقة 0، ساعة 0، يوم 1، كل شهر
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("salaryTrigger", "bankingGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();

        // باش تختبر: كل 30 ثانية (حيّد هاد التعليق ملي تبغي تجرب)
        // Trigger trigger = TriggerBuilder.newTrigger()
        //         .withIdentity("salaryTrigger", "bankingGroup")
        //         .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
        //         .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        System.out.println("✅ تم تشغيل Scheduler بنجاح!");
        System.out.println("📅 سيتم تنفيذ Job في اليوم الأول من كل شهر");
    }
}