package com.fernandowirtz.reportsexample.reports;

import com.fernandowirtz.reportsexample.MainFrame;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Gonzalo
 */
public class ReportService {

    private final String url;
    private final String user;
    private final String password;

    public ReportService(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Report get(String reportPath) throws Exception {
        return get(reportPath, null);
    }

    public Report get(String reportPath, Map<String, Object> parameters) throws Exception {
        try (InputStream is = MainFrame.class.getResourceAsStream(reportPath)) {
            try (Connection con = DriverManager.getConnection(url, user, password)) {
                JasperReport jr = JasperCompileManager.compileReport(is);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameters, con);
                return new Report(jp);
            }
        }
    }
}
