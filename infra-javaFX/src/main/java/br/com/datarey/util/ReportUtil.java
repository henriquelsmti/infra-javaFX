package br.com.datarey.util;

import br.com.datarey.app.BaseReport;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by henrique.luiz on 16/02/2016.
 */
public class ReportUtil {

    private BaseReport baseReport;
    private Stage stage;

    public ReportUtil(BaseReport baseReport, Stage stage){
        this.baseReport = baseReport;
        this.stage = stage;
    }

    public void exportToPDF(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToPDF(baseReport.gerarRelatorio(lista, metadados)), ".pdf");
    }

    public void exportToRTF(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToRTF(baseReport.gerarRelatorio(lista, metadados)), ".rtf");
    }

    public void exportToDOCX(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToDOCX(baseReport.gerarRelatorio(lista, metadados)), ".docx");
    }

    public void exportToXLSX(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToXLSX(baseReport.gerarRelatorio(lista, metadados)), ".xlsx");
    }

    public void exportToPPTX(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToPPTX(baseReport.gerarRelatorio(lista, metadados)), ".pptx");
    }

    public void exportToODT(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToODT(baseReport.gerarRelatorio(lista, metadados)), ".odt");
    }

    public void exportToODS(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToODS(baseReport.gerarRelatorio(lista, metadados)), ".ods");
    }

    public void exportToHTML(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToHTML(baseReport.gerarRelatorio(lista, metadados)), ".html");
    }

    public void exportToTXT(List<?> lista, List<Map<String, Object>> metadados){
        saveFile(baseReport.exportToTXT(baseReport.gerarRelatorio(lista, metadados)), ".txt");
    }


    public void openPDF(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToPDF(baseReport.gerarRelatorio(lista, metadados)), ".pdf");
    }


    public void openRTF(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToRTF(baseReport.gerarRelatorio(lista, metadados)), ".rtf");
    }

    public void openDOCX(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToDOCX(baseReport.gerarRelatorio(lista, metadados)), ".docx");
    }

    public void openXLSX(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToXLSX(baseReport.gerarRelatorio(lista, metadados)), ".xlsx");
    }

    public void openPPTX(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToPPTX(baseReport.gerarRelatorio(lista, metadados)), ".pptx");
    }

    public void openODT(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToODT(baseReport.gerarRelatorio(lista, metadados)), ".odt");
    }

    public void openODS(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToODS(baseReport.gerarRelatorio(lista, metadados)), ".ods");
    }

    public void openHTML(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToHTML(baseReport.gerarRelatorio(lista, metadados)), ".html");
    }

    public void openTXT(List<?> lista, List<Map<String, Object>> metadados){
        openFile(baseReport.exportToTXT(baseReport.gerarRelatorio(lista, metadados)), ".txt");
    }



    private void openFile(ByteArrayOutputStream stream, String extension){

        String tempDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "temp";
        File arquivoDir = new java.io.File(tempDir);
        if(!arquivoDir.exists()){
            arquivoDir.mkdir();
        }

        File arquivo = new File(arquivoDir.getPath() + System.getProperty("file.separator") +
                System.currentTimeMillis() +
                Thread.currentThread().getId() + extension);

        FileOutputStream in = null;
        try {
            in = new FileOutputStream(arquivo);
            in.write(stream.toByteArray());
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Desktop.getDesktop().open(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(ByteArrayOutputStream stream, String extension){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar");
        fileChooser.setInitialFileName("Relatorio " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extension.replace(".", "").toUpperCase(), "*" + extension));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            FileOutputStream in = null;
            try {
                in = new FileOutputStream(file);
                in.write(stream.toByteArray());
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
