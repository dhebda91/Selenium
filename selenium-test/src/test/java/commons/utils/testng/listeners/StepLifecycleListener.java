package commons.utils.testng.listeners;

import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import commons.utils.ScreenShotMaker;

import java.util.List;

public class StepLifecycleListener implements io.qameta.allure.listener.StepLifecycleListener {

    @Override
    public void beforeStepStop(StepResult result) {
        if (result.getStatus() != Status.SKIPPED) {
            ScreenShotMaker.makeScreenShot();
        }
    }
}