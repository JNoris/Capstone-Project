package controller;

import broker.TransactionBroker;
import domain.Transaction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import manager.ControllerManager;
import utility.Timer;

/**
 * Controls the creation of sale reports.
 *
 * @author Vinicius Smith
 */
public class SaleReportController {

    @FXML
    private TextField textFieldFileName;
    @FXML
    private TextField textFieldLocation;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;

    private File folder;

    /**
     * Creates the prompt for user to find the .CSV file
     */
    public void selectFolderClicked() {
        Timer.resetTimer();
        DirectoryChooser dirChooser = new DirectoryChooser();
        folder = dirChooser.showDialog(ControllerManager.getInstance().getWindow());
        textFieldLocation.setText(folder.getAbsolutePath());
    }

    /**
     * The button to validate correct user input.
     */
    public void confirmBtnClicked() {
        Timer.resetTimer();
        if (textFieldFileName.getText().isEmpty()) {
            textFieldFileName.requestFocus();
            return;
        }
        if (textFieldLocation.getText().isEmpty()) {
            textFieldLocation.requestFocus();
            return;
        }
        if (fromDatePicker.getValue() == null) {
            fromDatePicker.requestFocus();
            return;
        }
        if (toDatePicker.getValue() == null) {
            toDatePicker.requestFocus();
            return;
        }

        TransactionBroker transacationBroker = new TransactionBroker();
        LocalDate fromLocalDate = fromDatePicker.getValue();
        LocalDate toLocalDate = toDatePicker.getValue();

        String sFromDate = fromLocalDate.getYear() + "-" + fromLocalDate.getMonthValue() + "-" + fromLocalDate.getDayOfMonth();
        String sToDate = toLocalDate.getYear() + "-" + toLocalDate.getMonthValue() + "-" + toLocalDate.getDayOfMonth();

        writeFile(transacationBroker.getTransactionsBetweenDates(sFromDate, sToDate));
        ControllerManager.getInstance().hidePopup();

    }

    /**
     * The button to validate incorrect user input.
     */
    public void cancelBtnClicked() {
        Timer.resetTimer();
        ControllerManager.getInstance().hidePopup();
    }

    /**
     * Checks if the filename is a Microsoft Excel file.
     *
     * @param filename the filename to correct.
     * @return the filename with .csv appended.
     */
    private String correctFileName(String filename) {
        if (filename.endsWith(".csv")) {
            return filename;
        }
        return filename + ".csv";
    }

    /**
     * Persists the transactions onto a Microsoft Excel Sheet.
     *
     * @param transactions list of transactions to be written.
     */
    private void writeFile(List<Transaction> transactions) {
        File file = new File(folder.getAbsolutePath() + "/" + correctFileName(textFieldFileName.getText()));
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("ID,Date,Total Price\n");
            for (Transaction t : transactions) {
                String line = String.format("%d,%s,$%.2f\n", t.getTransactionID(), t.getTransactionDate(), t.getFinalPrice());
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save file. Error:" + e.getMessage());
        }
        Timer.resetTimer();
    }
}
