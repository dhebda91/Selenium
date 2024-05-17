package page.objects;

public class PageObjectManager {
    DashboardPage dashboardPage;
    AutomationPracticeFormPage automationPracticeFormPage;

    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage() : dashboardPage;
    }
    public AutomationPracticeFormPage getAutomationPracticeFormPage(){
        return (automationPracticeFormPage == null) ? automationPracticeFormPage = new AutomationPracticeFormPage() : automationPracticeFormPage;
    }
}