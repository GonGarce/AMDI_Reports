package com.fernandowirtz.reportsexample;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.SAVE_DIALOG;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gonzalo
 */
public class SavePdfChooser {

    private final JFileChooser chooser;
    private final Component parent;

    public SavePdfChooser(Component parent) {
        this.parent = parent;
        chooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (f.exists() && getDialogType() == SAVE_DIALOG) {
                    int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (result != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                super.approveSelection();
            }
        };
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
    }

    public String getFilePath() {
        int returnVal = chooser.showSaveDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
