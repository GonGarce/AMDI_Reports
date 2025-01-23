package com.fernandowirtz.reportsexample.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Gonzalo
 */
public class Report {

    private final JasperPrint report;

    protected Report(JasperPrint jp) {
        this.report = jp;
    }

    public Report view() {
        JasperViewer.viewReport(report, false);
        return this;
    }

    public Report toPdf(String destFile) throws IOException {
        return toPdf(destFile, true);
    }

    public Report toPdf(String destFile, boolean open) throws IOException {
        try {
            JasperExportManager.exportReportToPdfFile(report, destFile);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        if (open) {
            Desktop.getDesktop().open(new File(destFile));
        }
        return this;
    }
}
