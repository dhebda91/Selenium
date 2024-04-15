package commons.utils;

import commons.helpers.FileManagingHelper;
import io.qameta.allure.AllureResultsWriteException;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.util.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static java.util.stream.Collectors.toList;

public class AllureAttachmentsRemover implements io.qameta.allure.listener.TestLifecycleListener {
    private static final Properties properties = PropertiesUtils.loadAllureProperties();
    private static final String outputDirectory = properties.getProperty("allure.results", "allure-results");
    private static final String patternForRemove = "^((?!txt).)*$";

    @Override
    public void beforeTestWrite(TestResult testResult) {
//        String deletionFlag= ConfigurationProperties.getProperties().getProperty(...) tutaj można dodać jakąś flagę
        if (testResult.getStatus().equals(Status.PASSED)) {
            removeMatchingAttachments(testResult.getAttachments());
            List<StepResult> steps = testResult.getSteps();
            removeStepsAttachments(steps);
        }
    }

    private void removeMatchingAttachments(Collection<Attachment> attachments) {
        List<Attachment> attachmentsToRemove = attachments.stream()
                .filter(attachment -> !Objects.equals(attachment.getType(), "text/plain"))
                .collect(toList());
        attachmentsToRemove.forEach(this::deleteAttachmentFile);
        attachments.removeAll(attachmentsToRemove);
    }

    private void removeStepsAttachments(List<StepResult> steps){
        for (StepResult stepResult:steps){
            List<Attachment> attachments = stepResult.getAttachments();
            removeMatchingAttachments(attachments);
            List<StepResult> subSteps = stepResult.getSteps();
            if(!subSteps.isEmpty()){
                removeStepsAttachments(subSteps);
            }
        }
    }

    private void deleteAttachmentFile(Attachment attachment) {
        File resultDir = FileManagingHelper.convertStringPathToAbsoluteFilePath(outputDirectory);
        Path path = resultDir.toPath();
        Path filePath = path.resolve(attachment.getSource());
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not remove Allure attachment", e);
        }
    }
}
