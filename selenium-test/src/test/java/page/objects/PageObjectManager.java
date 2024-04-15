package page.objects;

public class PageObjectManager {
    DashboardPage dashboardPage;

    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage() : dashboardPage;
    }
}